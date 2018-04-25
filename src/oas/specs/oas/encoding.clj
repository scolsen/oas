(ns oas.specs.oas.encoding 
  (:require [clojure.spec.alpha :as spec] [oas.specs.oas.header]
            [oas.specs.oas.reference]))

(spec/def ::contentType string?)
(spec/def ::headers (spec/map-of keyword? (spec/or :header (spec/get-spec :oas.specs.oas.header/header) 
  :reference (spec/get-spec :oas.specs.oas.reference/reference))))
(spec/def ::style string?)
(spec/def ::explode (spec/or :true true? :false false?))
(spec/def ::allowReserved (spec/or :true true? :false false?))

(spec/def ::encoding (spec/keys :req-un []
                                :opt-un [::contentType ::headers ::style
                                         ::explode ::allowReserved]))
