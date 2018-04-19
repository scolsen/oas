(ns oas.specs.swagger
  "A representation of the OAS specification."
  (:require [clojure.spec.alpha :as s]))

;; Predicates

(s/def ::swagger string?)
(s/def ::default string?)
(s/def ::enum (s/coll-of string?))
(s/def ::paths map?)
(s/def ::$ref string?)
(s/def ::propertyName string?)
(s/def ::mapping (s/map-of string? string?))
(s/def ::definitions map?)
(s/def ::flow string?)
(s/def ::basePath string?)
(s/def ::host string?)
(s/def ::consumes string?)
(s/def ::produces string?)
(s/def ::parameters string?)
(s/def ::requestBodies string?)
(s/def ::deprecated (s/or true? false?))
(s/def ::variables string?)

;redefine
(s/def ::securityDefinitions string?)
(s/def ::security string?)




(s/def ::variable (s/keys :req-un [::default]
                          :opt-un [::enum ::description]))

(s/def ::components (s/keys :req-un []
                             :opt-un [::schemas ::responses ::parameters 
                                      ::examples ::requestBodies ::headers 
                                      ::securitySchemes ::links ::callbacks]))






(s/def ::responses (s/keys :req-un []
                           :opt-un [::default]))

(s/def ::response (s/keys :req-un [::description]
                          :opt-un [::headers ::schema ::examples]))

(s/def ::header (s/merge ::jsonSchema (s/keys :req-un [::type]
                                              :opt-un [::description ::format 
                                                       ::items 
                                                       ::collectionFormat 
                                                       ::default])))

(s/def ::tag (s/keys :req-un [::name]
                     :opt-un [::description ::externalDocs]))

(s/def ::schema (s/keys :req-un []
                        :opt-un [::nullable ::discriminator ::readOnly 
                                 ::writeOnly ::xml ::externalDocs ::example 
                                 ::deprecated]))

(s/def ::discriminator (s/keys :req-un [::propertyName]
                               :opt-un [::mapping]))

(s/def ::xml (s/keys :req-un []
                     :opt-un [::name ::namespace ::prefix ::attribute ::wrapped]))

(s/def ::securityScheme (s/keys :req-un [::type ::name ::in ::flow 
                                         ::authorizationUrl ::tokenUrl ::scopes]
                                :opt-un [::description]))
