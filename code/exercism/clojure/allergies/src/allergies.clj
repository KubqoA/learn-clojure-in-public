(ns allergies)

(def allergens
  {:eggs 1
   :peanuts 2
   :shellfish 4
   :strawberries 8
   :tomatoes 16
   :chocolate 32
   :pollen 64
   :cats 128})

(defn allergies [score]
  (map first (filter #(> (bit-and score (second %)) 0) allergens))
)

(defn allergic-to? [score allergy]
  (> (bit-and score (allergens allergy)) 0)
)
