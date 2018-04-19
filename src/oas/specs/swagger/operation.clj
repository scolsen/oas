(ns oas.specs.swagger.operation 
  "Operations"
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.primitives :refer :all]
            [oas.specs.swagger.parameters :refer :all]
            [oas.specs.swagger.responses :refer :all]
            [oas.specs.swagger.security :refer :all]))

(spec/def ::operationId string?)

(spec/def ::operation (spec/keys :req-un [::responses]
                                 :opt-un [::tags ::summary ::description 
                                          ::externalDocs ::operationId ::consumes 
                                          ::produces ::schemes 
                                          ::parameters ::deprecated ::security]))

