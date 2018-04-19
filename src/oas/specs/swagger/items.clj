(ns oas.specs.swagger.items 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.jsonSchema :refer :all]
            [oas.specs.swagger.primitives :refer :all]))

(spec/def ::items (spec/merge ::jsonSchema 
                              (spec/keys :req-un [::type]
                                         :opt-un [::items ::format 
                                                  ::collectionFormat 
                                                  ::default])))

