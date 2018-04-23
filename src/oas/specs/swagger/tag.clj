(ns oas.specs.swagger.tag 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.externalDocs]))

(spec/def ::name string?)
(spec/def ::description string?)
(spec/def ::externalDocs (spec/get-spec :oas.specs.swagger.externalDocs/externalDocs))

(spec/def ::tag (spec/keys :req-un [::name]
                           :opt-un [::description ::externalDocs]))
