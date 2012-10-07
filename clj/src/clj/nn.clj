(ns clj.nn
  (:import [org.encog Encog]
           [org.encog.engine.network.activation ActivationSigmoid]
           [org.encog.ml.data MLData MLDataPair MLDataSet]
           [org.encog.ml.data.basic BasicMLDataSet]
           [org.encog.neural.networks BasicNetwork]
           [org.encog.neural.networks.layers BasicLayer]
           [org.encog.neural.networks.training.propagation.resilient
            ResilientPropagation]))


(defn make-network []
  (let [n  (BasicNetwork.)
        l1 (BasicLayer. nil true 2)
        l2 (BasicLayer. (ActivationSigmoid.) true 3)
        l3 (BasicLayer. (ActivationSigmoid.) false 1)]
    (doto n
      (.addLayer l1)
      (.addLayer l2)
      (.addLayer l3))
    (.. n getStructure finalizeStructure)
    (.reset n)
    n))

(def input
  (into-array [(double-array [0.0 0.0])
               (double-array [1.0 0.0])
               (double-array [0.0 1.0])
               (double-array [1.0 1.0])]))

(def ideal
  (into-array [(double-array [0.0])
               (double-array [1.0])
               (double-array [1.0])
               (double-array [0.0])]))

(def training-set (BasicMLDataSet. input ideal))

(defn train-network [n]
  (let [t  (ResilientPropagation. n training-set)]
    (loop [epoch 1]
      (.iteration t)
      (println (str "Epoch #" epoch " Error:" (.getError t)))
      (when (> (.getError t) 0.01)
        (recur (inc epoch))))))

(defn run-network [n]
  (println "Neural Network Results:")
  (doseq [pair training-set]
    (let [output (.compute n (.getInput pair))]
      (println (str (.. pair getInput (getData 0))
                    ","
                    (.. pair getInput (getData 1))
                    ", actual="
                    (.getData output 0)
                    ", ideal="
                    (.. pair getIdeal (getData 0)))))))
