(ns memeshot.core
  (:require [mikera.image.core :refer :all])
  (:gen-class))

(def ge
  "Graphics environment, lets us get screen info later"
  (java.awt.GraphicsEnvironment/getLocalGraphicsEnvironment))

(def screens
  "Grab every usable screen"
  (.getScreenDevices ge))

(defn get-bounds
  "Create an x-y map of screen's dimensions"
  [screen]
  (let [dimensions (fn [rect]
                     (hash-map :x (.getWidth rect) :y (.getHeight rect)))]
    (dimensions (.getBounds (.getDefaultConfiguration screen)))))

(def bounds
  "Map and sum get-bounds over each screen we find"
  (let [sum (apply merge-with + (map get-bounds screens))
        x (int (:x sum))
        y (-> (sort-by :y > (map get-bounds screens)) first :y)]
    (hash-map :x (int x)
              :y (int y))))

(def rectangle
  "Define a rectangle the size of our screens"
  (let [x (:x bounds)
        y (:y bounds)]
    (java.awt.Rectangle. 0 0 x y)))

(def robot
  "Get a robot instance"
  (java.awt.Robot.))

(def img
  "Create an image of all our screens"
  (.createScreenCapture robot rectangle))
