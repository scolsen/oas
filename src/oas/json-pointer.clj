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
  [escape-vector]
  (if (= (last escape-vector) "0")
      "~"
      "/"))

(defn handle-bnf 
  "Handles a parsed reference-token."
  [bnf]
  (let [f (first bnf) r (second bnf)]
       (cond (= :escaped f) (escape bnf)
             (= :array-index f) (Integer. (s/join (rest bnf)))
             (= :reference-token f) (map handle-bnf (rest bnf))
             :else r)))

(defn token-list->key-or-int
  "Convert a list of tokens to a list of keys or ints."
  [token-list]
  (if (some integer? token-list)
      token-list
      (keyword (s/join token-list))))

(defn pointer->keys
  "Convert a json pointer string to a set of resolvable keys."
  [pointer]
  (->> pointer
       (filter vector?)
       (map handle-bnf)
       (map token-list->key-or-int)
       (flatten)))

(defn encode 
  "Escape invalid characters."
  [pointerstring]
  (-> pointerstring 
      (s/replace #"~" "~0")
      (s/replace #"/" "~1")))

(defn keys->pointer
  "Takes a list of keys and integers and returns a json pointer."
  [key-list]
  (->> key-list
       (map #(if (keyword? %) (s/replace-first (str %) #":" "") %))
       (map #(if (string? %) (encode %) %))
       (interpose \/)
       (s/join)
       (str "#")))

(defn parse-pointer
  "Parse a JSON pointer value into a list of keys."
  [json-pointer]
  (let [jp (json-pointer-parser json-pointer)] 
  (if (json-pointer? jp) 
      (pointer->keys jp) 
      (str "Invalid json pointer." (insta/get-failure jp)))))
