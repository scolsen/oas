(ns oas.alter-test 
  "Property tests for the alter module."
  (:require [oas.alter :as alt] [oas.parse :as parse]
            [oas.segment :as segment] [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :as t]
            [clojure.test.check.properties :as prop]
            [oas.specs.oas :as oas] [oas.specs.swagger :as swagger]
            [clojure.spec.alpha :as spec]
            [clojure.spec.test.alpha :as stest]
            [clojure.spec.gen.alpha :as gen]))

(gen/generate (spec/gen ::swagger/base))
