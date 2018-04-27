(ns oas.resolve
  "Resolve references in OAS files."
  (:require [clojure.string :as s]
            [clojure.algo.generic.functor :only (fmap)]))

(defn reference? 
  [reference-string]
  (= (first reference-string) \#))

(defn root?
  [reference-string]
  (= "#" reference-string))

(defn- int-or-key [x]
  (if (re-matches #"\d" x) 
      (Integer. x) 
      (keyword x)))

(defn reference-list 
  "Convert a reference string to a searchable sequence of keys.
   Integers are converted to integer values for accessing arrays."
  [reference-string] 
  (-> reference-string 
      (when-not "") ;;this line guards against nil values.
      (s/replace-first #"#" "")
      (s/split #"/")
      (->> (remove #(= "" %))
           (map int-or-key))))

(defn to-reference
  "Takes a reference list and returns a reference string."
  [reference-list]
  (->> reference-list
       (interpose \/)
       (map name)
       (s/join)
       (str "#")))

(defn revise-reference 
  "Takes a references string and some function f. 
   Uses function f to modify the list of refernce string keys.
   Then reconstructs a reference string out of the result."
  [reference-string f]
  (->> reference-string
       (comp f ref-to-keys)
       (to-reference)))

(defn last-of 
  "Returns the last segment of a reference string."
  [reference-string]
  (-> reference-string 
      (ref-to-keys)
      (last)))

(defn first-of 
  [reference-string]
  (-> reference-string
      (ref-to-keys)
      (first)))

(defn resolve-reference 
    "Resolve a reference in an OAS document."
    [oas reference-string] 
    (->> reference-string
         (ref-to-keys)
         (resolve-list oas))) 

(defn resolve-references 
  "Resolve references."
  [oas references] 
  (map #(resolve-reference oas %) references))

(defn resolve-list 
  "Resolve a list of references."
  [oas reference-list]
  (if (empty? reference-list)
      oas
      (recur (get oas (first reference-list)) (rest reference-list))))
