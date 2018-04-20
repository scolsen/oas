(ns oas.specs.swagger.scopes 
  "A representation of a scopes object."
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::scopes (spec/map-of string? string?))
