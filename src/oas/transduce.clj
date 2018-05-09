(ns oas.transduce
  "Modify OAS documents."
  (:require [oas.update :as up]
            [json-pointer.core :as jp]))

(defn transduce-by 
  "Takes a transducer and applies it to a predicate.
   First applies f to the k/v pair of x.
   Creates a structure s to hold results."
  [xf coll & fs]
  (fn ([pred json] 
       (into coll (xf (apply comp pred fs)) json))
      ([pred json pointer] 
       (as-> json j
           (jp/resolve-pointer j pointer)
           (into coll (xf (apply comp pred fs)) j)
           (up/update-value json pointer j)))))

(defn transduce-using 
  "Takes a transduce-by function and a list of things to remove. 
   Removes each x in xs that match the predicate.
   Provides a defualt predicate of = for simple removal by matching.
   You can also use other predicates, such as re-matches. e.g.
   (remove-keys part [#\".*Pet\"] re-matches"
  [t-by]
  (fn
    ([json xs pred]
     (if (empty? xs) json
         (recur (t-by #(pred (first xs) %) json) 
                (rest xs) 
                pred)))
    ([json xs pred pointer] 
     (if (empty? xs) json
         (recur (t-by #(pred (first xs) %) json pointer) 
                (rest xs) 
                pred 
                pointer)))))

(def remove-by (partial transduce-by remove {}))
(def filter-by (partial transduce-by filter {}))

(def remove-by-key (remove-by first))
(def remove-by-value (remove-by second))
(def remove-by-pair #((remove-by identity) (partial = [%1 %2]) %3))

(def filter-by-key (filter-by first))
(def filter-by-value (filter-by second))
(def filter-by-pair #((filter-by identity) (partial = [%1 %2]) %3))

(def remove-keys (transduce-using remove-by-key))
(def remove-values (transduce-using remove-by-value))
(def filter-keys (transduce-using filter-by-key))
(def filter-values (transduce-using filter-by-value))
