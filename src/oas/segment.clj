(ns oas.segment
  "Access segments of an OAS document.")

(defn segment [oas k] 
  "Get a segment of a parsed OAS document."
  (let [kw (keyword k)] 
    {kw (get oas kw nil)}))

(defn segments [oas parts]
  "Return the specified parts of the OAS document."
  (map #(segment oas %) (set parts)))

(defn specification [oas] 
  "Identify the version of an OAS file."
    (if (contains? oas :swagger) 
        (segment oas :swagger) 
        (segment oas :openapi)))

(defn paths [oas] 
  "Get the paths object of an OAS document."
  (segment oas :paths))

(defn info [oas] 
  "Get the info object of an OAS document."
  (segment oas :info))

(defn servers [oas]
  "Get the servers segment of an OAS document."
  (segment oas :servers))

(defn components [oas] 
  "Get the components segment of an OAS document."
  (segment oas :components))

(defn security [oas] 
  "Get the security segment of an OAS document."
  (segment oas :security))

(defn tags [oas]
  "Get the tags segment of an OAS document."
  (segment oas :tags))

(defn externalDocs [oas] 
  "Get the externalDocs segment of an OAS document."
  (segment oas :externalDocs))
