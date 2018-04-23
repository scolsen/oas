(ns oas.specs.swagger.root 
  "The root of a swagger document."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.info]
            [oas.specs.swagger.paths]
            [oas.specs.swagger.primitives]
            [oas.specs.swagger.definitions]
            [oas.specs.swagger.parameters]
            [oas.specs.swagger.responses]
            [oas.specs.swagger.securityDefinitions]
            [oas.specs.swagger.securityRequirement]
            [oas.specs.swagger.tags]
            [oas.specs.swagger.externalDocs]))

(spec/def ::swagger string?)
(spec/def ::info (spec/get-spec :oas.specs.swagger.info/info))
(spec/def ::host string?)
(spec/def ::basePath string?) ;; path 
(spec/def ::schemes (spec/coll-of string?))
(spec/def ::consumes (spec/coll-of string?))
(spec/def ::produces (spec/coll-of string?))
(spec/def ::paths (spec/get-spec :oas.specs.swagger.paths/paths))
(spec/def ::definitions (spec/get-spec :oas.specs.swagger.definitions/definitions))
(spec/def ::parameters (spec/get-spec :oas.specs.swagger.parameters/parameters))
(spec/def ::responses (spec/get-spec :oas.specs.swagger.responses/responses))
(spec/def ::securityDefinitions (spec/get-spec :oas.specs.swagger.securityDefinitions/securityDefinitions))
(spec/def ::security (spec/coll-of :oas.specs.swagger.securityRequirement/securityRequirement))
(spec/def ::tags (spec/get-spec :oas.specs.swagger.tags/tags))
(spec/def ::externalDocs (spec/get-spec :oas.specs.swagger.externalDocs/externalDocs))

(spec/def ::root (spec/keys :req-un [::swagger ::info ::paths]
                            :opt-un [::host ::basePath ::schemes ::consumes 
                                     ::produces ::definitions ::parameters 
                                     ::responses ::securityDefinitions ::security 
                                     ::tags ::externalDocs]))
