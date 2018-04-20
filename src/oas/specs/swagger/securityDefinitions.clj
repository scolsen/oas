(ns oas.specs.swagger.securityDefinitions 
  ""
  (:require [clojure.spec.alpha :as alpha]
            [oas.specs.swagger.securityScheme]))

(spec/def ::securityScheme (spec/map-of string? (spec/get-spec :oas.specs.swagger.securityScheme/securityScheme)))

