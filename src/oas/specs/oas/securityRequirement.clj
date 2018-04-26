(ns oas.specs.oas.securityRequirement
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::securityRequirement 
  (spec/map-of keyword? (spec/coll-of string?)))
