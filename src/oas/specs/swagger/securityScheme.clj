(ns oas.specs.swagger.securityScheme 
  "Representation of a securityScheme object."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.scopes])
  (:import [org.apache.commons.validator.routines UrlValidator]))

(defn url? [x] 
  "Predicate whether or not a string is a valid URL."
  (.isValid (UrlValidator.) x))

(defn scheme-type? [x] 
  (or (= "basic" x) (= "apiKey" x) (= "oauth2" x)))

(defn in? [x] 
  (or (= "query" x) (= "header" x)))

(defn flow? [x] 
  (or (= "password" x) (= "implicit" x) (= "application" x) (= "accessCode" x)))

(spec/def ::type (spec/and string? scheme-type?))
(spec/def ::description string?)
(spec/def ::name string?)
(spec/def ::in (spec/and string? in?)) ;; query or header
(spec/def ::flow (spec/and string? flow?)) ;; implicit password application or accessCode
(spec/def ::authorizationUrl (spec/and string? url?)) ;; URL
(spec/def ::tokenUrl (spec/and string? url?)) ;; URL
(spec/def ::scopes (spec/get-spec :oas.specs.swagger.scopes/scopes))

(spec/def ::securityScheme (spec/keys :req-un [::type ::name ::in 
                                               ::flow ::authorizationUrl 
                                               ::tokenUrl ::scopes]
                                      :opt-un [::description]))
