(ns oas.resolve
  "Resolve references in OAS files."
  (:require [clojure.string :as s]))

(defn reference? 
  [reference-string]
  (= (first reference-string) \#))

(defn ref-to-keys [reference-string] 
  "Convert a reference string to a searchable key."
  (let [ref-path (s/split (s/replace-first reference-string #"#" "") #"/")]
  (map keyword ref-path)))

(defn resolve-reference [oas reference] 
  "Resolve a reference in an OAS document."
    (loop [r (ref-to-keys reference) res oas prev (first r)] 
      (if (empty? r) 
          res 
          (recur (rest r) (get res (first r)) (first r)))))

(defn resolve-references 
  "Resolve references."
  [api references] 
  (map #(resolve-reference api %) references))
