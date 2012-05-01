(ns sandbox.ui
  (:use [seesaw core dev]))

(comment

  (-> (frame :title "Hello" :content "Hi there")
      pack!
      show!)

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
