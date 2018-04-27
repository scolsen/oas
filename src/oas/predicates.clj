(ns oas.predicates
  (:require [oas.data :as d])
  (:import [org.apache.commons.validator.routines UrlValidator EmailValidator]))

(defn url? [x]
  (.isValid (UrlValidator.) x))

(defn email? [x] 
  "Validate an email address value."
  (.isValid (EmailValidator/getInstance) x))

(defn openapi-version? [x]
  (re-matches #"\d\.\d\.\d" x))

(defn http-code? [x]
  (some #(= x %) d/codes))

(defn ref? [x]
  (= :$ref x))

(defn expression? [x] ;; TODO: Implement runtime expressions.
  true)

