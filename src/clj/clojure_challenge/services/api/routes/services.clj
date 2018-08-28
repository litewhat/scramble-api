(ns clojure-challenge.services.api.routes.services
  (:require [clojure-challenge.core :refer [scramble?]]
            [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]))


(defapi service-routes
  {:swagger {:ui   "/docs"
             :spec "/swagger.json"
             :data {:info {:version     "1.0.0"
                           :title       "Sample API"
                           :description "Sample Services"}}}}
  (context "/" []
    :tags ["scramble?"]

    (POST "/scramble" []
      :return Boolean
      :body-params [str1 :- String
                    str2 :- String]
      :summary "scramble? FIXME!"
      (scramble? str1 str2)))

  (context "/api" []
    :tags ["examples"]

    (GET "/plus" []
      :return Long
      :query-params [x :- Long, {y :- Long 1}]
      :summary "x+y with query-parameters. y defaults to 1."
      (ok (+ x y)))

    (POST "/minus" []
      :return Long
      :body-params [x :- Long, y :- Long]
      :summary "x-y with body-parameters."
      (ok (- x y)))

    (GET "/times/:x/:y" []
      :return Long
      :path-params [x :- Long, y :- Long]
      :summary "x*y with path-parameters"
      (ok (* x y)))

    (POST "/divide" []
      :return Double
      :form-params [x :- Long, y :- Long]
      :summary "x/y with form-parameters"
      (ok (/ x y)))

    (GET "/power" []
      :return Long
      :header-params [x :- Long, y :- Long]
      :summary "x^y with header-parameters"
      (ok (long (Math/pow x y))))))
