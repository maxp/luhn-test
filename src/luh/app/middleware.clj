(ns luh.app.middleware
  (:import
    [com.fasterxml.jackson.core JsonParseException])
  (:require
    [jsonista.core :as json]))
;=

(def ^:dynamic 
  *malformed-json-response*
  {:status  400
   :headers {"Content-Type" "text/plain"}
   :body    "Malformed JSON request."})
;-

(def RE_APPLICATION_JSON #"^application/(.+?\+)?json")

(defn- json-request? [request]
  (when-let [ctype (or
                      (get-in request [:headers :content-type])
                      (get-in request [:headers "content-type"]))]
    (boolean (re-find RE_APPLICATION_JSON ctype))))
;-

(defn- parse-json-data [body]
  (try
    [true (json/read-value body json/keyword-keys-object-mapper)]
    (catch JsonParseException _ex
      [false nil])))
;-

(defn- merge-json-params [req data]
  (let [request (assoc req :json-params data)]
    (if (map? data)
      (update-in request [:params] merge data)
      request)))
;-

(defn wrap-json-params [handler]
  (fn [req]
    (if (json-request? req)
      (let [[valid? data] (parse-json-data (:body req))]
        (if valid?
          (handler (merge-json-params req data))
          *malformed-json-response*))
      (handler req))))
;;

(comment

  (json-request? {:headers {"content-type" "application/vnd+json"} :body "[true]"})
  
  ,)
