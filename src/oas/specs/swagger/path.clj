(ns oas.specs.swagger.path 
  "Path"
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.primitives :refer :all]
            [oas.specs.swagger.operation :refer :all]
            [oas.specs.swagger.parameters :refer :all]))

(spec/def ::get (spec/get-spec ::operation))
(spec/def ::put (spec/get-spec ::operation))
(spec/def ::post (spec/get-spec ::operation))
(spec/def ::delete (spec/get-spec ::operation))
(spec/def ::options (spec/get-spec ::operation))
(spec/def ::head (spec/get-spec ::operation))
(spec/def ::patch (spec/get-spec ::operation))
(spec/def ::head (spec/get-spec ::operation))

(spec/def ::path (spec/keys :req-un []
                            :opt-un [::$ref ::get ::put ::post ::delete ::options 
                                     ::head ::patch ::parameters]))
