(ns oas.specs.oas.operation
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]
            [oas.specs.oas.responses]
            [oas.specs.oas.externalDocs] [oas.specs.oas.parameter]
            [oas.specs.oas.reference] [oas.specs.oas.requestBody]
            [oas.specs.oas.securityRequirement]
            [oas.specs.oas.server]))

(spec/def ::callback string?) ;; fix me. Callback is a cyclic reference.
(spec/def ::responses (spec/get-spec :oas.specs.oas.responses/responses))
(spec/def ::tags (spec/coll-of string?))
(spec/def ::summary string?)
(spec/def ::description string?)
(spec/def ::externalDocs (spec/get-spec :oas.specs.oas.externalDocs/externalDocs))
(spec/def ::operationId string?)
(spec/def ::parameters (spec/coll-of 
  (spec/or :parameter (spec/get-spec :oas.specs.oas.parameter/parameter) 
    :reference (spec/get-spec :oas.specs.oas.reference/reference))))
(spec/def ::requestBody 
  (spec/or :requestBody (spec/get-spec :oas.specs.oas.requestBody/requestBody) 
           :reference (spec/get-spec :oas.specs.oas.reference/reference)))
(spec/def ::callbacks (spec/map-of keyword? 
  (spec/or :callback ::callback 
           :reference (spec/get-spec :oas.specs.oas.reference/reference))))
(spec/def ::deprecated (spec/or :true true? :false false?))
(spec/def ::security (spec/coll-of 
  (spec/get-spec :oas.specs.oas.securityRequirement/securityRequirement)))
(spec/def ::servers (spec/coll-of (spec/get-spec :oas.specs.oas.server/server)))

(spec/def ::operation (spec/keys :req-un [::responses]
                                 :opt-un [::tags ::summary ::description
                                          ::externalDocs ::operationId 
                                          ::parameters ::requestBody 
                                          ::callbacks ::deprecated ::security
                                          ::servers]))
