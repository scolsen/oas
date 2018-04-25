(ns oas.specs.oas.paths 
  (:require [clojure.spec.alpha :as spec] [oas.specs.oas.path]))

(spec/def ::paths (spec/map-of keyword? (spec/get-spec :oas.specs.oas.path/path)))
