(ns oas.resolve
  "Resolve references in OAS files."
  (:require [oas.json-pointer :as jp]))

(defn resolve-pointer 
  ""
  [json pointer]
  (->> pointer
       (jp/parse-pointer)
       (jp/resolve-pointer-list json)))

(defn resolve-pointer-list 
  "Resolve a json pointer key-list."
  [json key-list]
  (if (empty? key-list)
      json
      (recur (get json (first key-list)) (rest key-list))))

(defn resolve-pointers 
  "Resolve multiple json pointers."
  [json pointer-lists] 
  (map #(resolve-json json %) pointer-lists))
