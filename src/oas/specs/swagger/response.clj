(ns oas.specs.swagger.response 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.schema]
            [oas.specs.swagger.headers]
            [oas.specs.swagger.example]))

(spec/def ::description string?)
(spec/def ::schema (spec/get-spec :oas.specs.swagger.schema/schema))
(spec/def ::headers (spec/get-spec :oas.specs.swagger.headers/headers))
(spec/def ::examples (spec/get-spec :oas.specs.swagger.example/example))

(spec/def ::response (spec/keys :req-un [::description]
                                :opt-un [::schema ::headers ::examples]))
