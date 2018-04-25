(ns oas.specs.oas.externalDocs 
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]))

(spec/def ::url (spec/and string? p/url?))
(spec/def ::description string?)

(spec/def ::externalDocs (spec/keys :req-un [::url]
                                    :opt-un [::description]))
