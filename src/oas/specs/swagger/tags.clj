(ns oas.specs.swagger.tags 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.tag]))

(spec/def ::tags (spec/coll-of  (spec/get-spec :oas.specs.swagger.tag/tag)))
