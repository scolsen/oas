(ns oas.specs.oas.header 
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::required (spec/or :true true? :false false?))
(spec/def ::deprecated (spec/or :true true? :false false?))
(spec/def ::allowEmptyValue (spec/or :true true? :false false?))
(spec/def ::description string?)
(spec/def ::style string?)
(spec/def ::explode (spec/or :true true? :false false?))
(spec/def ::allowReserved (spec/or :true true? :false false?))
(spec/def ::schema (spec/or :schema (spec/get-spec :oas.specs.oas.schema/schema)
  :reference (spec/get-spec :oas.specs.oas.reference/reference)))
(spec/def ::example string?)
(spec/def ::examples (spec/map-of keyword? 
  (spec/or :example (spec/get-spec :oas.specs.oas.example/example) 
    :reference (spec/get-spec :oas.specs.oas.reference/reference))))
(spec/def ::content (spec/map-of keyword? (spec/get-spec :oas.specs.oas.mediaType/mediaType)))

(spec/def ::serialization (spec/keys :req-un []
                                     :opt-un [::style ::explode 
                                              ::allowReserved ::schema
                                              ::example ::examples]))

(spec/def ::header (spec/merge ::serialization 
  (spec/keys :req-un [::required]
             :opt-un [::description ::deprecated 
                      ::allowEmptyValue ::content])))
