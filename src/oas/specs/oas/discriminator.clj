(ns oas.specs.oas.discriminator 
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::propertyName string?)
(spec/def ::mapping (spec/map-of keyword? string?))

(spec/def ::discriminator (spec/keys :req-un [::propertyName]
                                     :opt-un [::mapping]))
