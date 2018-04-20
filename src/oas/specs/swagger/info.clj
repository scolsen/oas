(ns oas.specs.swagger.info 
  "A representation of the swagger info object."
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.contact]
            [oas.specs.swagger.license]))

(spec/def ::title string?)
(spec/def ::version string?)
(spec/def ::description string?)
(spec/def ::termsOfService string?) 
(spec/def ::contact (spec/get-spec :oas.specs.swagger.contact/contact))
(spec/def ::license (spec/get-spec :oas.specs.swagger.license/license))

(spec/def ::info (spec/keys :req-un [::title ::version]
                            :opt-un [::description ::termsOfService ::contact
                                     ::license]))
