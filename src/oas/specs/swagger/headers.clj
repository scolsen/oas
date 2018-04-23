(ns oas.specs.swagger.headers 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.header]))

(spec/def ::headers (spec/map-of keyword? (spec/get-spec :oas.specs.swagger.header/header)))
