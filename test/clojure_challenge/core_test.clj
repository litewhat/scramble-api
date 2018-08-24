(ns clojure-challenge.core-test
  (:require [clojure.test :refer :all]
            [clojure-challenge.core :refer :all]))

(deftest test-scramble?
  (testing "when lowercase letters"
    (is (= (scramble? "rekqodlw" "world") true))
    (is (= (scramble? "cedewaraaossoqqyt" "codewars") true))
    (is (= (scramble? "katas" "steak") false))
    (is (= (scramble? "ala" "ala") true))
    (is (= (scramble? "barfo" "foobar") false))))
