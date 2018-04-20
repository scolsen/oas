(ns oas.specs.swagger.tag 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.externalDocs :refer :all]))

(spec/def ::name string?)
(spec/def ::description string?)

(spec/def ::tag (s/keys :req-un [::name]
                        :opt-un [::description ::externalDocs]))
