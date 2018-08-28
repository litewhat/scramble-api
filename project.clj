(defproject clojure-challenge "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :main clojure-challenge.core

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clj-time "0.14.4"]
                 [compojure "1.6.1"]
                 [cprop "0.1.11"]
                 [funcool/struct "1.3.0"]
                 [luminus-immutant "0.2.4"]
                 [luminus/ring-ttl-session "0.3.2"]
                 [markdown-clj "1.0.2"]
                 [metosin/compojure-api "1.1.12"]
                 [metosin/muuntaja "0.5.0"]
                 [metosin/ring-http-response "0.9.0"]
                 [mount "0.1.13"]
                 [nrepl "0.4.4"]
                 [org.clojure/tools.cli "0.3.7"]
                 [org.clojure/tools.logging "0.4.1"]
                 [org.webjars.bower/tether "1.4.4"]
                 [org.webjars/bootstrap "4.1.3"]
                 [org.webjars/font-awesome "5.2.0"]
                 [org.webjars/jquery "3.3.1-1"]
                 [org.webjars/webjars-locator "0.34"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-defaults "0.3.2"]
                 [selmer "1.12.0"]]

  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.23.0"]
                             ]}})
