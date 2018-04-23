(ns oas.specs.swagger.securityRequirement 
  "Representation of a security requirement object."
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::securityRequirement (spec/map-of keyword? (spec/coll-of string?))) ;;has rules surrounding array values.


