(ns oas.update
  "Reducing functions."
  (:require [oas.resolve :as r]))

(defn append-to 
  [oas k v] 
  (when ((complement contains?) oas k)
    (assoc oas k v))) 

(defn modify 
  "Update a key with a value in place."
  ([oas k v] (assoc oas k v))
  ([oas k v reference]
   (loop [r (r/ref-to-keys reference) k* k v* v]
         (if (empty? r) 
             (assoc oas k* v*)
             (recur (rest (reverse r)) 
                    (first (reverse r)) 
                    (assoc (r/resolve-list oas r) k* v*))))))

(defn modify-each 
  "Modify multiple objects in place."
  [oas k v references] 
  (if (empty? references)
      oas
      (recur (modify oas k v (first references)) k v (rest references))))
