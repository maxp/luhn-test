(ns luh.lib.luhn)

;; https://en.wikipedia.org/wiki/Luhn_algorithm
;; 
(defn calc-luhn-sum [^String digits]
  (->> 
    (reverse digits)
    (map-indexed
      #(let [digit (- (int %2) (int \0))]
        (if (even? %1) 
          digit
          (let [dig2 (+ digit digit)]
            (if (< 9 dig2)
              (- dig2 9)
              dig2)))))
    (reduce +)))
;;

(defn check-luhn-sum [^String digits]
  (->
    (calc-luhn-sum digits)
    (mod 10)
    (= 0)))
;;

(comment

  (calc-luhn-sum "4539148803436467")
  ;; => 80

  (calc-luhn-sum "79927398713")
  ;; => 70

  ;; ???
  (calc-luhn-sum "7253226253120539")
  ;; => 50


  (->>
    [ "79927398710" "79927398711" "79927398712" "79927398713" "79927398714" 
      "79927398715" "79927398716" "79927398717" "79927398718" "79927398719"]
    (map calc-luhn-sum))
  ;; => (67 68 69 70 71 72 73 74 75 76)

  (check-luhn-sum "4539148803436467")
  ;; => true

  (check-luhn-sum "4539148803436460")
  ;; => false


  (check-luhn-sum "79927398713")
  ;; => true

  (check-luhn-sum "799273987131")
  ;; => false

  ,)
