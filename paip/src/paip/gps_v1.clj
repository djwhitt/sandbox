(ns paip.gps-v1
  (:use [clojure.set]
        [clojure.tools.trace]))

;;; NOTE: This doesn't work exactly like Norvig's original. I use
;;; unordered sets where he uses ordered lists. This makes some of the
;;; problems he describes harder to demonstrate.

(def ^:dynamic *state* nil)
(def ^:dynamic *ops* nil)

(declare achieve)
(declare achieve-all)
(declare appropriate?)
(declare apply-op)

(defn gps [state goals ops]
  (binding [*state* state
            *ops* ops]
    (when (achieve-all goals) :solved)))

(defn achieve [goal]
  (or (contains? *state* goal)
      (some apply-op
            (filter #(appropriate? goal %) *ops*))))

(defn achieve-all [goals]
  (and (every? achieve goals) (subset? goals *state*)))

(defn appropriate? [goal op]
  (contains? (:add-list op) goal))

(defn apply-op [op]
  (when (achieve-all (:preconds op))
    (println (str "Executing " (:action op)))
    (set! *state* (difference *state* (:del-list op)))
    (set! *state* (union *state* (:add-list op)))
    true))

(def school-ops
  [{:action :drive-son-to-school
    :preconds #{:son-at-home :car-works}
    :add-list #{:son-at-school}
    :del-list #{:son-at-home}}
   {:action :shop-installs-battery
    :preconds #{:car-needs-battery :shop-knows-problem :shop-has-money}
    :add-list #{:car-works}}
   {:action :tell-shop-problem
    :preconds #{:in-communication-with-shop}
    :add-list #{:shop-knows-problem}}
   {:action :telephone-shop
    :preconds #{:know-phone-number}
    :add-list #{:in-communication-with-shop}}
   {:action :look-up-number
    :preconds #{:have-phone-book}
    :add-list #{:know-phone-number}}
   {:action :give-shop-money
    :preconds #{:have-money}
    :add-list #{:shop-has-money}
    :del-list #{:have-money}}
   {:action :ask-phone-number
    :preconds #{:in-communication-with-shop}
    :add-list #{:know-phone-number}}
   ])
