(ns oas.specs.oas.contact
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]))

(spec/def ::name string?)
(spec/def ::url (spec/and string? p/url?))
(spec/def ::email (spec/and string? p/email?))

(spec/def ::contact (spec/keys :req-un []
                               :opt-un [::name ::url ::email]))
