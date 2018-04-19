(ns oas.specs.oas 
  "A representation of the OAS specification."
  (:require [clojure.spec.alpha :as s])
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
(s/def ::$ref string?)
(s/def ::propertyName string?)
(s/def ::mapping (s/map-of string? string?))
(s/def ::tags (s/coll-of map?))
; oas
(s/def ::base (s/keys :req-un [::openapi ::info ::paths]
                      :opt-un [::servers ::components 
                               ::security ::tags ::externalDocs]))

(s/def ::info (s/keys :req-un [::title ::version]
                      :opt-un [::description ::termsOfService 
                               ::contact ::license]))

(s/def ::contact (s/keys :req-un []
                         :opt-un [::name ::url ::email]))

(s/def ::license (s/keys :req-un [::name]
                         :opt-un [::url]))

(s/def ::server (s/keys :req-un [::url]
                        :opt-un [::description ::variables]))

(s/def ::variable (s/keys :req-un [::default]
                          :opt-un [::enum ::description]))
(s/def ::components (s/keys :req-un []
                            :opt-un [::schemas ::responses ::parameters 
                                      ::examples ::requestBodies ::headers 
                                      ::securitySchemes ::links ::callbacks]))

(s/def ::path (s/keys :req-un []
                      :opt-un [::$ref ::summary ::description ::get ::put 
                               ::post ::delete ::options ::head ::patch 
                               ::trace ::servers ::parameters]))

(s/def ::operation (s/keys :req-un [::responses]
                           :opt-un [::tags ::summary ::description 
                                    ::externalDocs ::operationId ::parameters 
                                    ::requestBody ::callbacks ::deprecated 
                                    ::security ::servers]))

(s/def ::externalDocs (s/keys :req-un [::url]
                              :opt-un [::description]))

(s/def ::parameter (s/keys :req-un [::name ::in]
                           :opt-un [::description ::required ::deprecated 
                                    ::allowEmptyValue ::style ::explode 
                                    ::allowReserved ::schema ::example 
                                    ::examples ::content]))

(s/def ::requestBody (s/keys :req-un [::content]
                             :opt-un [::description ::required]))

(s/def ::mediaType (s/keys :req-un []
                           :opt-un [::schema ::example ::examples ::encoding]))

(s/def ::encoding (s/keys :req-un []
                          :opt-un [::contentType ::headers ::style 
                                   ::explode ::allowReserved]))

(s/def ::responses (s/keys :req-un []
                           :opt-un [::default]))

(s/def ::response (s/keys :req-un [::description]
                          :opt-un [::headers ::content ::links]))

(s/def ::callback (s/map-of string? map?))

(s/def ::link (s/keys :req-un []
                      :opt-un [::operationRef ::operationId ::parameters 
                               ::requestBody ::description ::server]))

(s/def ::header (s/keys :req-un []
                        :opt-un [::description ::required ::deprecated 
                                 ::allowEmptyValue ::style ::explode 
                                 ::allowReserved ::schema ::example ::examples 
                                 ::content]))

(s/def ::tag (s/keys :req-un [::name]
                     :opt-un [::description ::externalDocs]))
(s/def ::schema (s/keys :req-un []
                        :opt-un [::nullable ::discriminator ::readOnly 
                                 ::writeOnly ::xml ::externalDocs ::example 
                                 ::deprecated]))

(s/def ::discriminator (s/keys :req-un [::propertyName]
                               :opt-un [::mapping]))


(s/def ::xml (s/keys :req-un []
                     :opt-un [::name ::namespace ::prefix ::attribute 
                              ::wrapped]))

(s/def ::securityScheme (s/keys :req-un [::type ::name ::in ::scheme ::flows ::openIdConnectUrl]
                                :opt-un [::description ::bearerFormat]))

(s/def ::flows (s/keys :req-un []
                       :opt-un [::implicit ::password ::clientCredentials ::authorizationCode]))

(s/def ::oauthFlow (s/keys :req-un [::authorizationUrl ::tokenUrl ::scopes]
                           :opt-un [::refreshUrl]))

(s/def ::securityReuirement (s/keys :req-un []
                                    :opt-un []))
