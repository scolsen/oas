(ns oas.specs.swagger.definitions 
  "Representation of swagger definitions object."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.schema :refer :all]
            [oas.specs.swagger.parameter :refer :all]
            [oas.specs.swagger.response :refer :all]
            [oas.specs.swagger.securityScheme :refer :all]))

(spec/def ::definitions (spec/map-of string? (spec/or :schema (spec/get-spec ::schema) :parameter (spec/get-spec ::parameter) :response (spec/get-spec ::response) :securityScheme (spec/get-spec ::securityScheme))))
