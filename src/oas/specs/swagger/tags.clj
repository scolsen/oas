(ns oas.specs.swagger.tags 
  ""
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.tag :refer :all]))

(spec/def ::tags (spec/coll-of  (spec/get-spec ::tag)))
