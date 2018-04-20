(ns oas.specs.swagger.securityScheme 
  "Representation of a securityScheme object."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.primitives :refer :all]
            [oas.specs.swagger.scopes :refer :all]))

(spec/def ::in string?) ;; query or header
(spec/def ::flow string?) ;; implicit password application or accessCode
(spec/def ::authorizationUrl string?) ;; URL
(spec/def ::tokenUrl string?) ;; URL

(spec/def ::securityScheme (spec/keys :req-un [::type ::name ::in 
                                               ::flow ::authorizationUrl 
                                               ::tokenUrl ::scopes]
                                      :opt-un [::description]))
