(ns oas.validate
  "Validate the contents of an OAS document or part."
  (:require [oas.specs.swagger.root][oas.specs.oas.root]
            [clojure.spec.alpha :as spec] [clojure.string :as s]))

(defn list-specs [term]
  "List the keys in the spec registry that include some word."
  (let [pred #(and (s/includes? (namespace %) term) (keyword? %))]
  (->> (spec/registry)
       (filter pred))))

(defn mark 
  "Mark the places in which a non-conformant json object fails to conform."
  ([sp json]
   (-> (spec/explain-data sp json)
       (get :clojure.spec.alpha/problems)
       (->> (map #(get % :path)))))
  ([sp json tag]))

(defmacro constrain [sp object predicate] 
  "Update a spec with custom constraints."
  (let [k (keyword (str "oas.specs." sp) object)]
       `(spec/def ~k (spec/and (spec/get-spec ~k) ~predicate))))

(def swagger-specs (list-specs "swagger"))
(def oas-specs (list-specs "oas"))

(def valid? spec/valid?)
(def conform spec/conform)
(def explain spec/explain-data)
