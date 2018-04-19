(ns oas.encode
  "Encode an OAS document."
  (:require [cheshire.core :as che] [clojure.java.io :as io] 
            [oas.validate :as validate]))

(defn encode [part] 
  "Encode an OAS document or part."
  (validate/conform part))


