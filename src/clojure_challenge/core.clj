(ns clojure-challenge.core
  (:require [clojure.spec.alpha :as spec]))

(defn gte-and-not-nil? [x y]
  "Returns true if x is not nil and is greater than y, otherwise
  returns false."
  (if x (>= x y) false))


;; Clojure Challenge

(defn valid-to-scramble? [^String s]
  "Returns true if given string s contains only lowercase letters
  from a to z."
  (if (re-matches #"[a-z]+" s) true false))

(spec/def ::lowercase #(partial valid-to-scramble? (or % "")))

(defn scramble?
  "Returns true if a portion of str1 characters can be rearranged
  to match str2 otherwise returns false."
  [str1 str2]
  (let [args [str1 str2]]
    (->> args
         (map    #(map (frequencies %) (set str2)))
         (apply  #(map gte-and-not-nil? %1 %2))
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
  (let [available-letters (apply str (repeat 1000000 "xzvxvvxffgfdjgopoisehrugidhlkjghlkhjslkjgefasdfsadfwfsadfasfdeasdfasdlfaafsdisaffsdgsdfsdfnadssadsaessasdasdasdasdisslassssssssewasdasd"))
        searched-word     (apply str (repeat 7000 "ewelina"))
        execution-time    (benchmark (scramble? available-letters searched-word))]
    (println (str "Finding " (count searched-word) "-letters word "
                  "in " (count available-letters) " available letters took "
                  execution-time " miliseconds."))))
