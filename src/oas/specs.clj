(ns oas.specs 
  "A representation of the OAS specification."
  (:require [clojure.spec.alpha :as s]))

(import 'org.apache.commons.validator.routines.UrlValidator)
(import 'org.apache.commons.validator.routines.EmailValidator)

(defn url? [x] 
  "Predicate whether or not a string is a valid URL."
  (.isValid UrlValidator x))

(defn extension? [x]
  "Validate whether a key is an extension."
  (not (nil? (re-matches #"\^x-.*" x))))

(defn email? [x] 
  "Validate an email address value."
  (.isValid EmailValidator x))

;;;; Primitives

(s/def ::openapi string?)
(s/def ::description string?)
(s/def ::termsOfService (s/and string? url?))
(s/def ::url (s/and string? url?))
(s/def ::name string?)
(s/def ::title string?)
(s/def ::version string?)
(s/def ::email (s/and string? email?))
(s/def ::default string?)
(s/def ::enum (s/coll-of string?))
(s/def ::paths (s/map-of string? map?))

(s/def ::oas (s/keys :req-un [::openapi ::info ::paths]
                     :opt-un [::servers ::components ::security ::tags ::externalDocs]))
(s/def ::info (s/keys :req-un [::title ::version]
                      :opt-un [::description ::termsOfService ::contact ::license]))
(s/def ::contact (s/keys :req-un []
                         :opt-un [::name ::url ::email]))
(s/def ::license (s/keys :req-un [::name]
                         :opt-un [::url]))
(s/def ::server (s/keys :req-un [::url]
                        :opt-un [::description ::variables]))

(s/def ::variable (s/keys :req-un [::default]
                          :opt-un [::enum ::description]))
(s/def ::componenets (s/keys :req-un []
                             :opt-un [::schemas ::responses ::parameters ::examples ::requestBodies ::headers ::securitySchemes ::links ::callbacks]))

(s/def ::path (s/keys :req-un []
                      :opt-un [::$ref ::summary ::description ::get ::put ::post ::delete ::options ::head ::patch ::trace ::servers ::parameters]))

(s/def ::operation (s/keys :req-un [::responses]
                           :opt-un [::tags ::summary ::description ::externalDocs ::operationId ::parameters ::requestBody ::callbacks ::deprecated ::security ::servers]))

(s/def ::externalDocs (s/keys :req-un [::url]
                              :opt-un [::description]))


