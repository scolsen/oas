(ns oas.specs.oas.flows
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::implicit (spec/get-spec :oas.specs.oas.flow/flow))
(spec/def ::password (spec/get-spec :oas.specs.oas.flow/flow))
(spec/def ::clientCredentials (spec/get-spec :oas.specs.oas.flow/flow))
(spec/def ::authorizationCode (spec/get-spec :oas.specs.oas.flow/flow))

(spec/def ::flows (spec/keys :req-un []
                             :opt-un [::implicit ::password
                                      ::clientCredentials 
                                      ::authorizationCode]))
