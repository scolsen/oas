(ns oas.specs.swagger.securityDefinitions 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.securityScheme]))

(spec/def ::securityScheme (spec/map-of keyword? (spec/get-spec :oas.specs.swagger.securityScheme/securityScheme)))

