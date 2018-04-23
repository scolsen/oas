(ns oas.specs.swagger.paths 
  "Paths"
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.path]))

;; TODO: add path validation.

(spec/def ::paths (spec/map-of keyword? (spec/get-spec :oas.specs.swagger.path/path)))
