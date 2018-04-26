(ns oas.specs.oas.components 
  (:require [clojure.spec.alpha :as spec] [oas.specs.oas.schema]
            [oas.specs.oas.response] [oas.specs.oas.parameter]
            [oas.specs.oas.example] [oas.specs.oas.requestBody]
            [oas.specs.oas.header] [oas.specs.oas.securityScheme]
            [oas.specs.oas.link]))

(spec/def ::schemas (spec/map-of keyword? (spec/get-spec :oas.specs.oas.schema/schema)))
(spec/def ::responses (spec/map-of keyword? (spec/get-spec :oas.specs.oas.response/response)))
(spec/def ::parameters (spec/map-of keyword? (spec/get-spec :oas.specs.oas.parameter/parameter)))
(spec/def ::examples (spec/map-of keyword? (spec/get-spec :oas.specs.oas.example/example)))
(spec/def ::requestBodies (spec/map-of keyword? (spec/get-spec :oas.specs.oas.requestBody/requestBody)))
(spec/def ::headers (spec/map-of keyword? (spec/get-spec :oas.specs.oas.header/header)))
(spec/def ::securitySchemes (spec/map-of keyword? (spec/get-spec :oas.specs.oas.securityScheme/securityScheme)))
(spec/def ::links (spec/map-of keyword? (spec/get-spec :oas.specs.oas.link/link)))
(spec/def ::callbacks (spec/map-of keyword? string?)) ;;fix me

(spec/def ::components (spec/keys :req-un []
                                  :opt-un [::schemas ::responses ::parameters
                                           ::examples ::requestBodies 
                                           ::headers ::securitySchemes 
                                           ::links ::callbacks]))
