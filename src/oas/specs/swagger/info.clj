(ns oas.specs.swagger.info 
  "A representation of the swagger info object."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.primitives :refer ::all]
            [oas.specs.swagger.contact :refer :all]
            [oas.specs.swagger.license :refer :all]))

(spec/def ::info (spec/keys :req-un [::title ::version]
                            :opt-un [::description ::termsOfService ::contact
                                     ::license]))
