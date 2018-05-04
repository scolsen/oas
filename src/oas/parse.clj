(ns oas.parse 
  "Parse OAS files."
  (:require [cheshire.core :as che]
            [clojure.string :as s]))

(defn- kstring->key 
  "Coerce a swagger key, which may contain /, to a usable clojure key."
  [k] (keyword (s/replace k #"/" "_")))

(defn parse [oas] 
  "Parse the entirety of an OAS string." 
  (che/parse-string oas true))

(defn parse-file [file]
  "Parse the entirety of an OAS file."
  (che/parse-string (slurp file) kstring->key))
