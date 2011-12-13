(ns clj.tictactoe
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]))

(defrel coordinate Position Coordinate)

(defrel left-of Position Position)

(facts coordinate [[0 [0 0]]
                   [1 [1 0]]
                   [2 [2 0]]
                   [3 [0 1]]
                   [4 [1 1]]
                   [5 [2 1]]
                   [6 [0 2]]
                   [7 [1 2]]
                   [8 [2 2]]])

(facts left-of [[0 1] 
                [1 2]
                [3 4]
                [4 5]
                [6 7]
                [7 8]])

(defn pieceo [x]
  (conde
   ((== x '_))
   ((== x 'x))
   ((== x 'o))))

(defmacro boardo [b]
  (let [ps ['p0 'p1 'p2 'p3 'p4 'p5 'p6 'p7 'p8]]
    `(fresh ~ps
       (== ~b ~ps)
       ~@(map (fn [p] (list 'pieceo p)) ps))))

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
      (== [:x :e :e
           :o :o :o
           :x :x :x] b)
      (rowo b w)
      (== w q)))

  (run 12 [q]
    (boardo q))

  ;; generates: ([a 3] [b 3] [c 3] [a 2] ... )
  (let [rows   (range 3 0 -1)
        cols   ['a 'b 'c]
        coords (for [x rows y cols] [y x])]
    coords)
  )
