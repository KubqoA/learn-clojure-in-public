(ns four-clojure-hard
  (:require [clojure.test :as test]))

;; Hard problems on 4Clojure
(test/run-tests)

;; No. 53
;; Longest Increasing Sub-Seq
;; Given a vector of integers, find the longest consecutive sub-sequence of
;; increasing numbers. If two sub-sequences have the same length, use the one
;; that occurs first. An increasing sub-sequence must have a length of 2 or
;; greater to qualify.

(def f53
  (fn [coll]
    (let [result (->>
                  (reduce #(if (> %2 (last (last %1)))
                             (concat (drop-last %1) [(conj (last %1) %2)])
                             (concat %1 [[%2]]))
                          [[(first coll)]] (drop 1 coll))
                  reverse ;; the max-key returns the last if multiple are same
                  (apply max-key count))]
      (if (> (count result) 1) result []))))

(test/deftest test-f53
  (test/is (= (f53 [1 0 1 2 3 0 4 5]) [0 1 2 3]))
  (test/is (= (f53 [5 6 1 3 2 7]) [5 6]))
  (test/is (= (f53 [2 3 3 4 5]) [3 4 5]))
  (test/is (= (f53 [7 6 5 4]) [])))

;; this solution is inspired by devn's one, but corrected
(def f53'
  (fn [coll]
    (->> coll
         ;; creates groups like ((1 0) (0 1) (1 2) ...)
         (partition 2 1)
         ;; partitions them by the change in monotony
         (partition-by #(> 0 (apply - %)))
         ;; filters decreasing ones
         (filter #(> 0 (apply - (first %))))
         reverse
         ;; finds the largest
         (apply max-key count nil)
         ;; joins the ((1 0) (0 1) ...) group back to nice vector
         (#(when % (cons (ffirst %) (map last %)))) vec)))

;; one liner :D
(fn [c] (->> c(partition 2 1)(partition-by #(> 0 (apply - %)))(filter #(> 0 (apply - (first %))))reverse(apply max-key count nil)(#(when % (cons (ffirst %) (map last %)))) vec))

(test/deftest test-f53'
  (test/is (= (f53' [1 0 1 2 3 0 4 5]) [0 1 2 3]))
  (test/is (= (f53' [5 6 1 3 2 7]) [5 6]))
  (test/is (= (f53' [2 3 3 4 5]) [3 4 5]))
  (test/is (= (f53' [7 6 5 4]) [])))