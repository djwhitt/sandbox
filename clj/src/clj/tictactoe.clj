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

(defn pieceo [x]
  (conde
   ((== x '_))
   ((== x 'x))
   ((== x 'o))))

(defn boardo [b]
  (fresh [a3 b3 c3 a2 b2 c2 a1 b1 c1]
    (== b [a3 b3 c3 a2 b2 c2 a1 b1 c1])
    (pieceo a3)
    (pieceo b3)
    (pieceo c3)
    (pieceo a2)
    (pieceo b2)
    (pieceo c2)
    (pieceo a1)
    (pieceo b1)
    (pieceo c1)))

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

  (run 12 [q]
    (boardo q))

  ;; generates: ([a 3] [b 3] [c 3] [a 2] ... )
  (let [rows   (range 3 0 -1)
        cols   ['a 'b 'c]
        coords (for [x rows y cols] [y x])]
    coords)

  (run* [q]
    (fresh [a]
      (== a {:a 1 :b 2})
      (== a {:a q :b 2}))
    )

  )
