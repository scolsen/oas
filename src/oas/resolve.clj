(ns oas.resolve
  "Resolve references in OAS files."
  (:require [clojure.string :as s]))

(defn ref-to-keys [reference-string] 
  "Convert a reference string to a searchable key."
  (let [ref-path (s/split (s/replace-first reference-string #"#" "") #"/")]
  (map keyword ref-path)))

(defn resolve-reference [oas reference] 
  "Resolve a reference in an OAS document."
    (loop [r (ref-to-keys reference) res oas] 
      (if (empty? r) 
          res 
          (recur (rest r) (get res (first r))))))


