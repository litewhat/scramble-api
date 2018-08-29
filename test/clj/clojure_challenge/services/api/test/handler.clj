(ns clojure-challenge.services.api.test.handler
  (:require [clojure.test :refer :all]
            [clojure-challenge.services.api.handler :refer :all]
            [ring.mock.request :refer :all]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'clojure-challenge.services.config/env
                 #'clojure-challenge.services.api.handler/app)
    (f)))

(deftest test-app
  (testing "scramble route"
    (let [response (app (request :post "/scramble"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (app (request :get "/"))]
      (is (= 404 (:status response))))))
