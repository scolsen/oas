(ns oas.validate
  "Validate the contents of an OAS document or part."
  (:require [clojure.set :as S][oas.specs.swagger.root]
            [oas.specs.oas :as oas][clojure.spec.alpha :as s]))

(defn- examine-spec [f]
  (fn 
    ([api spec] 
     (f (keyword (str "oas.specs." spec ".root") "root") api))
    ([api spec object] 
     (f (keyword (str "oas.specs." spec ".root") object) api))))

(def valid? (examine-spec s/valid?))
(def conform (examine-spec s/conform))
(def explain (examine-spec s/explain))
