(ns user
  (:require [clojure-challenge.services.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [clojure-challenge.main :refer [start-app]]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'clojure-challenge.main/repl-server))

(defn stop []
  (mount/stop-except #'clojure-challenge.main/repl-server))

(defn restart []
  (stop)
  (start))


