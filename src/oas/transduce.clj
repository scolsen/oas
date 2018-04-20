(ns oas.transduce
  "Modify OAS documents."
  (:require [oas.resolve :as res]))

(defn transduce-by [t s & fs]
  "Takes a transducer and applies it to a predicate.
   First applies f to the k/v pair of x.
   Creates a structure s to hold results."
  (fn [p x] (into s (t (apply comp p fs)) x)))

(defn transduce-using [t-by pred]
  "Takes a remove function and a list of things to remove. 
   Removes each x in xs that match the predicate.
   Provides a defualt predicate of = for simple removal by matching.
   You can also use other predicates, such as re-matches. e.g.
   (remove-keys part [#\".*Pet\"] re-matches"
  (fn
    ([part xs]
      (loop [x xs result part] 
            (if (empty? x) 
                result
                (recur (rest x) (t-by result #(pred (first x) %)))))) 
    ([part xs predicate] 
      (loop [x xs result part] 
            (if (empty? x) 
                result
                (recur (rest x) (t-by result #(predicate (first x) %))))))))

(defn append-to [target k v] 
  "Append a key/val to a target part. 
   Does not modify contents if the key already exists."
  (if (contains? target k) 
      target 
      (assoc target k v)))

(defn modify [target k v]
  "Update a key with a value."
  (assoc target k v))

(def remove-by-key (transduce-by remove {} name first))
(def remove-by-value (transduce-by remove {} second))
(def remove-by-pair #((transduce-by remove {} identity) (partial = [%1 %2]) %3))
(def filter-by-key (transduce-by filter {} name first))
(def filter-by-value (transduce-by filter {} second))
(def filter-by-pair #((transduce-by filter {} identity) (partial = [%1 %2]) %3))
(def remove-keys (transduce-using remove-by-key =))
(def remove-values (transduce-using remove-by-value =))
(def filter-keys (transduce-using filter-by-key =))
(def filter-values (transduce-using filter-by-value =))
