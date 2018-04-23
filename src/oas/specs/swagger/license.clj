(ns oas.specs.swagger.license
  "License."
  (:require [clojure.spec.alpha :as spec])
  (:import [org.apache.commons.validator.routines UrlValidator EmailValidator]))

(defn url? [x] 
  "Predicate whether or not a string is a valid URL."
  (.isValid (UrlValidator.) x))

(spec/def ::name string?)
(spec/def ::url (spec/and string? url?))

(spec/def ::license (spec/keys :req-un [::name]
                               :opt-un [::url]))

