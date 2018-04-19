(ns oas.specs.swaggger.externalDocs 
  "ExternalDocs"
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.primitives :refer :all]))

(spec/def ::externalDocs (spec/keys :req-un [::url]
                                    :opt-un [::description]))
