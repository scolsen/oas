(ns oas.specs.swagger.schema 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.jsonSchema]
            [oas.specs.swagger.ref]
            [oas.specs.swagger.externalDocs]
            [oas.specs.swagger.example]))

(spec/def ::$ref (spec/get-spec :oas.specs.swagger.ref/ref))
(spec/def ::format string?)
(spec/def ::title string?)
(spec/def ::description string?)
(spec/def ::type string?)
(spec/def ::readOnly (spec/or :true true? :false false?))
(spec/def ::discriminator string?)
(spec/def ::xml (spec/get-spec :oas.specs.swagger.xml/xml))
(spec/def ::externalDocs (spec/get-spec :oas.specs.swagger.externalDocs/externalDocs))
(spec/def ::example (spec/get-spec :oas.specs.swagger.example/example))

(spec/def ::schema (spec/merge :oas.specs.swagger.jsonSchema/jsonSchema 
                               (spec/keys :req-un []
                                          :opt-un [::discriminator ::readOnly
                                                   ::xml ::externalDocs ::example])))


