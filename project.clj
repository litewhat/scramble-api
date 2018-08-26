(defproject clojure-challenge "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :main clojure-challenge.core
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/math.combinatorics "0.1.4"]]
  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.23.0"]]}})
