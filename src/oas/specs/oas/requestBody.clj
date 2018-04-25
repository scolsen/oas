(ns oas.specs.oas.requestBody 
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::content (spec/map-of keyword? (spec/get-spec :oas.specs.oas.mediaType/mediaType)))
(spec/def ::description string?)
(spec/def ::required (spec/or :true true? :false false?))

(spec/def ::requestBody (spec/keys :req-un [::content]
                                   :opt-un [::description ::required]))
