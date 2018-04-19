(ns oas.specs.swagger.contact 
  "A representation of a swagger contact."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.primitives :refer :all]))

(spec/def ::contact (spec/keys :req-un []
                               :opt-un [::name ::url ::email]))

