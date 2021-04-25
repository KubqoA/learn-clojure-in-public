(ns four-clojure-medium
  (:require [clojure.test :as test]))

;; Medium problems on 4Clojure
(test/run-tests)

;; No. 43
;; Reverse Interleave
;; Write a function which reverses the interleave process into x number
;; of subsequences.
(def f43
  (fn [coll n] (reduce
                (fn [colls values] (map-indexed #(conj (nth colls %1 nil) %2) values))
                ()
                (reverse (partition n coll)))))

;; Note. map can accept multiple colls, see f39''
(def f43'
  #(apply map list (partition %2 %1)))

(test/deftest f43-test
  (test/is (= (f43 [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6))))
  (test/is (= (f43' (range 9) 3) '((0 3 6) (1 4 7) (2 5 8))))
  (test/is (= (f43 (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))))

;; No. 44
;; Rotate Sequence
;; Write a function which can rotate a sequence in either direction.
(def f44
  #(let [len (count %2) n (rem %1 len) n (if (>= n 0) n (+ len n))]
     (apply concat (reverse (split-at n %2)))))

(test/deftest f44-test
  (test/is (= (f44 2 [1 2 3 4 5]) '(3 4 5 1 2)))
  (test/is (= (f44 -2 [1 2 3 4 5]) '(4 5 1 2 3)))
  (test/is (= (f44 6 [1 2 3 4 5]) '(2 3 4 5 1)))
  (test/is (= (f44 1 '(:a :b :c)) '(:b :c :a)))
  (test/is (= (f44 -4 '(:a :b :c)) '(:c :a :b))))

;; No. 46
;; Flipping out
;; Write a higher-order function which flips the order of the arguments
;; of an input function.
(def f46
  (fn [f] #(f %2 %1)))

(test/deftest f46-test
  (test/is (= 3 ((f46 nth) 2 [1 2 3 4 5])))
  (test/is (= true ((f46 >) 7 8)))
  (test/is (= 4 ((f46 quot) 2 8)))
  (test/is (= [1 2 3] ((f46 take) [1 2 3 4 5] 3))))

;; No. 50
;; Split by Type
;; Write a function which takes a sequence consisting of items with different
;; types and splits them up into a set of homogeneous sub-sequences. The
;; internal order of each sub-sequence should be maintained, but the
;; sub-sequences themselves can be returned in any order (this is why 'set' is
;; used in the test cases).
(def f50
  #(vals (group-by type %)))

(test/deftest f50-test
    (test/is (= (set (f50 [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]}))
    (test/is (= (set (f50 [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]}))
    (test/is (= (set (f50 [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})))
