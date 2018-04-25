(ns oas.specs.oas.path 
  (:require [clojure.spec.alpha :as spec] [oas.predciates :as p]
            [oas.specs.oas.parameter] [oas.specs.oas.reference]
            [oas.specs.oas.server] [oas.specs.oas.operation]))

(spec/def ::$ref (spec/and string? p/ref?))
(spec/def ::summary string?)
(spec/def ::description string?)
(spec/def ::get (spec/get-spec :oas.specs.oas.operation/operation))
(spec/def ::put (spec/get-spec :oas.specs.oas.operation/operation))
(spec/def ::post (spec/get-spec :oas.specs.oas.operation/operation))
(spec/def ::delete (spec/get-spec :oas.specs.oas.operation/operation))
(spec/def ::options (spec/get-spec :oas.specs.oas.operation/operation))
(spec/def ::head (spec/get-spec :oas.specs.oas.operation/operation))
(spec/def ::patch (spec/get-spec :oas.specs.oas.operation/operation))
(spec/def ::trace (spec/get-spec :oas.specs.oas.operation/operation))
(spec/def ::servers (spec/coll-of (spec/get-spec :oas.specs.oas.server/server)))
(spec/def ::parameters 
  (spec/coll-of (spec/or 
    :parameter (spec/get-spec :oas.specs.oas.parameter/parameter) 
    :reference (spec/get-spec :oas.specs.oas.reference/reference))))

(spec/def ::path (spec/keys :req-un []
                            :opt-un [::$ref ::summary ::description ::get 
                                     ::put ::post ::delete ::options ::head
                                     ::patch ::trace ::servers ::parameters]))
