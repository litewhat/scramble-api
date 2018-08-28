(ns user
  (:require [clojure-challenge.services.api.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [clojure-challenge.services.api.app :refer [start-app]]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'clojure-challenge.services.api.app/repl-server))

(defn stop []
  (mount/stop-except #'clojure-challenge.services.api.app/repl-server))

(defn restart []
  (stop)
  (start))


