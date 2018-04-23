(ns oas.specs.swagger.definitions 
  "Representation of swagger definitions object."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.schema]
            [oas.specs.swagger.parameter]
            [oas.specs.swagger.response]
            [oas.specs.swagger.securityScheme]))

(spec/def ::definitions 
  (spec/map-of keyword? 
    (spec/or :schema (spec/get-spec :oas.specs.swagger.schema/schema) 
             :parameter (spec/get-spec :oas.specs.swagger.parameter/parameter) 
             :response (spec/get-spec :oas.specs.swagger.response/response) 
             :securityScheme (spec/get-spec :oas.specs.swagger.securityScheme/securityScheme))))
