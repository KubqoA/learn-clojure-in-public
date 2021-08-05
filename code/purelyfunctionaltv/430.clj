;; New Numbers

;; A number is new if its digits are not a permutation of a smaller number. For
;; instance, 789 is a new number because its permutations (879, 798, 897, 978,
;; and 987) are all larger than it is. However, 645 is not a new number since
;; 456 and 465 are smaller than it.

;; Write a function that takes an integer and returns true if it is a new
;; number and false otherwise.

(defn new-number? [x] (->> x str (map int) (apply <=)))

;; Examples
(new-number? 789) ;=> true
(new-number? 645) ;=> false
(new-number? 444) ;=> true (it's permutations are not smaller than it)
