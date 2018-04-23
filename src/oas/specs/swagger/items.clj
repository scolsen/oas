(ns oas.specs.swagger.items 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.jsonSchema]))

(defn collectionFormat? [x]
  (or (= "csv" x) (= "ssv" x) (= "tsv" x) (= "pipes" x)))

(spec/def ::type string?)
(spec/def ::format string?)
(spec/def ::collectionFormat (spec/and string? collectionFormat?)) ;; restricted values.
(spec/def ::default (spec/or :string string? :number number? :integer integer? :true true? :false false? :array vector?))

(spec/def ::items (spec/merge :oas.specs.swagger.jsonSchema/jsonSchema
                              (spec/keys :req-un [::type]
                                         :opt-un [::items ::format 
                                                  ::collectionFormat 
                                                  ::default])))

