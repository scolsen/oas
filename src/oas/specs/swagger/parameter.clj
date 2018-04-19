(ns oas.specs.swagger.parameter 
  "Paramerter"
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.primitives :refer :all]))

(spec/def ::in string?)
(spec/def ::required (spec/or true? false?))

(spec/def ::parameter (spec/merge ::jsonSchema 
                            (spec/keys :req-un [::name ::in]
                                       :opt-un [::description ::required 
                                                ::allowEmptyValue ::schema 
                                                ::type ::format ::items 
                                                ::collectionFormat ::default])))
