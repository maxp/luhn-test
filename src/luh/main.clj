(ns luh.main
  ;(:gen-class)
  (:require
    [luh.app.routes :refer [make-handler]]
    [luh.app.server :refer [start-server]]))
;=

(def HTTP_OPTIONS
  { :host "0.0.0.0"
    :port 8800})
;-

(defn -main []
  (start-server (make-handler) HTTP_OPTIONS))
;;
