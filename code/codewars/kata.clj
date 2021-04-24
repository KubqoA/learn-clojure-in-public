(ns kata
  (:require [clojure.string :as str]
            [clojure.test :as test]))

(test/run-all-tests)

;; Detect pangram
;; 6 kyu
;; https://www.codewars.com/kata/545cedaa9943f7fe7b000048
(defn pangram?
  [s]
  (every? #(str/includes? (str/upper-case s) (str (char %))) (range 65 91)))

(test/deftest pangram?-tests
  (test/is (= (pangram? "The quick brown fox jumps over the lazy dog.") true))
  (test/is (= (pangram? "Pack my box with five dozen liquor jugs.") true))
  (test/is (= (pangram? "Not every sentence is a a pangram. An example") false)))

;; String repeat
;; 8 kyu
;; https://www.codewars.com/kata/57a0e5c372292dd76d000d7e
(defn repeat-str
  [n strng]
  (apply str (repeat n strng)))

(test/deftest repeat-str-tests
  (test/is (= (repeat-str 4 "a") "aaaa"))
  (test/is (= (repeat-str 3 "hello ") "hello hello hello "))
  (test/is (= (repeat-str 2 "abc") "abcabc")))

;; Odd or Even?
;; 7 kyu
;; https://www.codewars.com/kata/5949481f86420f59480000e7
(defn odd-or-even
  [xs]
  (if (even? (apply + xs)) "even" "odd"))

(test/deftest odd-or-even-tests
  (test/is (= (odd-or-even [1]) "odd"))
  (test/is (= (odd-or-even [-1]) "odd"))
  (test/is (= (odd-or-even [1, -2]) "odd"))
  (test/is (= (odd-or-even [2, 5, 34, 6]) "odd"))
  (test/is (= (odd-or-even [0]) "even"))
  (test/is (= (odd-or-even [2]) "even"))
  (test/is (= (odd-or-even [-2]) "even"))
  (test/is (= (odd-or-even [0, -1, -5]) "even"))
  (test/is (= (odd-or-even []) "even")))

;; Array.diff
;; 6 kyu
;; https://www.codewars.com/kata/523f5d21c841566fde000009
(defn array-diff
  [a b]
  (filterv #(= (some #{%} b) nil) a))

(test/deftest array-diff-tests
  (test/is (= (array-diff [1 2 2 2 3] [2]) '(1 3)))
  (test/is (= (array-diff [1 12 82 10 42 71 34 42] [12 42]) '(1 82 10 71 34))))

;; Principle of the other solution
(remove #{2} [1 3 2]) ;; (1 3)
(remove #{1 2 3} [4 5 1 3 6]) ;; (4 5 6)

(defn array-diff'
  [a b]
  (remove (set b) a))

(test/deftest array-diff'-tests
  (test/is (= (array-diff' [1 2 2 2 3] [2]) '(1 3)))
  (test/is (= (array-diff' [1 12 82 10 42 71 34 42] [12 42]) '(1 82 10 71 34))))

;; Remove anchor from URL
;; 7 kyu
;; https://www.codewars.com/kata/51f2b4448cadf20ed0000386

(defn remove-url-anchor
  [url]
  (str/replace-first url #"(.*)#.*" "$1"))

(test/deftest remove-url-anchor-tests
  (test/is (= (remove-url-anchor "www.codewars.com#about") "www.codewars.com"))
  (test/is (= (remove-url-anchor "www.codewars.com/katas/?page=1#about") "www.codewars.com/katas/?page=1"))
  (test/is (= (remove-url-anchor "www.codewars.com/katas/") "www.codewars.com/katas/")))