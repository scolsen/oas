(ns oas.specs.swagger.path 
  "Path"
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.operation]
            [oas.specs.swagger.parameters]))

(spec/def ::get (spec/get-spec :oas.specs.swagger.operation/operation))
(spec/def ::put (spec/get-spec :oas.specs.swagger.operation/operation))
(spec/def ::post (spec/get-spec :oas.specs.swagger.operation/operation))
(spec/def ::delete (spec/get-spec :oas.specs.swagger.operation/operation))
(spec/def ::options (spec/get-spec :oas.specs.swagger.operation/operation))
(spec/def ::head (spec/get-spec :oas.specs.swagger.operation/operation))
(spec/def ::patch (spec/get-spec :oas.specs.swagger.operation/operation))
(spec/def ::head (spec/get-spec :oas.specs.swagger.operation/operation))
(spec/def ::$ref string?)
(spec/def ::parameters (spec/get-spec :oas.specs.swagger.parameters/parameters))

(spec/def ::path (spec/keys :req-un []
                            :opt-un [::$ref ::get ::put ::post ::delete ::options 
                                     ::head ::patch ::parameters]))
