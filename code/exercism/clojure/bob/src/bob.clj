(ns bob
  (:require [clojure.string :as str]))

(declare silence? yell? question? question-yell?)

(defn response-for [s]
  (let [s (str/trim s)]
    (cond
      (silence? s) "Fine. Be that way!"
      (question-yell? s) "Calm down, I know what I'm doing!"
      (question? s) "Sure."
      (yell? s) "Whoa, chill out!"
      :else "Whatever."
    )
  )
)

(defn- silence? [s] (str/blank? s))
(defn- yell? [s] (and (re-find #"[A-Z]" s) (not (re-find #"[a-z]" s))))
(defn- question? [s] (= \? (last s)))
(defn- question-yell? [s] (or (str/ends-with? s "?!") (and (question? s) (yell? s))))