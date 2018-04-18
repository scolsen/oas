(ns oas.parse 
  "Parse OAS files."
  (:require [cheshire.core :refer :all]))

(defn parse [oas] 
  "Parse the entirety of an OAS file." 
  (parse-string oas true))
