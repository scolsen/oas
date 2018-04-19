(ns oas.specs.swagger.primitives 
  "Primitive swagger values."
  (:require [clojure.spec.alpha :as spec])
  (:import [org.apache.commons.validator.routines UrlValidator EmailValidator]))

(defn url? [x] 
  "Predicate whether or not a string is a valid URL."
  (.isValid (UrlValidator.) x))

(defn extension? [x]
  "Validate whether a key is an extension."
  (not (nil? (re-matches #"\^x-.*" x))))

(defn email? [x] 
  "Validate an email address value."
  (.isValid (EmailValidator/getInstance) x))

(spec/def ::swagger string?)
(spec/def ::description string?)
(spec/def ::termsOfService string?)
(spec/def ::title string?)
(spec/def ::version string?)
(spec/def ::name string?)
(spec/def ::email (spec/and string? email?))
(spec/def ::url (spec/and string? url?))
(spec/def ::host string?)
(spec/def ::basePath string?)
(spec/def ::schemes (spec/coll-of string?)) ;; must be one of four types.
(spec/def ::consumes (spec/coll-of string?)) ;; must be one of mime types.
(spec/def ::produces (spec/coll-of string?))
(spec/def ::pathString string?) ;;must begin with /
(spec/def ::$ref string?) ;; must be a path item object
(spec/def ::internalTags (spec/coll-of string?))
(spec/def ::summary string?)
(spec/def ::deprecated (spec/or true? false?))
(spec/def ::type string?)
(spec/def ::format string?)
(spec/def ::collectionFormat string?) ;; set list of values
