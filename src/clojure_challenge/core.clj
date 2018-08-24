(ns clojure-challenge.core
  (:require [clojure.math.combinatorics :as combo]))

(defn scramble?
  [^String str1 ^String str2]
  (let [combos (combo/combinations (seq str1) (count str2))]
    (true? (seq (filter (fn [combo]
                          (= (frequencies combo)
                             (frequencies (seq str2))))
                        combos)))))

(defn scramble?-optimized-prototype
  [^String letters ^String word]
  (let [searched (select-keys
                   (frequencies word)
                   (keys (frequencies word)))
        available (select-keys
                    (frequencies letters)
                    (keys (frequencies word)))]
    (every?
      true?
      (map
        (fn [[_ searched] [_ available]]
          (if available
            (>= available searched)
            false))
        searched
        available))))


(clojure.pprint/pprint
  (let [letters "katas"
        word    "steak"]
    (let [searched (select-keys
                     (frequencies word)
                     (keys (frequencies word)))
          available (select-keys
                      (frequencies letters)
                      (keys (frequencies word)))
          result    (every?
                      true?
                      (map
                        (fn [[se-name searched] [av-name available]]
                          (assert (= se-name av-name))
                          (if available
                            (>= available searched)
                            false))
                        searched
                        available))]
      {:result result
       :available available
       :searched searched})))


;; test cases

(comment
  (scramble? "rekqodlw" "world")
  (scramble? "cedewaraaossoqqyt" "codewars")
  (scramble? "katas" "steak"))


;; benchmark

(defmacro benchmark
  "Evaluates expr and returns the time in milliseconds it took."
  [expr]
  `(let [start# (. System (nanoTime))]
     ~expr
     (/ (double
          (- (. System (nanoTime))
             start#))
        1000000.0)))


;; tasks

(def task
  (future
    (benchmark
      (scramble?
        "iufhsljhdfbkjhsabkdhasbkjfdbhsfkjsdhbfkasjbdhskjflkjbashkdfjhsbkfjhbdslkjfdjkglkdfsjghlkjsdfghlkdjfhglkdjshfglkjdshglkjshdfglkjdshfklgjhdsflkjghldkjfhglsdkjfhglsdkfjhglkdsfjghlkdsjhglkadsjflaksjfliewuahtoieruthoieursthoiweauhfliuahewlfskdjnlaijniujablisfdhaucehomiaurhcgoyusnreouchaouhoeaiurchomiaseurchvoiuahrociuhoamiruhaonyriyuhficahyuiabvfoauhbofijnoifjnvsOIJFndozijfnfijafnoweriunfaiowejnmfpo;kxzdm;vkljsdnflijaernpgoiuarpjnojkdmsnv"
        "asdsadgdfgsdfasdfsdgfsdfsfuhiuhwisdhblasjfiushfoisuefiusejnraeicnoiauervnouysociuahnoricunshcroiuhosiruhnsoiuhroiusvehnouihseoircuhoinvehrovishniouheoivcuneoishu"))))


(defn -main [args]
  (clojure.pprint/pprint
   "Hello! :)"))
