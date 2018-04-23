(ns oas.specs.swagger.operation 
  "Operations"
  (:require [clojure.spec.alpha :as spec]
            [oas.specs.swagger.parameters]
            [oas.specs.swagger.responses]
            [oas.specs.swagger.securityRequirement]))

(defn scheme? [x] 
  (or (= x "http") (= x "https") (= x "ws") (= x "wss"))) 

(spec/def ::tags (spec/coll-of string?))
(spec/def ::operationId string?)
(spec/def ::summary string?)
(spec/def ::description string?)
(spec/def ::externalDocs (spec/get-spec :oas.specs.swagger.externalDocs/externalDocs))
(spec/def ::consumes (spec/coll-of string?))
(spec/def ::produces (spec/coll-of string?))
(spec/def ::parameters (spec/get-spec :oas.specs.swagger.parameters/parameters))
(spec/def ::responses (spec/get-spec :oas.specs.swagger.responses/responses))
(spec/def ::schemes (spec/coll-of (spec/and string? scheme?))) ;; restricted value set. http, https, ws, wss
(spec/def ::deprecated (spec/or :true true? :false false?))
(spec/def ::security (spec/coll-of (spec/get-spec :oas.specs.swagger.securityRequirement/securityRequirement)))

(spec/def ::operation (spec/keys :req-un [::responses]
                                 :opt-un [::tags ::summary ::description 
                                          ::externalDocs ::operationId ::consumes 
                                          ::produces ::schemes 
                                          ::parameters ::deprecated ::security]))

