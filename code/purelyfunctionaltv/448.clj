; Reverse-Polish calculator

(require 'clojure.string)

(defn- step [stack x]
 (if-let [op ({"+" + "-" - "*" * "/" /} x)]
         (conj (pop (pop stack)) (op (peek (pop stack)) (peek stack)))
         (conj stack (Long/parseLong x))))

(defn rpol [str]
 (first (reduce step '() (clojure.string/split str #"\s+"))))

; Examples

(rpol "1") ;=> 1
(rpol "1 2 +") ;=> 3
(rpol "1 2 + 3 +") ;=> 6
(rpol "4 2 * 2 2 + + 8 /") ;=> 2
(rpol "1 2 3 4 5 + * +")
(rpol "2 3 * 1 - 5 /")

; Notes
; * All operations are binary.
; * There are some cases where there aren't enough arguments. You should throw an exception.
; * There are some cases where there are too many arguments. Return the result of the last operation performed.
