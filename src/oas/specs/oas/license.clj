(ns oas.specs.oas.license 
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]))

(spec/def ::name string?)
(spec/def ::url (spec/and string? p/url?))

(spec/def ::license (spec/keys :req-un [::name]
                               :opt-un [::url]))
