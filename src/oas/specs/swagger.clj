(ns oas.specs.swagger
  "A representation of the OAS specification."
  (:require [clojure.spec.alpha :as s]))

(import 'org.apache.commons.validator.routines.UrlValidator)
(import 'org.apache.commons.validator.routines.EmailValidator)

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
(s/def ::paths map?)
(s/def ::$ref string?)
(s/def ::propertyName string?)
(s/def ::mapping (s/map-of string? string?))
(s/def ::definitions map?)
(s/def ::flow string?)

(s/def ::base (s/keys :req-un [::swagger ::info ::paths]
                         :opt-un [::host ::basePath ::schemes ::consumes ::produces ::definitions ::parameters ::responses ::securityDefinitions ::security ::tags ::externalDocs]))

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
                      :opt-un [::$ref ::get ::put ::post ::delete ::options ::head ::patch ::parameters]))

(s/def ::operation (s/keys :req-un [::responses]
                           :opt-un [::tags ::summary ::description ::externalDocs ::operationId ::consumes ::produces ::responses ::schemes ::parameters ::deprecated ::security ]))

(s/def ::externalDocs (s/keys :req-un [::url]
                              :opt-un [::description]))

(s/def ::parameter (s/keys :req-un [::name ::in]
                           :opt-un [::description ::required ::allowEmptyValue ::schema ::type ::format ::items ::collectionFormat ::default ::maximum ::exclusiveMaximum ::minimum ::exclusiveMinimum ::maxLength ::minLength ::pattern ::maxItems ::minItems ::uniqueItems ::enum ::multipleOf]))

(s/def ::items (s/keys :req-un [::type]
                       :opt-un [::items ::format ::collectionFormat ::default ::maximum ::exclusiveMaximum ::minimum ::exclusiveMinimum ::maxLength ::minLength ::pattern ::maxItems ::minItems ::uniqueItems ::enum ::multipleOf]))

(s/def ::responses (s/keys :req-un []
                           :opt-un [::default]))

(s/def ::response (s/keys :req-un [::description]
                          :opt-un [::headers ::schema ::examples]))

(s/def ::header (s/keys :req-un [::type]
                        :opt-un [::description ::format ::items ::collectionFormat ::default ::maximum ::exclusiveMaximum ::minimum ::exclusiveMinimum ::maxLength ::minLength ::pattern ::maxItems ::minItems ::uniqueItems ::enum ::multipleOf]))

(s/def ::tag (s/keys :req-un [::name]
                     :opt-un [::description ::externalDocs]))
(s/def ::schema (s/keys :req-un []
                        :opt-un [::nullable ::discriminator ::readOnly ::writeOnly ::xml ::externalDocs ::example ::deprecated]))

(s/def ::discriminator (s/keys :req-un [::propertyName]
                               :opt-un [::mapping]))

(s/def ::xml (s/keys :req-un []
                     :opt-un [::name ::namespace ::prefix ::attribute ::wrapped]))

(s/def ::securityScheme (s/keys :req-un [::type ::name ::in ::flow ::authorizationUrl ::tokenUrl ::scopes ]
                                :opt-un [::description]))
