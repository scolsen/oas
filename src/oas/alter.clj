(ns oas.alter
  "Modify OAS documents."
  (:require [oas.resolve :as res]))

(defn remove-by [pred p f] 
  "Remove the contents of a segment based on a predicate."
  (assoc {} (key (first p)) (into {} (remove #(pred (f %)) (val (first p))))))

(defn remove-by-key 
  "Remove the keys of an object based on a predicate over keys."
  ([part pred] (remove-by pred part (comp name first)))
  ([part pred ref-string] (remove-by pred (res/resolve-reference part ref-string) (comp name first))))

(defn filter-by-key [part pred]
  "Filter the contents of an object based on a predicate over keys."
  (remove-by #(not (pred %)) part (comp name first)))

(defn remove-by-value [part pred] 
  "Remove the keys of an object based on a predicate over keys."
  (remove-by pred part second))

(defn filter-by-value [part pred]
  "Filter the contents of an object based on a predicate over keys."
  (remove-by #(not (pred %)) part second))

(defn remove-keys [part ks] 
  "Apply multiple filters to a part."
  (loop [k ks result part]
    (if (empty? k) 
        result 
        (recur (rest k) (remove-by-key part #(= (first k) %))))))

(defn filter-keys [part ks] 
  "Apply multiple filters to a part."
  (loop [k ks result part]
    (if (empty? k) 
        result 
        (recur (rest k) (filter-by-key part #(= (first k) %))))))
