;; Dealing elements

; There's a function in clojure.core called partition-all. It creates subsequences of a given maximum size.

(partition-all 3 [1 2 3 4 5 6 7 8]);=> [[1 2 3] [4 5 6] [7 8]]

; Notice that the first sequence gets the first three elements, the second sequence gets the second three elements, etc. We could get the original sequence again by concatenating the sequences back together.

; Your task is to write a function that deals out the cards more evenly. That is, the first element goes into the first sequence, the second element goes into the second sequence, etc. We're going to write two versions of this function.

; Version one takes the maximum size of the subsequence. That means the number of subsequences will depend on the size of the given sequence.
; defined below

; Note that we can put these sequences back together with interleave (except for the behavior when you have a short sequence).

; Version two takes the number of subsequences. It is variable in the size of the subsequence.

(defn deal-out [parititons-count coll]
  (->> (range parititons-count)
       (mapv #(vec (take-nth parititons-count (drop % coll))))))

;; deal out 4 subsequences
(deal-out 4 [1 2 3 4 5 6 7 8]) ;=> [[1 5] [2 6] [3 7] [4 8]]

; Note that this also can be put back together with interleave.

(defn deal-max [n coll]
 (deal-out (Math/ceil (/ (count coll) n)) coll))

;; at most 3 elements in each subsequence
(deal-max 3 [1 2 3 4 5 6 7 8]) ;=> [[1 4 7] [2 5 8] [3 6]]
