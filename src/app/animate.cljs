
(ns app.animate (:require [app.global :as global]))

(defn animation-loop! [timestamp]
  (let [tree (:tree @global/*objects)]
    (when (and (some? tree))
      (swap! global/*values assoc :time timestamp)
      (set! (.. tree -rotation -y) (/ (:time @global/*values) 4000))
      (.render global/renderer global/scene global/camera)))
  (.requestAnimationFrame js/window animation-loop!))
