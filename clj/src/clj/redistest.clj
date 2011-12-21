(ns clj.redistest
  (:require [clj-redis.client :as redis]))

(def db (redis/init))

(defn init-publisher []
  (def publisher
    (Thread.
     #(loop [x 0]
        (redis/publish db "counter" (str x))
        (Thread/sleep 1000)
        (recur (inc x))))))

(defn init-subscriber []
  (def subscriber
    (Thread.
     #(redis/subscribe db ["counter"] 
                       (fn [ch msg]
                         (println (str msg)))))))
