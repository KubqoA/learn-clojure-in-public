(ns aoc-2020.05 
  (:require
    [clojure.java.io :as io]))

(def input (-> "05.txt" io/resource io/reader line-seq))

(defn shift-seq [begin] (iterate #(/ % 2) begin))
(defn shift-range-end   [amount] (fn [[a b]] (list a (- b amount))))
(defn shift-range-start [amount] (fn [[a b]] (list (+ a amount) b)))

(def op {\F shift-range-end
         \B shift-range-start
         \L shift-range-end
         \R shift-range-start})

(defn mover [moves initial-shift]
  (reduce #(%2 %1) (list 0 (dec (* 2 initial-shift))) (map #((op %1) %2) moves (shift-seq initial-shift))))

(defn row [moves] (first (mover (take 7 moves) 64)))
(defn col [moves] (first (mover (take-last 3 moves) 4)))
(defn seat-id [boarding-pass] (+ (* (row boarding-pass) 8) (col boarding-pass)))

(apply max (map seat-id input))

; Second part

(def seat-ids (sort (map seat-id input)))
(inc (ffirst (filter (fn [[a b]] (not= (inc a) b)) (partition 2 1 seat-ids))))

