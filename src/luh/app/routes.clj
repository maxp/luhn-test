(ns luh.app.routes)


(defn make-handler []
  (fn [_req]
    {:status 200
     :body "handler stub"}))
;;
