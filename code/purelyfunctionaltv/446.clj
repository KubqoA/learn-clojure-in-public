; Longest Alternating Substring

; Write a function that takes a string of digits and returns the longest
; substring that contains only alternating odd/even digits. If two substrings
; have the same length, return the one that appears first.

(defn longest-alt-subs [s]
 (let [numbers (map #(- (int %) (int \0)) s)
       pairs   (partition 2 1 numbers)
       alts    (map (fn [[x y]] (if (odd? x) (even? y) (odd? y))) pairs)
       seqs    (partition-by true? alts)
       ]
      seqs))

; Examples

(longest-alt-subs "") ;=> ""
(longest-alt-subs "1") ;=> "1"
(longest-alt-subs "123") ;=> "123"
(longest-alt-subs "13") ;=> "1"
(longest-alt-subs "122381") ;=> "2381"

