(ns oas.write 
  "Write out OAS documents."
  (:require [cheshire.core :as che] [oas.parse :as parse]
            [oas.alter :as alt]))

(defn write-file
  "Write out an encoded part or document."
  ([encoded] (che/generate-string encoded))
  ([encoded file] (spit file (che/generate-string encoded))))

(defn append-file 
  "Append the part contents to a segment in a file."
  ([in out k v]
   (-> in 
       (parse/parse)
       (alt/append-to k v)
       (write-file out))) 
  ([in out k v target] 
   (let [parsed (parse/parse in)]
    (-> target 
        (segment parsed)
        (alt/append-to k v)
        (->> (alt/modify parsed target))
        (write-file out)))))
