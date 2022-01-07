(require '[clojure.set :as set])

(defn all-present?
  [bigrams sentence]
  (let [bigrams-in-sentence (set (partition 2 1 sentence))
        bigrams (set (map seq bigrams))]
       (set/subset? bigrams bigrams-in-sentence)))

(all-present? ["st" "tr"] "street") ;=> true
(all-present? ["ea" "ng" "kt"] "eating at a restaurant") ;=> false
(all-present? [] "hello!") ;=> true
