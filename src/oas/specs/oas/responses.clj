(ns oas.specs.oas.responses 
  (:require [clojure.spec.alpha :as spec] [oas.predicates :as p]
            [oas.specs.oas.repsonse] [oas.specs.oas.reference]))

(spec/def (spec/map-of p/http-code? 
  (spec/or :response (spec/get-spec :oas.specs.oas.response/response) 
           :reference (spec/get-spec :oas.specs.oas.reference/reference))))
