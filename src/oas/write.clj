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
  ([in out k v target] 
   (let [parsed (p/parse-file in)]
        (-> target 
            (cond-> (r/reference? target) (->> (r/resolve-reference parsed))
                    ((complement r/reference?) target) (s/segment parsed))
            (u/append-to k v)
            (->> (u/modify parsed target))
            (write out)))))
