(ns oas.specs.oas.serverVariable
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::enum (spec/coll-of string?))
(spec/def ::default string?)
(spec/def ::description string?)

(spec/def ::serverVariable (spec/keys :req-un [::default]
                                      :opt-un [::enum ::description]))
