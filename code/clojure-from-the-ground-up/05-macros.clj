(ns macros)

; find how many numbers from 0 to 9999 are palindromes

(defn is-palindrome? [num]
  (let [num-string (str num)]
    (= num-string (->> num-string reverse (apply str)))))

(->> 0
     (iterate inc)
     (take 10000)
     (filter is-palindrome?))

; Write a macro ‹id› which takes a function and a list of args: (id f a b c),
; and returns an expression which calls that function with the given args: (f a b c).

(defmacro id
  [f & args] `(~f ~@args))

(macroexpand '(id + 1 2 3))
(id + 1 2 4)

; Write a macro ‹log› which uses a var, ‹logging-enabled›, to determine whether
; or not to print an expression to the console at compile time.
; If ‹logging-enabled‹ is false, (log :hi) should macroexpand to nil.
; If ‹logging-enabled‹ is true, (log :hi) should macroexpand to (prn :hi).

(def logging-enabled true)

(defmacro log
  [msg] (if logging-enabled `(prn ~msg) nil))

(macroexpand '(log :hi))
(log "Hello world")
 
; Using the rationalize function, write a macro exact which rewrites any use of
; +, -, *, or / to force the use of ratios instead of floating-point numbers.

(defmacro exact
  [[op & args]] (conj (map rationalize args) op))

(macroexpand '(exact (+ 12.4 23)))

(* 2452.45 100)
(exact (* 2452.45 100))
