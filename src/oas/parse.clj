(ns oas.parse 
  "Parse OAS files."
  (:require [cheshire.core :refer :all]))

(defn parse-oas [oas] 
  "Parse the entirety of an OAS file." 
  (parse-string oas true))

(defn specification-version [oas] 
  "Identify the version of an OAS file."
  (let [parsed (parse-string oas true)]
    (if (contains? parsed :swagger) 
        {:swagger (get parsed :swagger)} 
        {:openapi (get parsed :openapi)})))

(defn find-part [oas part]
  "Return the specified part of the OAS document."
  {(keyword part) (get oas (keyword part) nil)})

(defn find-parts [oas parts]
  "Return the specified parts of the OAS document."
  (map #(find-part oas %) (set parts)))
