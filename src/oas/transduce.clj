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
                (recur (rest x) (t-by #(pred (first x) %) result))))) 
    ([part xs predicate] 
      (loop [x xs result part] 
            (if (empty? x) 
                result
                (recur (rest x) (t-by #(predicate (first x) %) result)))))))

(defn append-to [target k v] 
  "Append a key/val to a target part. 
   Returns nil if the part already contains the key."
  (when ((complement contains?) target k) 
    (assoc target k v)))

(defn modify [target k v]
  "Update a key with a value."
  (assoc target k v))

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
