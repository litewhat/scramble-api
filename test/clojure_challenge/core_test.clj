(ns clojure-challenge.core-test
  (:require [clojure.test :refer :all]
            [clojure-challenge.core :refer :all]))

(deftest test-scramble?
  (testing "when lowercase letters from a to z"
    (is (= true  (scramble? "rekqodlw" "world")))
    (is (= true  (scramble? "cedewaraaossoqqyt" "codewars")))
    (is (= false (scramble? "katas" "steak")))
    (is (= true  (scramble? "ala" "ala")))
    (is (= false (scramble? "barforu" "foobar"))))

  (testing "when args other than strings"
    (let [result (scramble? nil nil)]
      (is (not= '(nil nil) (:clojure-challenge.core/errors result)))))

  ;(testing "when lowercase letters not from a to z"
    ;(is (= nil (scramble? "ąśąśśś" "world")))
    ;(is (= nil (scramble? "ąśąśśś" "world")))
    ;(is (= nil (scramble? "ąśąśśś" "world")))
    ;(is (= nil (scramble? "ąśąśśś" "world")))
    ;
    ;(is (= nil (scramble? nil "world")))
    ;(is (= nil (scramble? nil nil)))

    ;)

  ;  (is (= nil (scramble? "cedewaraaossoqqyt" "codewars")))
  ;  (is (= nil (scramble? "katas" "steak")))
  ;  (is (= nil (scramble? "ala" "ala")))
  ;  (is (= nil (scramble? "barforu" "foobar"))))
  ;
  )

(deftest test-valid-to-scramble?
  (is (= false (valid-to-scramble? "uashiduh23rjsi3b5h23bj2hbkjh23b4kj2h3")))
  (is (= true  (valid-to-scramble? "uashiduhrjsibhbjhbkjhbkjh")))
  (is (= false (valid-to-scramble? "uashiduhrjs % {i} -> 0bhbjhbkjhbSkjh")))
  (is (= false (valid-to-scramble? "ASDASDASDAWRAEFSSSE$%#Q$#$T")))
  (is (= false (valid-to-scramble? "asdasdas ")))
  (is (= false (valid-to-scramble? "ółść")))
  (is (= false (valid-to-scramble? nil)))
  (is (= false (valid-to-scramble? ""))))
