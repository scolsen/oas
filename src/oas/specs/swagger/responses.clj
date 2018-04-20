(ns oas.specs.swagger.responses 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.response]
            [oas.specs.swagger.ref]))

(spec/def ::default (spec/or :response (spec/get-spec :oas.specs.swagger.response/response) :ref (spec/get-spec :oas.specs.swagger.ref/ref)))

(spec/def ::responses (spec/keys :req-un [::default]
                                 :opt-un []))
