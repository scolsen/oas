(ns oas.transduce
  "Modify OAS documents."
  (:require [oas.resolve :as res]))

(defn transduce-by 
  "Takes a transducer and applies it to a predicate.
   First applies f to the k/v pair of x.
   Creates a structure s to hold results."
  [transducer structure & fs]
  (fn [pred x] (into structure (transducer (apply comp pred fs)) x)))

(defn transduce-using 
  "Takes a transduce-by function and a list of things to remove. 
   Removes each x in xs that match the predicate.
   Provides a defualt predicate of = for simple removal by matching.
   You can also use other predicates, such as re-matches. e.g.
   (remove-keys part [#\".*Pet\"] re-matches"
  [t-by pred]
  (fn
    ([api xs]
     (if (empty? xs) 
         api
         (recur (t-by #(pred (first xs) %) api) (rest xs))))
    ([api xs predicate] 
     (if (empty? xs) 
         api
         (recur (t-by #(predicate (first x) %) api) (rest xs) predicate)))))

(def remove-by (partial transduce-by remove {}))
(def filter-by (partial transduce-by filter {}))

(def remove-by-key (remove-by first))
(def remove-by-value (remove-by second))
(def remove-by-pair #((remove-by identity) (partial = [%1 %2]) %3))

(def filter-by-key (filter-by first))
(def filter-by-value (filter-by second))
(def filter-by-pair #((filter-by identity) (partial = [%1 %2]) %3))

(def remove-keys (transduce-using remove-by-key =))
(def remove-values (transduce-using remove-by-value =))
(def filter-keys (transduce-using filter-by-key =))
(def filter-values (transduce-using filter-by-value =))
