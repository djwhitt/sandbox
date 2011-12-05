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
   ((father p child))
   ((mother p child))))

(comment
  (run* [r]
    (fresh [x y]
      (resto '(grape raisin pair) x)
      (firsto '((a) (b) (c)) y)
      (== (lcons x y) r)))
  )
