(ns oas.write 
  "Write out OAS documents."
  (:require [cheshire.core :as che] [oas.parse :as parse]
            [oas.alter :as alt][oas.segment :as seg]))

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
       (parse/parse-file)
       (alt/append-to k v)
       (write out))) 
  ([in out k v target] 
   (let [parsed (parse/parse-file in)]
    (-> target 
        (seg/segment parsed)
        (alt/append-to k v)
        (->> (alt/modify parsed target))
        (write out)))))
