(ns oas.specs.oas.info 
  (:require [clojure.spec.alpha :as spec]
            [oas.predicates :as p]))

(spec/def ::title string?)
(spec/def ::description string?)
(spec/def ::termsOfService (spec/and string? p/url?))
(spec/def ::contact (spec/get-spec :oas.specs.oas.contact/contact))
(spec/def ::license (spec/get-spec :oas.specs.oas.license/license))
(spec/def ::version (spec/and string? p/openapi-version?))

(spec/def ::info (spec/keys :req-un [::title ::version]
                            :opt-un [::description ::termsOfService ::contact
                                     ::license]))
