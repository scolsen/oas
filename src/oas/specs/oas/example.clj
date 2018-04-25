(ns oas.specs.oas.example 
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]))

(spec/def ::summary string?)
(spec/def ::description string?)
(spec/def ::value string?)
(spec/def ::externalValue (spec/and string? p/url?))

(spec/def ::exmaple (spec/keys :req-un []
                               :opt-un [::summary ::description ::value
                                        ::externalValue]))
