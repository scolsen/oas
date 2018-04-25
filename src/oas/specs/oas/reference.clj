(ns oas.specs.oas.refernce
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]))

(spec/def ::reference (spec/map-of p/ref? string?))
