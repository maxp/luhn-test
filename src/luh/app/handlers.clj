(ns luh.app.handlers
  (:require
    [clojure.spec.alpha :as s]
    [jsonista.core :as json]
    [ring.util.response :refer [resource-response]]
    [luh.lib.luhn :refer [check-luhn-sum]]))
;=

; - - - - - - - - - - - - - - - - - - -

(s/def ::code string?)
(s/def ::check-code-s
  (s/keys :req-un [::code]))

(def RE_CODE #"\s*(\d{16})\s*")

(defn ok-json [data]
  (println "response:" data)
  {:status 200
   :headers {"Content-Type" "application/json;charset=utf-8"}
   :body (json/write-value-as-string data)})
;-

(defn check-code [{params :params}]

  (println "check-code params:" params)

  ;
  (when-not (s/valid? ::check-code-s params)
    (throw 
      (ex-info "invalid parameter" (s/explain-data ::check-code-s params))))
  ;
  (if-let [[_ code] (re-matches RE_CODE (:code params))]
    (ok-json
      {:message
        (if (check-luhn-sum code)
          "Luhn Sum - Ok"
          "Luhn Sum - Wrong")})
    (ok-json
      {:message "code must be 16 digits"})))
;;

; - - - - - - - - - - - - - - - - - - -

(defn root-page [_]
  (resource-response "index.html" {:root "public"}))
;;
