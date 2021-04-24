;; Easy problems on 4Clojure

;; No. 19
;; Last Element
;; Write a function which returns the last element in a sequence.
;; Restricted: last
(def f19 #(first (reverse %)))
(= (f19 [1 2 3 4 5]) 5)
(= (f19 '(5 4 3)) 3)
(= (f19 ["b" "c" "d"]) "d")

;; No. 20
;; Penultimate Element
;; Write a function which returns the second to last element from a sequence.
(def f20 #(first (take-last 2 %)))
(= (f20 (list 1 2 3 4 5)) 4)
(= (f20 ["a" "b" "c"]) "b")
(= (f20 [[1 2] [3 4]]) [1 2])

;; No. 21
;; Nth Element
;; Write a function which returns the Nth element from a sequence.
;; Restricted: nth
(def f21 #(first (drop %2 %1)))
(def f21' #((vec %1) %2))
(= (f21 '(4 5 6 7) 2) 6)
(= (f21 [:a :b :c] 0) :a)
(= (f21 [1 2 3 4] 1) 2)
(= (f21' '([1 2] [3 4] [5 6]) 2) [5 6])

;; No. 22
;; Count a Sequence
;; Write a function which returns the total number of elements in a sequence.
;; Restricted: count
(defn f22
  [coll]
  ;; %& says the function takes any number of args
  ;; because we don't need the second arg, using #_%& we can include it but
  ;; it gets ignored (much like using (comment ...))
  (reduce #(+ % 1 #_%&) 0 coll))

;; alternatively something more readable:
;; (reduce (fn [sum & _] (+ 1 sum)) 0 coll)
(def f22' #(reduce (fn [sum & _] (+ 1 sum)) 0 %))

(= (f22 '(1 2 3 3 1)) 5)
(= (f22 "Hello World") 11)
(= (f22 [[1 2] [3 4] [5 6]]) 3)
(= (f22' '(13)) 1)
(= (f22' '(:a :b :c)) 3)

;; No. 23
;; Reverse a Sequence
;; Write a function which reverses a sequence.
;; Restricted: reverse, rseq

;; probably really ineffective, first try
;; (defn f23
;;   [coll]
;;   (map #(first (take-last % coll)) (range 1 (+ 1 (count coll)))))

(defn f23
  [coll]
  (reduce (fn [rev rest] (conj rev rest)) () coll))
;; (fn [coll] (reduce (fn [rev rest] (conj rev rest)) () coll)))
;; #(reduce (fn [rev rest] (conj rev rest)) () %)

(= (f23 [1 2 3 4 5]) [5 4 3 2 1])
(= (f23 (sorted-set 5 7 2 7)) '(7 5 2))
(= (f23 [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]])

;; No. 24
;; Sum It All Up
;; Write a function which returns the sum of a sequence of numbers.
(def f24
  #(apply + %))

(= (f24 [1 2 3]) 6)
(= (f24 (list 0 -2 5 5)) 8)
(= (f24 '(0 0 -1)) -1)
(= (f24 #{4 2 1}) 7)
(= (f24 '(1 10 3)) 14)

;; No. 25
;; Find the odd numbers
;; Write a function which returns only the odd numbers from a sequence.
(def f25
  #(filter odd? %))

(= (f25 #{1 2 3 4 5}) '(1 3 5))
(= (f25 [4 2 1 6]) '(1))
(= (f25 [2 2 4 6]) '())
(= (f25 [1 1 1 3]) '(1 1 1 3))

;; No. 26
;; Fibonacci Sequence
;; Write a function which returns the first X fibonacci numbers.

;; This solution is not correct for (f26 1)
(def f26
  #(reduce (fn [fibs n] (conj fibs (+ (fibs (- n 1)) (fibs (- n 2))))) [1 1] (range 2 %)))

;; https://tammymakesthings.com/posts/2020-05-06-fibonacci-sequence-in-clojure/
(def seq-iterate-fibonacci
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))
(def f26'
  #(drop 1 (take (+ 1 %) seq-iterate-fibonacci)))

(= (f26' 3) '(1 1 2))
(= (f26' 6) '(1 1 2 3 5 8))
(= (f26 8) '(1 1 2 3 5 8 13 21))

;; No. 27
;; Palindrome Detector
;; Write a function which returns true if the given sequence is a palindrome.
(def half #(take (quot (count %) 2) %))

(def f27
  (fn [coll] (every? true?
                     (map-indexed #(= %2 (nth coll (- (count coll) %1 1)))
                                  (half coll)))))



;; this is a nice eloquent solution, but IMHO it requires a lot of operations
;; (def f27
;;   #(= (seq %) (seq (reverse %))))

(false? (f27 '(1 2 3 4 5)))
(true? (f27 "racecar"))
(true? (f27 [:foo :bar :foo]))
(true? (f27 '(1 1 3 3 1 1)))
(false? (f27 '(:a :b :c)))

;; No. 28
;; Flatten a Sequence
;; Write a function which flattens a sequence.
;; Restricted: flatten
(def f28
  (fn flatten' [[item & remaining]] 
    (if (nil? item) nil
        (concat (if (coll? item) (flatten' item) (list item)) (flatten' remaining)))))

(= (f28 '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (f28 ["a" ["b"] "c"]) '("a" "b" "c"))
(= (f28 '((((:a))))) '(:a))

;; No. 29
;; Get the Caps
;; Write a function which takes a string and returns a new string containing
;; only the capital letters.
(def f29
  (fn [coll] (apply str (filterv #(and (>= (int %) (int \A)) (<= (int %) (int \Z))) coll))))

(= (f29 "HeLlO, WoRlD!") "HLOWRD")
(empty? (f29 "nothing"))
(= (f29 "$#A(*&987Zf") "AZ")

;; No. 30
;; Compress a Sequence
;; Write a function which removes consecutive duplicates from a sequence.
(def f30
  (fn compress [[item & remaining]]
    (if (nil? remaining) (list item)
      (if (= item (first remaining)) (compress remaining) (conj (compress remaining) item)))))

(def f30'
  #(map first (partition-by identity %)))

(= (apply str (f30 "Leeeeeerrroyyy")) "Leroy")
(= (f30 [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (f30' [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

;; No. 31
;; Pack a Sequence
;; Write a function which packs consecutive duplicates into sub-lists.
(def f31
  #(partition-by identity %))

(= (f31 [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (f31 [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (f31 [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

;; No. 32
;; Duplicate a Sequence
;; Write a function which duplicates each element of a sequence.
;; (def f32
;;   (fn [coll] (flatten (map #(list % %) coll))))

(def f32
  #(interleave % %))

(= (f32 [1 2 3]) '(1 1 2 2 3 3))
(= (f32 [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (f32 [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
(= (f32 [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

;; No. 33
;; Replicate a Sequence
;; Write a function which replicates each element of a sequence a variable number of times.

;; This doesn't work on 4Clojure
;; clojure.lang.ArityException: Wrong number of args (1) passed to: core$interleave
(def f33
  #(apply interleave (repeat %2 %1)))

(def f33'
  #(mapcat (fn [x] (repeat %2 x)) %1))

(= (f33 [1 2 3] 2) '(1 1 2 2 3 3))
(= (f33 [:a :b] 4) '(:a :a :a :a :b :b :b :b))
(= (f33 [4 5 6] 1) '(4 5 6))
(= (f33' [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
(= (f33 [44 33] 2) [44 44 33 33])

;; No. 34
;; Implement range
;; Write a function which creates a list of all integers in a given range.
(def f34
  (fn range' [start end]
    (if (= start end) nil (conj (range' (+ start 1) end) start))))

(def f34'
  (fn [start end] (map-indexed #(+ start %1 #_%&) (repeat (- end start) 1))))

(def f34''
  #(take (- %2 %1) (iterate inc %1)))

(= (f34 1 4) '(1 2 3))
(= (f34' -2 2) '(-2 -1 0 1))
(= (f34'' 5 8) '(5 6 7))