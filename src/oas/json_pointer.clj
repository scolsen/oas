(ns oas.json-pointer
  "Functions for manipulating JSON pointers. 
   Follows RFC 6901: https://tools.ietf.org/html/rfc6901"
  (:require [clojure.string :as s]
            [instaparse.core :as insta]))

(def json-pointer-parser (insta/parser "src/oas/data/json_pointer.txt" :input-format :abnf))

(defn json-pointer? 
  "Determine whether or not a string is a valid JSON pointer.
   Returns true iff the argument is a valid pointer."
  [json-pointer]
  ((complement insta/failure?) json-pointer))

(defn escape 
  "Converts escaped strings into their corresponding representations."
  [escape-vector [& { :keys [override-tilde override-slash]
                      :or {override-tilde "~" override-slash "/"}}]]
  (if (= (last escape-vector) "0")
      override-tilde
      override-slash))

(defn handle-bnf 
  "Handles a parsed reference-token."
  [bnf]
  (let [f (first bnf) r (second bnf)]
       (cond (= :escaped f) (escape bnf)
             (= :array-index f) (Integer. (s/join (rest bnf)))
             (= :reference-token f) (map handle-bnf (rest bnf))
             :else r)))

(defn token->* 
  "Convert a parsed token to a list of some other type."
  ([token-list f]
   (f token-list))
  ([token-list f p]
   (if (p token-list)
       (f token-list)
       token-list)))

(defn pointer->*
  "Convert a parsed pointer to a seq of some other type."
  ([f]
   (fn [pointer] 
       (->> pointer
            (filter vector?)
            (map handle-bnf)
            (map #(token->* % f))
            (flatten))))
  ([f p]
   (fn [pointer] 
       (->> pointer
            (filter vector?)
            (map handle-bnf)
            (map #(token->* % f p))
            (flatten)))))

(def pointer->keys (pointer->* (comp keyword s/join) (partial (complement some) integer?)))
(def pointer->strings (pointer->* s/join (partial (complement some) integer?)))

(defn *->pointer 
  "Takes a list of something and returns a json pointer."
  [f]
  (fn [xs]
      (->> (f xs)
           (interpose \/)
           (s/join)
           (str "#"))))

(defn- keys->pointer*
  "Takes a list of keys and integers and returns a json pointer."
  [ks]
  (->> ks
       (map #(if (keyword? %) (s/replace-first (str %) #":" "") %))
       (map #(if (string? %) (encode %) %))))


(def keys->pointer (*->pointer keys->pointer*))
(def strings->pointer (*->pointer identity))

(defn encode 
  "Escape invalid characters."
  [pointerstring]
  (-> pointerstring 
      (s/replace #"~" "~0")
      (s/replace #"/" "~1")))

(defn parse-pointer
  "Parse a JSON pointer value into a list of keys."
  [json-pointer]
  (let [jp (json-pointer-parser json-pointer)] 
  (if (json-pointer? jp) 
      (pointer->strings* jp) 
      (str "Invalid json pointer." (insta/get-failure jp)))))
