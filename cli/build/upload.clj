
(ns build.upload
  (:require [clojure.java.shell :refer [sh]]))

(def configs {:orgization "mvc-works"
              :name "calcit-threejs-workflow"
              :cdn "calcit-threejs-workflow"})

(defn sh! [command]
  (println command)
  (println (sh "bash" "-c" command)))

(defn -main []
  (sh! "cp -r entry dist/")
  (sh! (str "rsync -avr --progress dist/* tiye.me:cdn/" (:cdn configs)))
  (sh!
    (str "rsync -avr --progress dist/{index.html,manifest.json,entry} tiye.me:repo/"
      (:orgization configs) "/"
      (:name configs) "/"))
  (shutdown-agents))
