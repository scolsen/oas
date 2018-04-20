(ns oas.specs.swagger.jsonSchema 
  ""
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::maximum number?)
(spec/def ::exclusiveMaximum (spec/or :true true? :false false?))
(spec/def ::minimum number?)
(spec/def ::exclusiveMinimum (spec/or :true true? :false false?))
(spec/def ::maxLength integer?)
(spec/def ::minLength integer?)
(spec/def ::pattern string?)
(spec/def ::maxItems integer?)
(spec/def ::minItems integer?)
(spec/def ::uniqueItems (spec/or :true true? :false false?))
(spec/def ::enum vector?)
(spec/def ::multipleOf number?)

(spec/def ::jsonSchema (spec/keys :req-un []
                                  :opt-un [::maximum 
                                           ::exclusiveMaximum ::minimum 
                                           ::exclusiveMinimum ::maxLength 
                                           ::minLength ::pattern ::maxItems 
                                           ::minItems ::uniqueItems ::enum 
                                           ::multipleOf]))
