(ns api-test
  (:import 
    [java.io File])
  (:require
    [clojure.test :refer [deftest testing is]]
    [luh.app.handlers :refer [check-code root-page]]))
;=

(deftest check-code-test
  (testing "correct parameter"
    (let [req {:params {:code "1234123412341238"}}
          resp (check-code req)]
      (is (= 200 (:status resp)))))

  (testing "incorrect request"
    (let [req {:params {:qwe "asd"}}]
      (is (thrown? clojure.lang.ExceptionInfo (check-code req))))))
;;

(deftest root-page-test
  (testing "root page response"
    (let [{:keys [status body]} (root-page {})]
      (is (= 200 status))
      (is (= File (type body))))))
;;
