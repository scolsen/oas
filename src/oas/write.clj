(ns oas.write 
  "Write out OAS documents."
  (:require [cheshire.core :as che] [oas.parse :as parse]
            [oas.alter :as alt]))

(defn write-file
  "Write out an encoded part or document."
  ([encoded] (che/generate-string encoded))
  ([encoded file] (spit file (che/generate-string encoded))))

(defn append-file [file k v target] 
  "Append the part contents to a segment in a file."
  ([file k v]
   (-> file 
       (parse/parse)
       (alt/append-to k v)
       (write-file file))) 
  ([file k v target] 
   (let [parsed (parse/parse file)]
    (-> target 
        (segment (parse/parse file))
        (alt/append-to k v)
        (->> (alt/modify parsed target))
        (write-file file)))))
