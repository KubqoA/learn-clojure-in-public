;; Atbash Cipher

;; The Atbash Cipher is simple: replace every letter with its "mirror" in the
;; alphabet. A is replaced by Z. B is replaced by Y. Etc.
;; Write a function to calculate it.

(defn char-range [from to] (map char (range (int from) (+ (int to) 1))))
(defn mirror-part [xs] (zipmap xs (reverse xs)))
(def mirror (merge (mirror-part (char-range \A \Z)) (mirror-part (char-range \a \z))))

(defn atbash [s] (apply str (replace mirror s)))

;; Examples
(atbash "") ;=> ""
(atbash "hello") ;=> "svool"
(atbash "Clojure") ;=> "Xolqfiv"
(atbash "Yo!") ;=> "Bl!"

;; https://gist.github.com/ericnormand/4bca050e029545069d0e84b827fc8123
