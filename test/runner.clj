(ns runner
  (:require
    [clojure.test :refer [run-tests]]
    [luhn-test]
    [api-test]))
;=

(defn -main []
  (run-tests 'luhn-test 'api-test))
  ;(run-all-tests re))
;;

(comment

  (require 'luhn-test)

  (run-tests 'luhn-test)

  ,)
