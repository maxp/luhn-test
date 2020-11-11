(ns luh.app.handlers
  (:require
    [clojure.spec.alpha :as s]
    [ring.util.response :refer [resource-response]]
    [luh.lib.luhn :refer [check-luhn-sum]]))
;=

; - - - - - - - - - - - - - - - - - - -

(s/def ::code string?)
(s/def ::check-code-s
  (s/keys :req-un [::code]))

(def RE_CODE #"\s*(\d{16})\s*")

(defn ok-response [text]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body text})
;-

(defn check-code [{params :params}]

  (println "check-code params:" params)

  ;
  (when-not (s/valid? ::check-code-s params)
    (throw 
      (ex-info "invalid parameter" (s/explain-data ::check-code-s params))))
  ;
  (if-let [[_ code] (re-matches RE_CODE (:code params))]
    (ok-response
      (if (check-luhn-sum code)
        "Luhn Sum - Ok"
        "Luhn Sum - Wrong"))
    (ok-response
        "Incorrect parameter")))
;;

; - - - - - - - - - - - - - - - - - - -

(defn root-page [_]
  (resource-response "index.html" {:root "public"}))
;;
