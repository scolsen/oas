(ns oas.specs.swagger.security 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.securityRequirement]))

(spec/def ::security (spec/coll-of :oas.specs.swagger.secruityRequirement/securityRequirement))
