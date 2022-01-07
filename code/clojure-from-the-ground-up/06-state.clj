(ns state)

; 1.
(defn sum [start end] (reduce + (range start end)))
(time (sum 0 1e7))

(defn delayed-sum [start end] (delay (sum start end)))
(time (delayed-sum 0 1e7)) ;=> Elapsed time 0.01ms
(time @(delayed-sum 0 1e7)) ;=> Elapsed time 200ms

; 2.
(defmacro my-future [f]
  `(let [result# (promise)] 
     (.start (Thread. (fn [] (deliver result# ~f))))
     result#))

(macroexpand '(my-future (sum 0 1e7)))
(def future-sum (my-future (sum 0 1e7)))
@future-sum

; 3.
(defn split-sum [start end]
  (let [first-half  (future (sum start (/ end 2)))
        second-half (future (sum (/ end 2) end))]
    (+ @first-half @second-half)))
(time (split-sum 0 1e7))

; 4.
(defn atom-sum [start end]
  (let [sum         (atom 0)
        first-half  (future (doseq [x (range start (/ end 2))]
                              (swap! sum + x)))
        second-half (future (doseq [x (range (/ end 2) end)]
                              (swap! sum + x)))]
    @first-half @second-half @sum))
(time (atom-sum 0 1e7))

; 5.

(def parts 2)
(defn generic-split-sum [start end]
  (let [chunk-size (/ (- end start) parts)]
    (->> (range start end)
         (partition chunk-size)
         (map #(future (reduce + %)))
         (map deref)
         (apply +))))
