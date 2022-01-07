; Product of digits of sum

; Write a function that takes one or more numbers as arguments. It should sum them, then multiply the digits. If the answer is one digit long, it's done. If it's more than one digit, repeat and multiply the digits again.

(defn sum-prod [& numbers]
 (let [sum (apply + numbers)
       digits (map #(- (int %) (int \0)) (str sum))
       prod (apply * digits)]
  (if (> prod 9) (recur [prod]) prod)))
  
; Examples

(sum-prod 4) ;=> 4
(sum-prod 10) ;=> 0 (since the sum is two digits, then 1 * 0)
(sum-prod 11 12) ;=> 6
(sum-prod 12 16 223) ;=> 0 (work it out!)
(sum-prod 1, 2, 3, 4, 5, 6)
