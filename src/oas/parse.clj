(ns oas.parse 
  "Parse OAS files."
  (:require [cheshire.core :refer :all]))

(defn parse [oas] 
  "Parse the entirety of an OAS file." 
  (parse-string oas true))

(defn specification [oas] 
  "Identify the version of an OAS file."
  (let [parsed (parse-string oas true)]
    (if (contains? parsed :swagger) 
        {:swagger (get parsed :swagger)} 
        {:openapi (get parsed :openapi)})))

(defn find-part [oas part]
  "Return the specified part of the OAS document."
  {(keyword part) (get oas (keyword part) nil)})

(defn find-parts [oas parts]
  "Return the specified parts of the OAS document."
  (map #(find-part oas %) (set parts)))

(defn remove-by [pred part f] 
  "Remove the contents of an object based on a predicate."
  (assoc {} (key (first part)) (into {} (remove #(pred (f %)) (val (first part))))))

(defn remove-by-key [part pred] 
  "Remove the keys of an object based on a predicate over keys."
  (remove-by pred part (comp name first)))

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
