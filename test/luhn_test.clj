(ns luhn-test
  (:require
    [clojure.test :refer [deftest testing is]]
    [luh.lib.luhn :refer [check-luhn-sum]]))
;=

(deftest checksum-test
  (testing "correct sums"
    (is (= true (check-luhn-sum "4539148803436467")))
    (is (= true (check-luhn-sum "79927398713")))
    (is (= true (check-luhn-sum "7253226253120539")))
    (is (= true (check-luhn-sum "1234123412341238"))))

  (testing "incorrest sums"
    (is (= false (check-luhn-sum "79927398710")))
    (is (= false (check-luhn-sum "79927398711")))
    (is (= false (check-luhn-sum "79927398712")))
    (is (= false (check-luhn-sum "79927398714")))
    (is (= false (check-luhn-sum "79927398715")))
    (is (= false (check-luhn-sum "79927398716")))
    (is (= false (check-luhn-sum "79927398717")))
    (is (= false (check-luhn-sum "79927398718")))
    (is (= false (check-luhn-sum "79927398719")))))
;;
