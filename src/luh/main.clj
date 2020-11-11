(ns luh.main
  ;(:gen-class)
  (:require
    [luh.app.routes :refer [make-handler]]
    [luh.app.server :refer [start-server]]))
;=

(def HTTP_OPTIONS
  { :host "localhost"   ;; NOTE: beware CORS issues
    :port 8800})
;-

(defn -main []
  (println (str "listener - http://" (:host HTTP_OPTIONS) ":" (:port HTTP_OPTIONS) "/"))
  (start-server (make-handler) HTTP_OPTIONS))
;;
