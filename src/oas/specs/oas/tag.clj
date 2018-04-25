(ns oas.specs.oas.tag 
  (:require [clojure.spec.alpha :as spec] [oas.specs.oas.externalDocs]))

(spec/def ::name string?)
(spec/def ::description string?)
(spec/def ::externalDocs (spec/get-spec :oas.specs.oas.externalDocs))

(spec/def ::tag (spec/keys :req-un [::name]
                           :opt-un [::description ::externalDocs]))
