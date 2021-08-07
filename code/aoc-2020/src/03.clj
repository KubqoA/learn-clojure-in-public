(ns aoc-2020.03 
  (:require
    [clojure.java.io :as io]))

(def input
  (->> "03.txt" io/resource io/reader line-seq))

(def width (count (first input)))

(defn x-pos-seq [x-step]
  (map #(rem (* x-step %) width) (range)))

(defn encountered-trees [x-step y-step]
  (count (filter #(= % \#)
                 (map #(nth %1 %2)
                      (take-nth y-step input)
                      (x-pos-seq x-step)))))

(* (encountered-trees 1 1)
   (encountered-trees 3 1)
   (encountered-trees 5 1)
   (encountered-trees 7 1)
   (encountered-trees 1 2))
