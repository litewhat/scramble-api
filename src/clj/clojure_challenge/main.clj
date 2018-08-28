(ns clojure-challenge.main
  (:require [clojure-challenge.services.api.app :refer [start-app]]))

(defn -main [& args]
  (start-app args))
