(ns oas.alter
  "Modify OAS documents."
  (:require [oas.resolve :as res]))

(defn remove-by  
  "Returns a function that removes the contents of a segment based on a predicate.
   The function first runs the provided f over the part, and the g over the predicate."
  ([f] 
   (fn [p pred] 
       (assoc {} (key (first p)) 
                 (into {} (remove #(pred (f %)) (val (first p)))))))
  ([f g] 
   (fn [p pred] 
       (assoc {} (key (first p)) 
                 (into {} (remove #(g (pred (f %))) (val (first p))))))))

(def remove-by-key (remove-by (comp name first)))

(def remove-by-value (remove-by second))

(def filter-by-key (remove-by (comp name first) not))

(def filter-by-value (remove-by second not))

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

(defn filter-values [part vs]
  "Apply multiple value filters to a part."
  (loop [v vs result part]
        (if (empty? v) 
          result 
          (recur (rest v) (filter-by-value part #(= (first v) %))))))

(defn append-to [target k v] 
  "Append a key/val to a target part. Does not modify contents if the key already exists."
  (if (contains? target k) 
      target 
      (assoc target k v)))

(defn modify [target k v]
  "Update a key with value."
  (assoc target k v))
