(ns luh.app.routes
  (:require
    [luh.app.handlers :refer [check-code root-page]]))
;=

(def URI_HANDLER_MAP
  {
    "/api/check-code" check-code
    "/"               root-page})
;- 

(defn make-handler []
  (fn [req]
    ;; NOTE: request method not handled!
    (if-let [hdl (get URI_HANDLER_MAP (:uri req))]
      (try
        (hdl req)
        (catch clojure.lang.ExceptionInfo ex
          {:status 400 :body (ex-message ex)}))
      {:status 404 :body "Not Found"})))
;;
