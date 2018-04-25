(ns oas.specs.oas.root 
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.oas.info] [oas.specs.oas.paths]
            [oas.specs.oas.server] [oas.specs.oas.components]
            [oas.specs.oas.securityRequirement] [oas.specs.oas.tag]
            [oas.specs.oas.externalDocs]))

(spec/def ::openapi string?)
(spec/def ::info (spec/get-spec :oas.specs.oas.info/info))
(spec/def ::paths (spec/get-spec :oas.specs.oas.paths/paths))
(spec/def ::servers (spec/coll-of (spec/get-spec :oas.specs.oas.server/server)))
(spec/def ::components (spec/get-spec :oas.specs.oas.components/components))
(spec/def ::securityRequirement 
  (spec/coll-of 
    (spec/get-spec :oas.specs.oas.securityRequirement/securityRequirement)))
(spec/def ::tags (spec/coll-of (spec/get-spec :oas.specs.oas.tag/tag)))
(spec/def ::externalDocs (spec/get-spec :oas.specs.oas.externalDocs/externalDocs))

(spec/def ::root (spec/keys :req-un [::openapi ::info ::paths]
                            :opt-un [::servers ::components ::security ::tags 
                                     ::externalDocs]))


