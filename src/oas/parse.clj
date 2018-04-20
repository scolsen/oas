(ns oas.parse 
  "Parse OAS files."
  (:require [cheshire.core :as che]))

(defn parse [oas] 
  "Parse the entirety of an OAS string." 
  (che/parse-string oas true))

(defn parse-file [file]
  "Parse the entirety of an OAS file."
  (che/parse-string (slurp file) true))
