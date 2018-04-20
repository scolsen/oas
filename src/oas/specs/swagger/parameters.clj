(ns oas.specs.swagger.parameters 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.parameter]
            [oas.specs.swagger.ref]))

(spec/def ::parameters (spec/coll-of (spec/or :parameter (spec/get-spec :oas.specs.swagger.parameter/parameter) :ref (spec/get-spec :oas.specs.swagger.ref/ref))))
