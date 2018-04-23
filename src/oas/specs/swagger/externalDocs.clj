(ns oas.specs.swagger.externalDocs 
  "ExternalDocs"
  (:require [clojure.spec.alpha :as spec])
  (:import [org.apache.commons.validator.routines UrlValidator]))

(defn url? [x] 
  "Predicate whether or not a string is a valid URL."
  (.isValid (UrlValidator.) x))

(spec/def ::description string?)
(spec/def ::url (spec/and string? url?))

(spec/def ::externalDocs (spec/keys :req-un [::url]
                                    :opt-un [::description]))
