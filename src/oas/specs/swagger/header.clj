(ns oas.specs.swagger.header 
  ""
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::description string?)
(spec/def ::type string?)
(spec/def ::format string?)
(spec/def ::items (spec/get-spec :oas.specs.swagger.items/items))
(spec/def ::collectionFormat string?) ;:restricted
(spec/def ::jsonSchema (spec/get-spec :oas.specs.swagger.jsonSchema/jsonSchema))

(spec/def ::header (spec/keys :req-un [::items ::type]
                              :opt-un [::jsonSchema ::collectionFormat
                                       ::description ::format]))
