(ns oas.specs.oas.schema
  (:require [clojure.spec.alpha :as spec] [oas.specs.oas.discriminator]
            [oas.specs.oas.xml] [oas.specs.oas.externalDocs]
            [oas.specs.oas.example]))

(spec/def ::title string?)
(spec/def ::multipleOf number?)
(spec/def ::maximum number?)
(spec/def ::exclusiveMaximum number?)
(spec/def ::minimum number?)
(spec/def ::exculsiveMinimum number?)
(spec/def ::maxLength number?)
(spec/def ::minLength number?)
(spec/def ::pattern string?) ;; fix me. Should be a regex.
(spec/def ::maxItems number?)
(spec/def ::minItems number?)
(spec/def ::uniqueItems (spec/or :true true? :false false?))
(spec/def ::maxProperties integer?)
(spec/def ::minProperties integer?)
(spec/def ::required (spec/coll-of string?))
(spec/def ::enum (spec/coll-of string?))
(spec/def ::type string?)
(spec/def ::allOf (spec/get-spec ::schema))
(spec/def ::oneOf (spec/get-spec ::schema))
(spec/def ::anyOf (spec/get-spec ::schema))
(spec/def ::not (spec/get-spec ::schema))
(spec/def ::items (spec/get-spec ::schema))
(spec/def ::properties (spec/get-spec ::schema))
(spec/def ::additionalProperties (spec/or :true true? :false false? :schema (spec/get-spec ::schema)))
(spec/def ::description string?)
(spec/def ::format string?) ;; fixme?
(spec/def ::default string?) ;; fixme?
(spec/def ::nullable (spec/or :true true? :false false?))
(spec/def ::discriminator (spec/get-spec :oas.specs.oas.discriminator/discriminator))
(spec/def ::readOnly (spec/or :true true? :false false?))
(spec/def ::writeOnly (spec/or :true true? :false false?))
(spec/def ::xml (spec/get-spec :oas.specs.oas.xml/xml))
(spec/def ::externalDocs (spec/get-spec :oas.specs.oas.externalDocs/externalDocs))
(spec/def ::example (spec/get-spec :oas.specs.oas.example/example))
(spec/def ::deprecated (spec/or :true true? :false false?))

(spec/def ::schema (spec/keys :req-un []
                              :opt-un [::title ::multipleOf ::maximum
                                       ::exclusiveMaximum ::minimum
                                       ::exclusiveMinimum ::maxLength
                                       ::minLength ::pattern
                                       ::maxItems ::minItems
                                       ::uniqueItems ::maxProperties
                                       ::minProperties ::required
                                       ::enum ::type ::allOf ::oneOf
                                       ::anyOf ::not ::items ::properties
                                       ::additionalProperties 
                                       ::description ::format
                                       ::default ::nullable ::discriminator
                                       ::readOnly ::writeOnly ::xml
                                       ::externalDocs ::example ::deprecated]))
