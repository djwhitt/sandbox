(ns clj.tictactoe
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]))

(defrel left-of Position Position)

(facts left-of [[[:a 1] [:a 2]]
                [[:a 2] [:a 3]]
                [[:b 1] [:b 2]]
                [[:b 2] [:b 3]]
                [[:c 1] [:c 2]]
                [[:c 2] [:c 3]]])

(defn rowo [b w]
  (fresh [p1 p2 p3]
    (left-of p1 p2)
    (left-of p2 p3)
    (project [b p1 p2 p3]
      (!= w nil)
      (== w (b p1))
      (== w (b p2))
      (== w (b p3)))))

(comment
  (run* [q]
    (fresh [b w]
      (== {[:a 1] :x, [:a 2] :e, [:a 3] :e
           [:b 1] :o, [:b 2] :o, [:b 3] :o
           [:c 1] :x, [:c 2] :x, [:c 3] :x} b)
      (rowo b w)
      (== w q)))
  )
