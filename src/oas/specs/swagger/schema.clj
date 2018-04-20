(ns oas.specs.swagger.schema 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.jsonSchema]
            [oas.specs.swagger.ref]))

(spec/def ::ref (spec/get-spec :oas.specs.swagger.ref/ref))
(spec/def ::format string?)
(spec/def ::title string?)
(spec/def ::description string?)
(spec/def ::type string?)
(spec/def ::readOnly (spec/or :true true? :false false?))

(spec/def ::schema (spec/merge :oas.specs.swagger.jsonSchema/jsonSchema 
                               (spec/keys :req-un []
                                          :opt-un [::discriminator ::readOnly
                                                   ::xml ::externalDocs ::example])))


