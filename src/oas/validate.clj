(ns oas.validate
  "Validate the contents of an OAS document or part."
  (:require [clojure.set :as S]))

;;; specification {:info true :servers false ...}
;;; A map of key names and booleans. A boolean indicates whether the key is
;;; required.

(defn requirements [spec] 
  "Get the required fields of a spec."
  (keys (filter #(= true (val %)) spec)))

(defn missing-reqs [part spec]
  "Return the set of missing required keys."
  (S/difference (set (requirements spec)) (set (keys part))))

(defn unknowns [part spec]
  "Return the set of unrecognized keys."
  (S/difference (set (keys part)) (set (keys spec))))

(defn requirements-met? [part spec] 
  "Returns a boolean indicating whether all required fields are present."
  (S/subset? (set (keys (requirements spec))) (set (keys part))))

(defn valid? [part spec]
  "Validate an OAS document part. 
   A part is valid iff the set of missing and set of unknowns are empty."
  (and (empty? (missing-reqs part spec)) (empty? (unknowns part spec)))) 
