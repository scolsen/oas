(ns oas.write 
  "Write out OAS documents."
  (:require [cheshire.core :as che] [oas.parse :as p]
            [oas.update :as u][oas.segment :as s]
            [oas.resolve :as r]))

(defn write
  "Write out an encoded part or document."
  ([encoded] (che/generate-string encoded))
  ([encoded file] (spit file (che/generate-string encoded))))

(defn append-file 
  "Append a key and value to a segment and write to a file.
   Reads the in file, and parses the json.
   appends the key and value to the in file.
   writes the result out to the out file."
  ([in out k v]
   (-> in 
       (p/parse-file)
       (u/append-to k v)
       (write out))) 
  ([in out k v reference] 
   (let [parsed (p/parse-file in)]
        (-> reference 
            (->> (u/modify parsed k v))
            (write out)))))

;; add compatibility checking. i.e. 2.0 cannot be merged to 3.0.
;; later add support for merging 2.0 and 3.0 
(defn merge-files
  "Merge the contents of one oas file with another."
  [in in* out]
  (let [oas (p/parse-file in) oas* (p/parse-file in*)]
       (-> oas
           (u/merge-oas oas*)
           (write out))))

