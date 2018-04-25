(ns oas.specs.oas.response 
  (:require [clojure.spec.alpha :as spec] [oas.specs.oas.header]
            [oas.specs.oas.link] [oas.specs.oas.reference]))

(spec/def ::description string?)
(spec/def ::headers (spec/map-of keyword? 
  (spec/or :header (spec/get-spec :oas.specs.oas.header/header) 
           :reference (spec/get-spec :oas.specs.oas.reference/reference))))
(spec/def ::content (spec/map-of keyword? 
  (spec/get-spec :oas.specs.oas.mediaType/mediaType)))
(spec/def ::links (spec/map-of keyword? 
  (spec/or :link (spec/get-spec :oas.specs.oas.link/link) 
           :reference (spec/get-spec :oas.specs.oas.reference/reference))))

(spec/def ::response (spec/keys :req-un [::description]
                                :opt-un [::headers ::content ::links]))
