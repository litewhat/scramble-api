(ns clojure-challenge.services.api.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [clojure-challenge.services.api.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[api-service started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[api-service has shut down successfully]=-"))
   :middleware wrap-dev})
