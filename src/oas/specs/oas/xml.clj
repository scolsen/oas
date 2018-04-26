(ns oas.specs.oas.xml 
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::name string?)
(spec/def ::namespace string?)
(spec/def ::prefix string?)
(spec/def ::attribute (spec/or :true true? :false false?))
(spec/def ::wrapped (spec/or :true true? :false false?))

(spec/def ::xml (spec/keys :req-un []
                           :opt-un [::name ::namespace ::prefix
                                    ::attribute ::wrapped]))
