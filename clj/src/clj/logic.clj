(ns clj.logic
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]))

(defrel father Father Child)
(defrel mother Mother Child)

(facts father [['Vito 'Michael]
               ['Vito 'Sonny]
               ['Vito 'Fredo]
               ['Michael 'Anthony]
               ['Michael 'Mary]
               ['Sonny 'Vicent]
               ['Sonny 'Francesca]
               ['Sonny 'Kathryn]
               ['Sonny 'Frank]
               ['Sonny 'Santino]])

(facts mother [['Carmela 'Michael]
               ['Carmela 'Sonny]
               ['Carmela 'Fredo]
               ['Kay 'Mary]
               ['Kay 'Anthony]
               ['Sandra 'Francesca]
               ['Sandra 'Kathryn]
               ['Sandra 'Frank]
               ['Sandra 'Santino]])

(defn parent [p child]
  (conde
   [(father p child)]
   [(mother p child)]))

(comment
  
  (run* [r]
    (fresh [x y]
      (resto '(grape raisin pair) x)
      (firsto '((a) (b) (c)) y)
      (== (lcons x y) r)))


  ;; unification
  (run* [q]
    (== q 1))

  ;; fresh
  (run* [q]
    (fresh [a]
      (== q a)))

  (run* [q]
    (fresh [a]
      (== a 1)
      (== q a)))

  ;; conde
  (run* [q]
    (conde
     [(== q 1)]
     [(== q 2)]))

  (run* [q]
    (fresh [a]
      (conde
       [(== a 1) (== q a)]
       [(== a 2) (== q a)]
       [(== 1 1) (== q a)]
       [(== 1 2) (== q a)])))

  ;; examples using appendo
  (run* [q]
    (fresh [a b]
      (== a '(1 2 3))
      (== b '(4 5 6))
      (appendo a b q )))

  (run* [q]
    (fresh [a b]
      (== a '(1 2 3))
      (== b '(4 5 6))
      (appendo a q b)))

  (run* [q]
    (fresh [a b c d e]
      (== a '(1 2 3))
      (== e '(1 2 3 4 5 6))
      (appendo a b c)
      (appendo c d e)
      (== q [b d])))

  (defn sublisto
    [x y]
    (fresh [a b c]
      (appendo a b y)
      (appendo x c b)))

  (run 5 [q]
    (sublisto q [1 2 3 4 5]))
  
  )
