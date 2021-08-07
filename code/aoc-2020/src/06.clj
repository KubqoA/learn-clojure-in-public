(ns aoc-2020.06
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))

; Basic ‹frequencies› counts also \newline, we don't want that
(defn answers-freq [group] (-> group frequencies (dissoc \newline)))

(def input
  (str/split (slurp (io/resource "06.txt")) #"\n\n"))

(defn number-of-questions [group] (-> group answers-freq count))

(apply + (map number-of-questions input))

; Part 2

(defn number-of-questions2 [group]
  (let [number-of-people (count (str/split-lines group))]
    (->> group answers-freq
         (filter #(= number-of-people (second %)))
         count)))

(apply + (map number-of-questions2 input))
