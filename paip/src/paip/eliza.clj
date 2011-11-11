(ns paip.eliza)

(defn variable? [x]
  (and (symbol? x) (= (-> x str (nth 0)) \?)))

(defn elem-match [pattern input]
  (if (variable? pattern)
    true
    (= pattern input)))

(defn pat-match [pattern input]
  (if (and (seq pattern) (seq input))
    (and (elem-match (first pattern) (first input))
         (pat-match (rest pattern) (rest input)))
    (= pattern input)))
