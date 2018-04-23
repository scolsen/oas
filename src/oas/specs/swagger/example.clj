(ns oas.specs.swagger.example
  (:require [clojure.spec.alpha :as spec]))
 
(spec/def ::example (spec/map-of keyword? map?)) ;; should be a map of valid mime-types to maps.
