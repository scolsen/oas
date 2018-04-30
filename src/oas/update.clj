(ns oas.update
  "Reducing functions."
  (:require [oas.resolve :as r]
            [oas.json-pointer :as j]))

(defn append-to 
  [json k v] 
  (when ((complement contains?) json k)
    (assoc json k v))) 

(defn modify-json 
  "Update a key with a value in place."
  ([json k v] (assoc json k v))
  ([json k v pointer]
   (loop [p (j/parse-pointer pointer) k* k v* v]
         (if (empty? r) 
             (assoc json k* v*)
             (recur (butlast p) 
                    (last p) 
                    (assoc (r/resolve-json json p) k* v*))))))

(defn modify-each 
  "Modify multiple objects in place."
  [oas k v references] 
  (if (empty? references)
      oas
      (recur (modify oas k v (first references)) k v (rest references))))

(defn merge-json 
  "Merge oas objects."
  ([json json*]
   (merge json json*))
  ([json json* pointer pointer*]
   (let [k (last (j/parse-pointer pointer))] 
        (modify json k
                (merge (r/resolve-json json pointer) 
                       (r/resolve-json json* pointer*))
             (j/keys->pointer pointer drop-last)))))
