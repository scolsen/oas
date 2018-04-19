(ns oas.specs.swagger.paths 
  "Paths"
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.primitives :refer :all]
            [oas.specs.swagger.path :refer :all]))

(spec/def ::paths (spec/map-of (spec/get-spec ::pathString) (spec/get-spec ::path)))
