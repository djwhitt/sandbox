(ns clj.tictactoe
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]))

(defrel coordinate Position Coordinate)
(defrel left-of Position Position)
(defrel above Position Position)

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

(facts above [[0 3] 
              [1 4]
              [2 5]
              [3 6]
              [4 7]
              [5 8]])

(defn spaceo [x]
  (== x :_))

(defn pieceo [x]
  (conde
   ((== x :x))
   ((== x :o))))

(def playero pieceo)

(defn squareo [x]
  (conde
   ((spaceo x))
   ((pieceo x))))

(defmacro boardo [b]
  (let [ps ['p0 'p1 'p2 'p3 'p4 'p5 'p6 'p7 'p8]]
    `(fresh ~ps
       (== ~b ~ps)
       ~@(map (fn [p] (list 'squareo p)) ps))))

(defn rowo [b r]
  (fresh [p1 p2 p3]
    (left-of p1 p2)
    (left-of p2 p3)
    (project [b p1 p2 p3]
      (== r [(b p1) (b p2) (b p3)]))))

(defn colo [b c]
  (fresh [p1 p2 p3]
    (above p1 p2)
    (above p2 p3)
    (project [b p1 p2 p3]
      (== c [(b p1) (b p2) (b p3)]))))

(defn moveo [b p]
  (fresh [c x]
    (coordinate p c)
    (project [b p]
      (== (b p) x)
      (spaceo x))))

;; minimax
;;   input
;;     board
;;     player

(comment
  (run* [q]
    (fresh [b r]
      (== [:x :_ :_
           :o :o :o
           :x :x :x] b)
      (rowo b r)
      (boardo b)
      (== q r)))

  (run* [q]
    (fresh [b c]
      (== [:x :_ :_
           :o :o :o
           :x :x :x] b)
      (colo b c)
      (boardo b)
      (== q c)))

  (run* [q]
    (fresh [b p]
      (== [:x :_ :_
           :o :o :o
           :x :x :x] b)
      (moveo b p)
      (== q p)))

  (run* [q]
    (squareo q))

  (run 12 [q]
    (boardo q))

  ;; generates: ([a 3] [b 3] [c 3] [a 2] ... )
  (let [rows   (range 3 0 -1)
        cols   ['a 'b 'c]
        coords (for [x rows y cols] [y x])]
    coords)
  )
