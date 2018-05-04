(ns oas.update
  "Reducing functions."
  (:require [oas.resolve :as r]
            [oas.json-pointer :as j]))

(defn append-to 
  [json k v] 
  (when ((complement contains?) json k)
    (assoc json k v))) 

(defn update-value 
  "Update a key with a value in place."
  [json pointer v]
  (assoc-in json (j/parse-pointer pointer) v))

(defn update-each 
  "Modify multiple objects in place."
  [oas pointers v] 
  (if (empty? pointers)
      oas
      (recur (modify oas (first pointers) v) (rest pointers) v)))

(defn merge-json 
  "Merge oas objects."
  ([json json*]
   (merge json json*))
  ([json json* pointer pointer*]
   (modify json pointer
     (merge (r/pointer->value json pointer) (r/pointer->value json* pointer*)))))
