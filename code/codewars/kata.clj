(ns kata
  (:require [clojure.string :as str]
            [clojure.test :as test]))

(test/run-tests)

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

;; Highest Rank Number in an Array
;; 6 kyu
;; https://www.codewars.com/kata/5420fc9bb5b2c7fd57000004

(def highest-rank #(->> % frequencies (sort-by (juxt val key)) last key))
;;(def highest-rank #(->> % frequencies (sort-by first) (apply max-key second) first))
;; (juxt val key) ==  #(vector (val %) (key %))

(test/deftest highest-rank-tests
  (test/is (= (highest-rank [12, 10, 8, 12, 7, 6, 4, 10, 12]) 12))
  (test/is (= (highest-rank [12, 10, 8, 12, 7, 6, 4, 10]) 12))
  (test/is (= (highest-rank [12, 10, 8, 12, 7, 6, 4, 10, 10]) 10))
  (test/is (= (highest-rank [12, 10, 8, 12, 7, 6, 4, 10, 10, 12, 12]) 12))
  (test/is (= (highest-rank [12, 10, 8, 12, 7, 6, 4, 10, 10, 12, 12, 14, 14, 14, 14]) 14)))

;; Switcheroo
;; 7 kyu
;; https://www.codewars.com/kata/57f759bb664021a30300007d

(defn switcheroo [xs]
  (apply str (replace {\a \b \b \a} xs)))

(test/deftest switcheroo-tests
  (test/is (= (switcheroo "abc") "bac"))
  (test/is (= (switcheroo "aaabcccbaaa") "bbbacccabbb"))
  (test/is (= (switcheroo "ccccc") "ccccc"))
  (test/is (= (switcheroo "abababababababab")  "babababababababa"))
  (test/is (= (switcheroo "aaaaa") "bbbbb")))

;; L2: Triple X
;; 7 kyu
;; https://www.codewars.com/kata/568dc69683322417eb00002c

(defn triple-x? [s]
  (>= (count (re-find #"x+" s)) 3))

(test/deftest triple-x?-tests
  (test/is (= (triple-x? "") false))
  (test/is (= (triple-x? "there's no XXX here") false))
  (test/is (= (triple-x? "abraxxxas") true))
  (test/is (= (triple-x? "xoxotrololololololoxxx") false))
  (test/is (= (triple-x? "soft kitty warm kitty, xxxxx") true)))

;; Homogenous arrays
;; 7 kyu
;; https://www.codewars.com/kata/57ef016a7b45ef647a00002d
(defn filter-homogenous [sq]
  (filter #(and (seq %) (apply = (map type %))) sq))

(test/deftest sample-tests
  (test/is (= (filter-homogenous [[1 5 4] [\a 3 5] [\b] [] [\1 2 3]])
              [[1 5 4] [\b]]))
  (test/is (= (filter-homogenous [[123 234 432] ["" "abc"] [""] ["" 1] ["" "1"] []])
              [[123 234 432] ["" "abc"] [""] ["" "1"]]))
  (test/is (= (filter-homogenous [() [] ["a"] ["b"] ["c"]])
              [["a"] ["b"] ["c"]]))
  (test/is (= (filter-homogenous (list [] [1 2 3] ["z" \z] ["z"])) (list [1 2 3] ["z"])))
  (test/is (= (filter-homogenous [[{}] [#{} #{}]]) [[{}] [#{} #{}]])))

;; Gradient interpolation
;; 6 kyu
;; https://www.codewars.com/kata/58b03e48a965cb194f0002bd

(defn gradient-fn
  "Returns a function that interpolates RGB values according to a gradient definition"
  [a b]
  (let [v (mapv - b a)]
    (fn [x] (mapv + a (mapv #(int (Math/floor (* x 0.01 %))) v)))))

(test/deftest gradient-fn-tests
  (let [g (gradient-fn [255 255 255] [0 0 0])]
    (test/is (= (g 0) [255 255 255]))
    (test/is (= (g 100) [0 0 0]))
    (test/is (= (g 50) [127 127 127]))))

;; Find the odd int
;; 6 kyu
;; https://www.codewars.com/kata/54da5a58ea159efa38000836

(defn find-odd [xs]
  (->> xs frequencies (filter (comp odd? second)) ffirst))

;; very interesting solution:
;; (defn find-odd [xs]
;;   (reduce bit-xor xs))

(test/deftest find-odd-tests
  (test/are [xs answer] (= (find-odd xs) answer)
    [20 1 -1 2 -2 3 3 5 5 1 2 4 20 4 -1 -2 5] 5
    [1 1 2 -2 5 2 4 4 -1 -2 5] -1
    [20 1 1 2 2 3 3 5 5 4 20 4 5] 5
    [10] 10
    [1 1 1 1 1 1 10 1 1 1 1] 10
    [5 4 3 2 1 5 4 3 2 10 10] 1))

;; Create Phone Number
;; 6 kyu
;; https://www.codewars.com/kata/525f50e3b73515a6db000b83

;; Naive solution
;; (defn create-phone-number [[a b c d e f & rest]]
;;   (apply str "(" a b c ") " d e f "-" rest))

(defn create-phone-number [nums]
  (apply format "(%d%d%d) %d%d%d-%d%d%d%d" nums))

(test/deftest create-phone-number-tests
  (test/are [nums answer] (= (create-phone-number nums) answer)
    [1 2 3 4 5 6 7 8 9 0] "(123) 456-7890"
    [1 1 1 1 1 1 1 1 1 1] "(111) 111-1111"
    [4 7 8 1 5 7 9 9 7 1] "(478) 157-9971"
    [7 8 0 2 2 1 7 5 1 3] "(780) 221-7513"))

;; Bit Counting
;; 6 kyu
;; https://www.codewars.com/kata/526571aae218b8ee490006f4

(defn count-bits [n]
  ((frequencies (Integer/toBinaryString n)) \1 0))

;; (defn count-bits [n] (Integer/bitCount n))

(test/deftest count-bits-tests
  (test/are [n bits] (= (count-bits n) bits)
    0 0
    1234 5
    127 7))

;; 

(defn sum_fracts [l]
  (when-not (empty? l)
    (let [sum (reduce + (map #(apply / %) l))]
      (if (ratio? sum) ((juxt numerator denominator) sum) sum))))

(= (sum_fracts [[1, 2], [1, 3], [1, 4]]) [13 12])
(= (sum_fracts [[1, 3], [5, 3]]) 2)
(= (sum_fracts []) nil)
