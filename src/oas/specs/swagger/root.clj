(ns oas.specs.swagger.root 
  "The root of a swagger document."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.info :refer :all]
            [oas.specs.swagger.paths :refer :all]
            [oas.specs.swagger.primitives :refer :all]
            [oas.specs.swagger.definitions :refer :all]
            [oas.specs.swagger.parameters :refer :all]
            [oas.specs.swagger.responses :refer :all]
            [oas.specs.swagger.securityDefinitions :refer :all]
            [oas.specs.swagger.security :refer :all]
            [oas.specs.swagger.tags :refer :all]
            [oas.specs.swagger.externalDocs :refer :all]))

(spec/def ::root (spec/keys :req-un [::swagger ::info ::paths]
                            :opt-un [::host ::basePath ::schemes ::consumes 
                                     ::produces ::definitions ::parameters 
                                     ::responses ::securityDefinitions ::security 
                                     ::tags ::externalDocs]))
