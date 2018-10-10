(ns clojure-challenge.services.api.middleware
  (:require [clojure-challenge.services.config :refer [env]]
            [clojure-challenge.services.api.env :refer [defaults]]
            [jumblerg.middleware.cors :refer [wrap-cors]]
            [immutant.web.middleware :refer [wrap-session]]
            [ring.middleware.flash :refer [wrap-flash]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]))


(defn wrap-base [handler]
  (-> ((:middleware defaults) handler)
      wrap-flash
      (wrap-session {:cookie-attrs {:http-only true}})
      (wrap-defaults
        (-> site-defaults
            (assoc-in [:security :anti-forgery] false)
            (dissoc :session)))
      (wrap-cors #".*localhost.*")))
