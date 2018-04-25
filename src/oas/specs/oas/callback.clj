(ns oas.specs.oas.callback 
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]
            [oas.specs.oas.path]))

(spec/def ::callback (spec/map-of p/expression? 
  (spec/get-spec :oas.specs.oas.path/path)))
