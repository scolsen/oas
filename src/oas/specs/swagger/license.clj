(ns oas.specs.swagger.license
  "License."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.primitives :refer :all]))

(spec/def ::license (spec/keys :req-un [::name]
                               :opt-un [::url]))

