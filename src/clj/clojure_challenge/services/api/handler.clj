(ns clojure-challenge.services.api.handler
  (:require [clojure-challenge.services.api.middleware :as middleware]
            [clojure-challenge.services.api.routes.services :refer [service-routes]]
            [clojure-challenge.services.api.env :refer [defaults]]
            [compojure.core :refer [routes wrap-routes]]
            [ring.util.http-response :as response]
            [compojure.route :as route]
            [mount.core :as mount]))

(mount/defstate init-app
  :start ((or (:init defaults) identity))
  :stop  ((or (:stop defaults) identity)))

(mount/defstate app
  :start
  (middleware/wrap-base
    (routes
      #'service-routes
      (route/not-found
        "page not found"))))

