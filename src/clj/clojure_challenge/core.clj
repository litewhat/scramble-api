(ns clojure-challenge.core
  (:require [clojure.spec.alpha :as spec])
  (:gen-class))


;; Preds

(defn gte-and-not-nil? [x y]
  "Returns true if x is not nil and is greater or equal y, otherwise
  returns false."
  (if x (>= x y) false))

(defn lowercase? [^String s]
  "Returns true if given string s contains only lowercase letters
  from a to z, otherwise returns false."
  (if (re-matches #"[a-z]+" (or s "")) true false))


;; Scramble?

(spec/def ::scramble?-args lowercase?)

(defn scramble?
  "Returns true if a portion of str1 characters can be rearranged
  to match str2, otherwise returns false."
  [charsets]
  (->> charsets
       (map    #(map (frequencies %) (second charsets)))
       (apply  #(map gte-and-not-nil? %1 %2))
       (every? true?)))

(def validate-scramble-inputs (partial spec/explain-data ::scramble?-args))

(defn scramble
  "if input strings are valid it runs calls scramble? function. Otherwise
  return map of errors (see ::scramble?-args spec.)"
  [^String str1 ^String str2]
  (let [problems (map validate-scramble-inputs [str1 str2])]
    (if (every? nil? problems)
      (scramble? [str1 str2])
      {::errors problems})))


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
  (let [[available-letters searched-word] args
        execution-time (benchmark (apply scramble args))]
    (println (str "Finding " (count searched-word) "-letters word "
                  "in " (count available-letters) " available letters took "
                  execution-time " miliseconds."))))
