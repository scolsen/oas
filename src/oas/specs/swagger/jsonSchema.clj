(ns oas.specs.swagger.jsonSchema 
  ""
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::maximum number?)
(spec/def ::exclusiveMaximum (spec/or true? false?))

(spec/def ::jsonSchema (spec/keys :req-un []
                                  :opt-un [::maximum 
                                           ::exclusiveMaximum ::minimum 
                                           ::exclusiveMinimum ::maxLength 
                                           ::minLength ::pattern ::maxItems 
                                           ::minItems ::uniqueItems ::enum 
                                           ::multipleOf]))
