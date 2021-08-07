(ns aoc-2020.02 
  (:require
    [clojure.java.io :as io]))

; (def demo-input
;   '([1 3 \a "abcde"]
;     [1 3 \b "cdefg"]
;     [2 9 \c "ccccccccc"]))

(defn parse-line [line]
  (let [[_ n m c password] (re-find #"(\d+)-(\d+) (.): (.*)" line)]
    (vector (Long/parseLong n) (Long/parseLong m) (first c) password)))

(def input
 (->> "02.txt" io/resource io/reader line-seq (map parse-line)))

; (defn is-valid? [min max letter password]
;   (<= min ((frequencies password) letter 0) max))

(defn is-valid? [pos-a pos-b letter password]
  (let [pos-a? (= letter (nth password (dec pos-a))) pos-b? (= letter (nth password (dec pos-b)))]
    (not= pos-a? pos-b?)))

(count (filter #(apply is-valid? %) input))
