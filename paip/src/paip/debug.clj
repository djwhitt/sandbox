(ns paip.debug
  (:use [clojure.pprint]
        [clojure.set]))

(def ^:dynamic *debug-out* (get-pretty-writer *err*))

(def dbg-ids (atom #{}))

(defn dbg [id format-string & args]
  (binding [*out* *debug-out*]
    (when (contains? @dbg-ids id)
      (fresh-line)
      (apply cl-format true format-string args))))

(defn debug [& ids]
  (swap! dbg-ids #(union %1 %2) (set ids)))

(defn undebug [& ids]
  (swap! dbg-ids #(difference %1 %2) (set ids)))

(defn dbg-indent [id indent format-string & args]
  (binding [*out* *debug-out*]
    (when (contains? @dbg-ids id)
      (fresh-line)
      (dotimes [i indent] (print " "))
      (apply cl-format true format-string args))))
