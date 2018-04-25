(ns oas.specs.swagger.contact 
  "A representation of a swagger contact."
  (:require [clojure.spec.alpha :as spec]
            [oas.predicates :as p]))

(spec/def ::name string?)
(spec/def ::url (spec/and string? p/url?)) ;; url
(spec/def ::email (spec/and string? p/email?)) ;; email

(spec/def ::contact (spec/keys :req-un []
                               :opt-un [::name ::url ::email]))

