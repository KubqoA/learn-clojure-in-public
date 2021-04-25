(ns _
  (:require clojure.set))

;; Elementary problems on 4Clojure

;; No. 4
;; Intro to Lists
;; Lists can be constructed with either a function or a quoted form.
;; (= (list __) '(:a :b :c))

(= (list :a :b :c) '(:a :b :c))

;; No. 5
;; Lists: conj
;; When operating on a list, the conj function will return a new list with one
;; or more items "added" to the front.
;; (= __ (conj '(2 3 4) 1))
;; (= __ (conj '(3 4) 2 1))

(= '(1 2 3 4) (conj '(2 3 4) 1) (conj '(3 4) 2 1))

;; No. 6
;; Intro to Vectors
;; Vectors can be constructed several ways. You can compare them with lists.
;; (= [__] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))

(= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))

;; No. 7
;; Vectors: conj
;; When operating on a Vector, the conj function will return a new vector with
;; one or more items "added" to the end.
;; (= __ (conj [1 2 3] 4))
;; (= __ (conj [1 2] 3 4))
(= [1 2 3 4] (conj [1 2 3] 4) (conj [1 2] 3 4))

;; No. 8
;; Intro to Sets
;; Sets are collections of unique values.
;; (= __ (set '(:a :a :b :c :c :c :c :d :d)))
;; (= __ (clojure.set/union #{:a :b :c} #{:b :c :d}))
(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)) (clojure.set/union #{:a :b :c} #{:b :c :d}))

;; No. 9
;; Sets: conj
;; When operating on a set, the conj function returns a new set with one or
;; more keys "added".
;; (= #{1 2 3 4} (conj #{1 4 3} __))
(= #{1 2 3 4} (conj #{1 4 3} 2))

;; No. 10
;; Intro to Maps
;; Maps store key-value pairs. Both maps and keywords can be used as lookup
;; functions. Commas can be used to make maps more readable, but they are not
;; required.
;; (= __ ((hash-map :a 10, :b 20, :c 30) :b))
;; (= __ (:b {:a 10, :b 20, :c 30}))
(= 20 ((hash-map :a 10, :b 20, :c 30) :b) (:b {:a 10, :b 20, :c 30}))

;; No. 11
;; Maps: conj
;; When operating on a map, the conj function returns a new map with one or
;; more key-value pairs "added".
;; (= {:a 1, :b 2, :c 3} (conj {:a 1} __ [:c 3]))
(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))

(= [:a 20] {:a 20}) ;; false
(= (conj {} [:a 20]) {:a 20}) ;; true

;; No. 12
;; Intro to Sequences
;; All Clojure collections support sequencing. You can operate on sequences
;; with functions like first, second, and last.
;; (= __ (first '(3 2 1)))
;; (= __ (second [2 3 4]))
;; (= __ (last (list 1 2 3)))
(= 3 (first '(3 2 1)) (second [2 3 4]) (last (list 1 2 3)))

(first {:a 30 :b 20}) ;; [:a 30]

;; No. 13
;; Sequences: rest
;; The rest function will return all the items of a sequence except the first.
;; (= __ (rest [10 20 30 40]))
(= [20 30 40] (rest [10 20 30 40]))

;; No. 14
;; Intro to Functions
;; Clojure has many different ways to create functions.
(def answer14 8)
(= answer14 ((fn add-five [x] (+ x 5)) 3))
(= answer14 ((fn [x] (+ x 5)) 3))
(= answer14 (#(+ % 5) 3))
(= answer14 ((partial + 5) 3))

;; No. 15
;; Double Down
;; Write a function which doubles a number.
;; (= (__ 11) 22)
(= ((partial * 2) 11) 22)
(= (#(* % 2) 11) 22)

;; No. 16
;; Hello World
;; Write a function which returns a personalized greeting.
;; (= (__ "Dave") "Hello, Dave!")
(= (#(str "Hello, " % "!") "Dave") "Hello, Dave!")

;; No. 17
;; Sequences: map
;; The map function takes two arguments: a function (f) and a sequence (s).
;; Map returns a new sequence consisting of the result of applying f to each
;; item of s. Do not confuse the map function with the map data structure.
;; (= __ (map #(+ % 5) '(1 2 3)))
(= '(6 7 8) (map #(+ % 5) '(1 2 3)))

;; No. 18
;; Sequences: filter
;; The filter function takes two arguments: a predicate function (f) and
;; a sequence (s). Filter returns a new sequence consisting of all the items
;; of s for which (f item) returns true.
;; (= __ (filter #(> % 5) '(3 4 5 6 7)))
(= '(6 7) (filter #(> % 5) '(3 4 5 6 7)))

;; No. 35
;; Local bindings
;; Clojure lets you give local names to values using the special let-form.
;; (= __ (let [x 5] (+ 2 x)))
;; (= __ (let [x 3, y 10] (- y x)))
;; (= __ (let [x 21] (let [y 3] (/ x y))))
(= 7 (let [x 5] (+ 2 x)))

;; No. 36
;; Let it Be
;; Can you bind x, y, and z so that these are all true?
;; (= 10 (let __ (+ x y)))
;; (= 4 (let __ (+ y z)))
;; (= 1 (let __ z))
(= 10 (let [x 7 y 3 z 1] (+ x y)))
(= 4 (let [x 7 y 3 z 1] (+ y z)))
(= 1 (let [x 7 y 3 z 1] z))

;; No. 37
;; Regular Expressions
;; Regex patterns are supported with a special reader macro.
;; https://clojure.org/reference/other_functions#regex
;; (= __ (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))
(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))

;; No. 52
;; Intro to Destructuring
;; Let bindings and function parameter lists support destructuring.
;; (= [2 4] (let [[a b c d e] [0 1 2 3 4]] __))
(= [2 4] (let [[a b c d e] [0 1 2 3 4]] [c e]))