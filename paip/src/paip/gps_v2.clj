(ns paip.gps-v2
  (:use [clojure.set]
        [clojure.tools.trace]
        [paip.debug]))

(def ^:dynamic *ops* nil)

(declare appropriate?)
(declare apply-op)
(declare achieve)
(declare achieve-each)
(declare achieve-all)

(defn member? [x coll]
  (some #(= x %) coll))

(defn starts-with? [coll x]
  (and (sequential? coll) (= (first coll) x)))

(defn executing? [x]
  (starts-with? x :executing))

(defn op [action & {:keys [preconds add-list del-list]}]
  {:action action
   :preconds preconds
   :add-list (cons [:executing action] add-list)
   :del-list del-list})

(defn orderings [l]
  (if (> (count l) 1)
    [l (reverse l)]
    [l]))

(defn achieve-all [state goals goal-stack]
  "Achieve each goal, trying several orderings."
  (some (fn [goals] (achieve-each state goals goal-stack))
        (orderings goals)))

(defn achieve-each [state goals goal-stack]
  (loop [[current-goal & remaining-goals] goals
         current-state state]
    (if (and current-goal current-state)
      (recur remaining-goals (achieve current-state current-goal goal-stack))
      (when (subset? (set goals) (set current-state))
        current-state))))

(defn appropriate-ops [goal state]
  (sort-by (fn [op]
             (count (remove #(member? % state)
                            (:preconds op))))
           (filter #(appropriate? goal %) *ops*)))

(defn achieve [state goal goal-stack]
  (dbg-indent :gps (count goal-stack) "Goal: ~a" goal)
  (cond (member? goal state) state
        (member? goal goal-stack) nil
        true (some #(apply-op state goal % goal-stack)
                   (appropriate-ops goal state))))

(defn apply-op [state goal op goal-stack]
  (dbg-indent :gps (count goal-stack) "Consider: ~a" (:action op))
  (let [state2 (achieve-all state (:preconds op)
                            (cons goal goal-stack))]
    (when state2
      (dbg-indent :gps (count goal-stack) "Action: ~a" (:action op))
      (concat (remove #(member? % (:del-list op)) state2)
              (:add-list op)))))

(defn appropriate? [goal op]
  (member? goal (:add-list op)))

(defn action? [x]
  (or (= x [:start]) (executing? x)))

(defn gps [state goals ops]
  (binding [*ops* ops]
    (filter action? (achieve-all (cons [:start] state) goals nil))))

(def school-ops
  [(op :drive-son-to-school
       :preconds [:son-at-home :car-works]
       :add-list [:son-at-school]
       :del-list [:son-at-home])
   (op :shop-installs-battery
       :preconds [:car-needs-battery :shop-knows-problem :shop-has-money]
       :add-list [:car-works])
   (op :tell-shop-problem
       :preconds [:in-communication-with-shop]
       :add-list [:shop-knows-problem])
   (op :telephone-shop
       :preconds [:know-phone-number]
       :add-list [:in-communication-with-shop])
   (op :look-up-number
       :preconds [:have-phone-book]
       :add-list [:know-phone-number])
   (op :give-shop-money
       :preconds [:have-money]
       :add-list [:shop-has-money]
       :del-list [:have-money])
   (op :ask-phone-number
       :preconds [:in-communication-with-shop]
       :add-list [:know-phone-number])])

(def banana-ops
  [(op :climb-on-chair
       :preconds [:chair-at-middle-room :at-middle-room :on-floor]
       :add-list [:at-bananas :on-chair]
       :del-list [:at-middle-room :on-floor])
   (op :push-chair-from-door-to-middle-room
       :preconds [:chair-at-door :at-door]
       :add-list [:chair-at-middle-room :at-middle-room]
       :del-list [:chair-at-door :at-door])
   (op :walk-from-door-to-middle-room
       :preconds [:at-door :on-floor]
       :add-list [:at-middle-room]
       :del-list [:at-door])
   (op :grasp-bananas
       :preconds [:at-bananas :empty-handed]
       :add-list [:has-bananas]
       :del-list [:at-door])
   (op :drop-ball
       :preconds [:has-ball]
       :add-list [:empty-handed]
       :del-list [:has-ball])
   (op :eat-bananas
       :preconds [:has-bananas]
       :add-list [:empty-handed :not-hungry]
       :del-list [:has-bananas :hungry])])

(defn make-maze-op [here there]
  (op [:move :from here :to there]
      :preconds [[:at here]]
      :add-list [[:at there]]
      :del-list [[:at here]]))

(defn make-maze-ops [pair]
  [(make-maze-op (first pair) (second pair))
   (make-maze-op (second pair) (first pair))])

(def maze-ops
  (mapcat make-maze-ops
          [[1 2] [2 3] [3 4] [4 9] [9 14] [9 8] [8 7] [7 12] [12 13]
           [12 11] [11 6] [11 16] [16 17] [17 22] [21 22] [22 23]
           [23 18] [23 24] [24 19] [19 20] [20 15] [15 10] [10 5] [20 25]]))

(defn destination [action]
  (-> (second action) (nth 4)))

(defn find-path [start end]
  (let [results (gps [[:at start]] [[:at end]] maze-ops)]
    (when results
      (cons start 
            (map destination (remove #(= [:start] %) results))))))

(defn move-ons [a b c]
  (if (= b :table)
    [[a :on c]]
    [[a :on c] [:space :on b]]))

(defn move-op [a b c]
  (op [:move a :from b :to c]
      :preconds [[:space :on a] [:space :on c] [a :on b]]
      :add-list (move-ons a b c)
      :del-list (move-ons a c b)))

(defn make-block-ops [blocks]
  (reverse ;; to get same order as book
   (apply concat
          (for [a blocks, b blocks,
                :when (not= a b)]
            (concat (for [c blocks
                          :when (and (not= c a) (not= c b))]
                      (move-op a b c))
                    [(move-op a :table b)
                     (move-op a b :table)])))))
