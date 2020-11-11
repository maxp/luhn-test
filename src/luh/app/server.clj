(ns luh.app.server
  (:require
    [clojure.spec.alpha   :as     s]
    [clojure.string       :refer  [blank?]]
    ;
    [org.httpkit.server       :refer  [run-server server-stop!]]
    ;[ring.util.response       :refer  [resource-response content-type]]
    [ring.middleware.params           :refer  [wrap-params]]
    [ring.middleware.keyword-params   :refer  [wrap-keyword-params]]))
    ;[ring.middleware.multipart-params :refer  [wrap-multipart-params]]))
;=

; - - - - - - - - - - - - - - - - - - -

(defn not-blank? [s]
  (and (string? s) (not (blank? s))))

(s/def ::host not-blank?)
(s/def ::port pos-int?)

(s/def ::httpkit-options
  (s/keys :req-un [::host ::port]))
;=

; - - - - - - - - - - - - - - - - - - -

;; https://github.com/http-kit/http-kit/blob/master/src/org/httpkit/server.clj      

(defn start-server [handler options]
  {:pre
    [(s/valid? fn? handler)
     (s/valid? ::httpkit-options options)]}
  ;
  (->
    handler
    ;(wrap-json-params)
    (wrap-keyword-params) 
    ;(wrap-multipart-params)
    (wrap-params) 
    ;(wrap-exceptinos)
    (run-server
      (assoc options
        :ip                   (:host options)
        :worker-name-prefix   "http-kit-"
        :server-header        nil
        :legacy-return-value? false))))
;;

(defn stop-server [server]
  (when server
    (server-stop! server {:timeout 500})))
;;
