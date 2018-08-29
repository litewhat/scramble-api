(ns clojure-challenge.test.services.api.handler
  (:require [clojure.test :refer :all]
            [clojure-challenge.services.api.handler :refer :all]
            [ring.mock.request :as mock]
            [mount.core :as mount]
            [cheshire.core :as cheshire])
  (:import (clojure.lang Keyword)))


(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(defn make-api-request-mock [^Keyword method ^String url body-params]
  (-> (mock/request method url)
      (mock/content-type "application/json")
      (mock/body (cheshire/generate-string body-params))))


(use-fixtures
  :once
  (fn [f]
    (mount/start #'clojure-challenge.services.config/env
                 #'clojure-challenge.services.api.handler/app)
    (f)))

(deftest scramble-route
  (testing "when valid params provided"
    (let [body-params {:str1 "tasak" :str2 "kaset"}
          request     (make-api-request-mock :post "/api/scramble" body-params)
          response    (app request)
          body        (parse-body (:body response))]
      (is (= 200 (:status response)))
      (is (= false body)))

    (let [body-params {:str1 "cebownafjeodrsasqp" :str2 "codepen"}
          request     (make-api-request-mock :post "/api/scramble" body-params)
          response    (app request)
          body        (parse-body (:body response))]
      (is (= 200 (:status response)))
      (is (= true body))))

  (comment
    (testing "when invalid params provided"
      (let [body-params {:str1 1 :str2 "codepen"}
            request     (make-api-request-mock :post "/api/scramble" body-params)
            response    (app request)
            body        (parse-body (:body response))]
        (is (= 400 (:status response)))
        (is (= {:errors nil} body)))

      (let [body-params {:str1 1 :str2 1}
            request     (make-api-request-mock :post "/api/scramble" body-params)
            response    (app request)
            body        (parse-body (:body response))]
        (is (= 400 (:status response)))
        (is (= {:errors nil} body)))

      (let [body-params {:str2 1}
            request     (make-api-request-mock :post "/api/scramble" body-params)
            response    (app request)
            body        (parse-body (:body response))]
        (is (= 400 (:status response)))
        (is (= {:errors nil} body)))

      (let [body-params {:str1 "abracadabra" :str2 nil}
            request     (make-api-request-mock :post "/api/scramble" body-params)
            response    (app request)
            body        (parse-body (:body response))]
        (is (= 400 (:status response)))
        (is (= {:errors nil} body)))))
  )

(deftest test-app

  (testing "not-found route"
    (let [response (app (mock/request :get "/"))]
      (is (= 404 (:status response)))))
  )