(ns clj.tictactoe
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]))

(defne rowo [b w]
  ([[w w w . ?r] _])
  ([[_ _ _ . ?r] _] (rowo ?r w)))

(defne colo [b w]
  ([[w _ _ w _ _ w . ?r] _])
  ([[_ . ?r] _] (colo ?r w)))

(comment
  (run* [q]
    (fresh [g]
      (== '(e x e
            e x e
            x x e) g)
      (colo g q)))

  (run* [q]
    (fresh [g]
      (== '(e x e
            e x e
            x e x) g)
      (colo g q)))

  (run* [q]
    (fresh [g]
      (== '(e x e
            e x e
            x x x) g)
      (rowo g q)))
  )
