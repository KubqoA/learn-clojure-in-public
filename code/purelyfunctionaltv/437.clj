;; Phone to letter translation

;; Phone keypads and rotary dials have little letters on them. Most numbers
;; translate into letters. Of course, with only 10 digits, but 26 letters,
;; there is a problem: the translation from letters to numbers is lossy. When
;; translating from numbers to letters, there is always more than one possible
;; interpretation.

;; Write a function that takes a string of digits and returns a collection of
;; the possible strings of letters it corresponds to.

;; Here are the mappings you should use:

;; 1: no letters
;; 2: abc
;; 3: def
;; 4: ghi
;; 5: jkl
;; 6: mno
;; 7: pqrs
;; 8: tuv
;; 9: wxyz
;; 0: space

;; If a character appears in the input that does not have a mapping, it will
;; appear in the output untranslated.

(require '[clojure.math.combinatorics :as combo])

(def digit->letters {\2 "abc"
                     \3 "def"
                     \4 "ghi"
                     \5 "jkl"
                     \6 "mno"
                     \7 "pqrs"
                     \8 "tuv"
                     \9 "wxyz"
                     \0 " "})

(defn digits->letters [digits] (->> digits
                                    (map digit->letters)
                                    (filter seq)
                                    (apply combo/cartesian-product)
                                    (mapv #(apply str %))))

;; Examples
(digits->letters "22") ;=> ["aa" "ab" "ac" "ba" "bb" "bc" "ca" "cb" "cc"
(digits->letters "0921")

;; https://gist.github.com/ericnormand/ec3a492c760bb1623febdaf7222bfa14
