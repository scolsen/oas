(ns oas.specs.oas.securityScheme 
  (:require [clojure.spec.alpha :as spec] [oas.specs.oas.flows]))

(spec/def ::type string?)
(spec/def ::name string?)
(spec/def ::in string?)
(spec/def ::scheme string?)
(spec/def ::flows (spec/get-spec :oas.specs.oas.flows/flows))
(spec/def ::openIdConnectUrl string?)
(spec/def ::description string?)
(spec/def ::bearerFormat string?)

(spec/def ::securityScheme (spec/keys :req-un [::type ::name ::in ::scheme
                                               ::flows ::openIdConnectUrl]
                                      :opt-un [::description ::bearerFormat]))
