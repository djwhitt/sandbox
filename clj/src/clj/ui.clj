(ns sandbox.ui
  (:require [fs.core :as fs]
            [clojure.string :as s])
  (:use [seesaw core dev]
        [clj-ns-browser.sdoc]))

(defn read-config []
  (-> (str (System/getenv "HOME") "/.recordsrc")
      slurp
      read-string))

(defn parse-file-name [f]
  (let [bn                (fs/base-name f)
        [n ext]           (fs/split-ext f)
        [date-str & tags] (s/split n #"-")
        tags              (set tags)
        [year month day]  (s/split date-str #"\.")]
    {:name n
     :extension ext
     :date {:year year, :month month, :day day}
     :tags tags}))

(comment

  (def f (frame :title "Hello" :content "Hi there"))

  (-> f
      pack!
      show!)

  (def fp (flow-panel :align :left
                      :hgap 20
                      :items ["Label" "Another label"]))

  (-> (frame :title "Hello"
             :content (vertical-panel :items ["This" "is" "a" "vertical"
                                              "stack of" "JLabels"]))
      pack!
      show!)

  (alert "Test")

  (-> (frame
       :title "Test"
       :content
       (let [choose (fn [e] (alert "I should open a file chooser"))]
         (flow-panel
          :items ["File"                                 [:fill-h 5]
                  (text (System/getProperty "user.dir")) [:fill-h 5]
                  (action :handler choose :name "...")])))
      pack!
      show!)
  
  )
