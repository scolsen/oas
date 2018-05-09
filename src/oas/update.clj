(ns oas.update
  "Reducing functions."
  (:require [json-pointer.core :as jp]))

(defn append-to 
  [json k v] 
  (when ((complement contains?) json k)
    (assoc json k v))) 

(defn update-value 
  "Update a key with a value in place."
  [json pointer v]
  (assoc-in json (jp/pointer->strings pointer) v))

(defn update-each 
  "Modify multiple objects in place."
  [oas pointers v] 
  (if (empty? pointers)
      oas
      (recur (update-value oas (first pointers) v) (rest pointers) v)))

(defn merge-json 
  "Merge oas objects."
  ([json json*]
   (merge json json*))
  ([json json* pointer pointer*]
   (update-value json pointer
     (merge (jp/resolve-pointer json pointer) (jp/resolve-pointer json* pointer*)))))
