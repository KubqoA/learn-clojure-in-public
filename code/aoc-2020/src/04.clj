(ns aoc-2020.04 
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))

(defn parse-entry [entry]
  (->> (str/split entry #"\s|:")
       (apply hash-map)))

(def input
  (map parse-entry (str/split (slurp (io/resource "04.txt")) #"\n\n")))

(def required-properties '("byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"))

(defn is-passport-valid? [passport]
  (every? #(contains? passport %) required-properties))

(count (filter is-passport-valid? input))

; Second part

(defn parse-long [x] (Long/parseLong x))

(defn is-passport-valid2? [{:strs [byr iyr eyr hgt hcl ecl pid]}]
  (and
    byr (<= 1920 (parse-long byr) 2002)
    iyr (<= 2010 (parse-long iyr) 2020)
    eyr (<= 2020 (parse-long eyr) 2030)
    hgt (let [[_ height unit] (re-find #"(\d+)(in|cm)" hgt)]
          (case unit
            "cm" (<= 150 (parse-long height) 193)
            "in" (<= 59 (parse-long height) 76)
            false))
    hcl (re-find #"^#[0-9a-f]{6}$" hcl)
    ecl (#{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} ecl)
    pid (re-find #"^\d{9}$" pid)))

(count (filter is-passport-valid2? input))

