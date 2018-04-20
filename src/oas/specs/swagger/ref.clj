(ns oas.specs.swagger.ref 
  ""
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::$ref string?) ;; must be a ref string

(spec/def ::ref (spec/keys :req-un [::$ref]
                           :opt-un []))
