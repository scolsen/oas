(ns oas.specs.swagger.contact 
  "A representation of a swagger contact."
  (:require [clojure.spec.alpha :as spec])
  (:import [org.apache.commons.validator.routines UrlValidator EmailValidator]))

(defn url? [x] 
  "Predicate whether or not a string is a valid URL."
  (.isValid (UrlValidator.) x))

(defn email? [x] 
  "Validate an email address value."
  (.isValid (EmailValidator/getInstance) x))

(spec/def ::name string?)
(spec/def ::url (spec/and string? url?)) ;; url
(spec/def ::email (spec/and string? email?)) ;; email

(spec/def ::contact (spec/keys :req-un []
                               :opt-un [::name ::url ::email]))

