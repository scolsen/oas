(ns oas.specs.swagger.parameter 
  "Paramerter"
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.jsonSchema]
            [oas.specs.swagger.schema]
            [oas.specs.swagger.items]))

(spec/def ::name string?)
(spec/def ::in string?)
(spec/def ::description string?)
(spec/def ::required (spec/or :true true? :false false?))
(spec/def ::allowEmptyValue (spec/or :true true? :false false?))
(spec/def ::schema (spec/get-spec :oas.specs.swagger.schema/schema))
(spec/def ::type string?) ;; restricted vals
(spec/def ::format string?) ;; restricted vals
(spec/def ::items (spec/get-spec :oas.specs.swagger.items/items))
(spec/def ::collectionFormat string?) ;; restricted vals
(spec/def ::default (spec/or :string string? :number number? :integer integer? :true true? :false false? :array vector?)) ;; add file, file?

(spec/def ::parameter (spec/merge :oas.specs.swagger.jsonSchema/jsonSchema
                            (spec/keys :req-un [::name ::in]
                                       :opt-un [::description ::required 
                                                ::allowEmptyValue ::schema 
                                                ::type ::format ::items 
                                                ::collectionFormat ::default])))
