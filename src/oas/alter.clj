(ns oas.alter
  "Modify OAS documents."
  (:require [oas.resolve :as res]))

(defn remove-by  
  "Returns a function that removes the contents of a map based on a predicate.
   f is applied to the part, 
   g is applied to the predicate--a predicate modifier."
  ([f] 
   (fn [p pred] 
       (into {} (remove #(pred (f %)) p))))
  ([f g] 
   (fn [p pred] 
       (into {} (remove #(g (pred (f %))) p)))))

(defn remove-xs [removal pred]
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
                (recur (rest x) (removal result #(pred (first x) %)))))) 
    ([part xs predicate] 
      (loop [x xs result part] 
            (if (empty? x) 
                result
                (recur (rest x) (removal result #(predicate (first x) %))))))))


(defn append-to [target k v] 
  "Append a key/val to a target part. 
   Does not modify contents if the key already exists."
  (if (contains? target k) 
      target 
      (assoc target k v)))

(defn modify [target k v]
  "Update a key with a value."
  (assoc target k v))

(def remove-by-key (remove-by (comp name first)))
(def remove-by-value (remove-by second))
(def filter-by-key (remove-by (comp name first) not))
(def filter-by-value (remove-by second not))
(def remove-keys (remove-xs remove-by-key =))
(def remove-values (remove-xs remove-by-value =))
(def filter-keys (remove-xs filter-by-key =))
(def filter-values (remove-xs filter-by-value =))

