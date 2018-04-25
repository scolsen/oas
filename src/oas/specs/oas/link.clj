(ns oas.specs.oas.link
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]
            [oas.specs.oas.server]))

(spec/def ::operationRef string?)
(spec/def ::operationId string?)
(spec/def ::parameters (spec/or :string string? :expression p/expression?))
(spec/def ::requestBody (spec/or :string string? :expression p/expression?))
(spec/def ::description string?)
(spec/def ::server (spec/get-spec :oas.specs.oas.server/server))

(spec/def ::link (spec/keys :req-un []
                            :opt-un [::operationRef ::operationId 
                                     ::parameters ::requestBody
                                     ::description ::server]))
