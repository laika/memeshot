(ns memeshot.core
  (:gen-class))

;; This is where we get the screen size for our shot
(def ge (java.awt.GraphicsEnvironment/getLocalGraphicsEnvironment))
(def screens (.getScreenDevices ge))
(defn get-bounds [screen]
  (let [dimensions (fn [rect]
                     (hash-map :x (.getWidth rect) :y (.getHeight rect)))]
    (dimensions (.getBounds (.getDefaultConfiguration screen)))))
(def bounds
  (let [sum (apply merge-with + (map get-bounds screens))]
    (hash-map :y (int (/ (:y sum) (count screens))) :x (int (:x sum)))))
(def rectangle
  (let [x (:x bounds)
        y (:y bounds)]
    (java.awt.Rectangle. x y)))
;;Get our robot out to do slave labor
(def robot (java.awt.Robot.))
;;Img
(def img (.createScreenCapture robot rectangle))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  )
