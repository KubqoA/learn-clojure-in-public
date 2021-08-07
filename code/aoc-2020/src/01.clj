(ns aoc-2020.01 
  (:require
    [clojure.java.io :as io]))

(def input
  (map #(Long/parseLong %) (line-seq (io/reader (io/resource "01.txt")))))

(for [x input
      y input
      z input
      :when (= 2020 (+ x y z))
      :when (< x y z)]
  (* x y z))
 
