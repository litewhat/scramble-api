(ns clojure-challenge.test.core
  (:require [clojure.test :refer :all]
            [clojure-challenge.core :refer :all]))


(def count-spec-problems
  (comp
    (map :clojure.spec.alpha/problems)
    (map count)))


(deftest test-scramble?
  (testing "when lowercase letters from a to z"
    (is (= true  (scramble? "rekqodlw" "world")))
    (is (= true  (scramble? "cedewaraaossoqqyt" "codewars")))
    (is (= false (scramble? "katas" "steak")))
    (is (= true  (scramble? "ala" "ala")))
    (is (= false (scramble? "barforu" "foobar"))))

  (testing "when args other than strings"
    (let [result   (scramble? nil nil)
          errors   (:clojure-challenge.core/errors result)
          problems (transduce count-spec-problems + errors)]
      (is (= 2 problems)))

    (let [result   (scramble? nil "world")
          errors   (:clojure-challenge.core/errors result)
          problems (transduce count-spec-problems + errors)]
      (is (= 1 problems)))

    (let [result   (scramble? "world" nil)
          errors   (:clojure-challenge.core/errors result)
          problems (transduce count-spec-problems + errors)]
      (is (= 1 problems)))

    (let [result   (scramble? (String/valueOf 1)
                              (String/valueOf 0.0))
          errors   (:clojure-challenge.core/errors result)
          problems (transduce count-spec-problems + errors)]
      (is (= 2 problems)))

    (is (thrown? java.lang.ClassCastException (scramble? [] "asdas")))
    (is (thrown? java.lang.ClassCastException (scramble? "dhgdfg" #{})))
    (is (thrown? java.lang.ClassCastException (scramble? '() "asdasd")))
    (is (thrown? java.lang.ClassCastException (scramble? '() {}))))

  (testing "when lowercase letters not from a to z"
    (let [result   (scramble? "ąśðąśðęæ" "world")
          errors   (:clojure-challenge.core/errors result)
          problems (transduce count-spec-problems + errors)]
      (is (= 1 problems)))

    (let [result   (scramble? "ąśðąśðęæ" "ASdasdad")
          errors   (:clojure-challenge.core/errors result)
          problems (transduce count-spec-problems + errors)]
      (is (= 2 problems)))

    (let [result   (scramble? "akfgks,sdpwqplxz" "asdwwasd ")
          errors   (:clojure-challenge.core/errors result)
          problems (transduce count-spec-problems + errors)]
      (is (= 2 problems)))

    (let [result   (scramble? "" "gbf24")
          errors   (:clojure-challenge.core/errors result)
          problems (transduce count-spec-problems + errors)]
      (is (= 2 problems)))))

(deftest test-valid-to-scramble?
  (is (= false (lowercase? "uashiduh23rjsi3b5h23bj2hbkjh23b4kj2h3")))
  (is (= true  (lowercase? "uashiduhrjsibhbjhbkjhbkjh")))
  (is (= false (lowercase? "uashiduhrjs % {i} -> 0bhbjhbkjhbSkjh")))
  (is (= false (lowercase? "ASDASDASDAWRAEFSSSE$%#Q$#$T")))
  (is (= false (lowercase? "asdasdas ")))
  (is (= false (lowercase? "ółść")))
  (is (= false (lowercase? nil)))
  (is (= false (lowercase? ""))))
