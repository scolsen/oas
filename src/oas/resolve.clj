(ns oas.resolve
  "Resolve references in OAS files."
  (:require [oas.json-pointer :as jp]))

(defn pointer->value 
  "Take a pointer and return the value mapped to it in the provided json."
  [json pointer]
  (->> pointer
       (jp/parse-pointer)
       (get-in json)))

(defn pointers->values 
  "Resolve multiple json pointers."
  [json pointers] 
  (map #(pointer->value json %) pointers))
