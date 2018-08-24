(ns clojure-challenge.core
  (:require [clojure.math.combinatorics :as combo]))

;; Clojure Challenge

(defn scramble?
  [letters word]
  (let [req-chars (set word)
        init      (for [char-seq [letters word]]
                    (map (frequencies char-seq) req-chars))
        pred-fn   (fn [x y]
                    (if x
                      (>= x y)
                      false))]
    (->> init
         (apply (fn [coll1 coll2]
                  (map pred-fn coll1 coll2)))
         (every? true?))))


;; Benchmarking

(defmacro benchmark
  "Evaluates expr and returns the time in milliseconds it took."
  [expr]
  `(let [start# (. System (nanoTime))]
     ~expr
     (/ (double
          (- (. System (nanoTime))
             start#))
        1000000.0)))


;; Main

(defn -main [& args]
  (println "Hello! :)")
  (let [available-letters (apply str (repeat 1000000 "xzvxvvxffgfdjgopoisehrugidhlkjghlkhjslkjgefasdfsadfwfsadfasfdeasdfasdlfaafsdisaffsdgsdfsdfnadssadsa"))
        searched-word (apply str (repeat 7000 "ewelina"))
        execution-time (benchmark
                         (scramble? available-letters
                                    searched-word))]
    (println (str "Finding " (count searched-word) "-letters word "
                  "in " (count available-letters) " available letters took "
                  execution-time " miliseconds."))))
