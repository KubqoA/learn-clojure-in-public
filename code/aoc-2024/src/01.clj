(ns aoc-2024.01
  (:require
   [clojure.java.io :as io]
   [clojure.string :as string]))


(def input (line-seq (io/reader (io/resource "01.txt"))))
(def input [
  "3   4"
  "4   3"
  "2   5"
  "1   3"
  "3   9"
  "3   3"])

;; 1st part
(->> input
     (map #(string/split % #"\s+"))
     (map (fn [nums] (map #(Long/parseLong %) nums)))
     ((juxt (partial map first) (partial map second)))
     (map sort)
     (apply map -)
     (map Math/abs)
     (reduce +))

;; 2nd part
(let [[list1 list2] (->> input
                         (map #(string/split % #"\s+"))
                         (map (fn [nums] (map #(Long/parseLong %) nums)))
                         ((juxt (partial map first) (partial map second))))
     counter2 (frequencies list2)]
  (->> list1
       (map (fn [x] (* x (get counter2 x 0))))
       (reduce +)))
