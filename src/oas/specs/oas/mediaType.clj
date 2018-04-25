(ns oas.specs.oas.mediaType 
  (:require [clojure.spec.alpha :as spec] [oas.specs.oas.schema]
            [oas.specs.oas.reference] [oas.specs.oas.encoding]))

(spec/def ::schema (spec/or :schema (spec/get-spec :oas.specs.oas.schema/schema)
  :reference (spec/get-spec :oas.specs.oas.reference/reference)))
(spec/def ::example string?)
(spec/def ::examples (spec/map-of keyword? (spec/or :example ::example 
  :reference (spec/get-spec :oas.specs.oas.reference/reference)))
(spec/def ::encoding (spec/map-of keyword? (spec/get-spec :oas.specs.oas.encoding/encoding)))

(spec/def ::mediaType (spec/keys :req-un []
                                 :opt-un [::schema ::example ::examples
                                          ::encoding]))
