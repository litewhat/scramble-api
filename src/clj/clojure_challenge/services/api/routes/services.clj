(ns clojure-challenge.services.api.routes.services
  (:require [clojure.tools.logging :as log]
            [clojure-challenge.core :refer [scramble]]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :as response]))


(defapi service-routes
  {:swagger {:ui   "/docs"
             :spec "/swagger.json"
             :data {:info {:version     "1.0.0"
                           :title       "Scramble API"
                           :description "Scramble service"}}}}

  (context "/api" []
    :tags ["scramble?"]

    (POST "/scramble" []
      :body-params [str1 :- String
                    str2 :- String]
      :summary "Calls scramble? function."
      (let [result (scramble str1 str2)
            response (if (boolean? result)
                       (response/ok {:result result})
                       (response/bad-request {:errors result}))]
        (log/info (str "Result: " result))
        (log/info (str "Response: " response))
        response))


    (GET "/plus" []
      :return Long
      :query-params [x :- Long, {y :- Long 1}]
      :summary "x+y with query-parameters. y defaults to 1."
      (response/ok (+ x y)))

    (POST "/minus" []
      :return Long
      :body-params [x :- Long, y :- Long]
      :summary "x-y with body-parameters."
      (response/ok (- x y)))

    (GET "/times/:x/:y" []
      :return Long
      :path-params [x :- Long, y :- Long]
      :summary "x*y with path-parameters"
      (response/ok (* x y)))

    (POST "/divide" []
      :return Double
      :form-params [x :- Long, y :- Long]
      :summary "x/y with form-parameters"
      (response/ok (/ x y)))

    (GET "/power" []
      :return Long
      :header-params [x :- Long, y :- Long]
      :summary "x^y with header-parameters"
      (response/ok (long (Math/pow x y))))))
