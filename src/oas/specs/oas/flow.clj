(ns oas.specs.oas.flow 
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]))

(spec/def ::authorizationUrl (spec/and string? p/url?))
(spec/def ::tokenUrl (spec/and string? p/url?))
(spec/def ::scopes (spec/map-of keyword? string?))
(spec/def ::refreshUrl (spec/and string? p/url?))

(spec/def ::flow (spec/keys :req-un [::authorizationUrl ::tokenUrl
                                     ::scopes]
                            :opt-un [::refreshUrl]))
