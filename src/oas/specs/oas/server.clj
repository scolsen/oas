(ns oas.specs.oas.server 
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]
            [oas.specs.oas.serverVariable]))

(spec/def ::url (spec/and string? p/url?))
(spec/def ::description string?)
(spec/def ::variables (spec/map-of keyword? (spec/get-spec :oas.specs.oas.serverVariable/serverVariable)))

(spec/def ::server (spec/keys :req-un [::url]
                              :opt-un [::description ::variables]))
