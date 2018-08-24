(ns clojure-challenge.core
  (:require [clojure.math.combinatorics :as combo]))

(defn scramble?
  [letters word]
  (let [searched (set word)]
    (every? true?
            (apply
              (fn [coll1 coll2]
                (map
                  (fn [n1 n2]
                    (if n1
                      (>= n1 n2)
                      false))
                  coll1 coll2))
              (for [char-seq [letters word]]
                (map (frequencies char-seq) searched))))))

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


(defn scramble?-transducers
  [letters word]
  (let [searched (set word)]
    (every? true?
            (apply
              (fn [coll1 coll2]
                (map
                  (fn [n1 n2]
                    (if n1
                      (>= n1 n2)
                      false))
                  coll1 coll2))
              (let [available letters
                    required word]
                (for [char-seq [available required]]
                  (map (frequencies char-seq) searched)))))))

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
  (println "Hello! :)"))
