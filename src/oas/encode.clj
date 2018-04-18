(ns oas.encode
  "Encode an OAS document."
  (:require [cheshire.core :refer :all]))

(defn oas [part] 
  "Encode an oas document."
  {part})

(defn info [part] 
  "Encode an info object."
  {:info part})

(defn definitions [part] 
  "Encode a definitions object."
  {:definitions part})

(defn servers [part] 
  "Encode a servers object."
  {:servers part})

(defn paths [part] 
  "Encode a paths object."
  {:paths part})

(defn components [part] 
  "Encode a components object."
  {:components part})

(defn security [part] 
  "Encode a security object."
  {:security part})

(defn tags [part] 
  "Encode a tags object."
  {:tags part})

(defn externalDocs [part] 
  "Encode an externalDocs object."
  {:externalDocs part})


