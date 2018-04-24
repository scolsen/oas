(ns oas.resolve
  "Resolve references in OAS files."
  (:require [clojure.string :as s]))

(defn reference? 
  [reference-string]
  (= (first reference-string) \#))

(defn ref-to-keys [reference-string] 
  "Convert a reference string to a searchable key."
  (let [ref-path (s/split (s/replace-first reference-string #"#" "") #"/")]
  (map #(if (re-matches #"\d" %) (Integer. %) (keyword %)) ref-path)))

(defn resolve-reference [oas reference] 
  "Resolve a reference in an OAS document."
    (loop [r (if (string? reference) (ref-to-keys reference) reference) res oas] 
      (if (empty? r) 
          res 
          (recur (rest r) (get res (first r))))))

(defn resolve-list [oas reference]
  (if (empty? reference)
      oas
      (recur (get oas (first reference)) (rest reference))))

(defn resolve-map 
  "Resolve references, then iterate f over point in the reference path.
   Returns the result. Takes an f to run on each value, and a g to set the previously processed value."
  [f oas reference]
  (loop [r (ref-to-keys reference) 
         result (into {} (map #(vector (first %) (f (second %)))) (resolve-list oas r))]
        (if (empty? r)
            result
            (recur (butlast r)
              (assoc (resolve-list oas (butlast r)) (last r) result)))))

(defn resolve-root 
  [oas reference]
  (get oas (first (ref-to-keys reference))))

(defn resolve-references 
  "Resolve references."
  [api references] 
  (map #(resolve-reference api %) references))
