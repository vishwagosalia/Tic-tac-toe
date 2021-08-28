(ns tic-tac-toe.core
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(def v1 (atom []))
(def v2 (atom []))
(def board (atom []))
(def winner (atom ""))
(def displayBoard (atom "| 1 | 2 | 3 |\n| 4 | 5 | 6 |\n| 7 | 8 | 9 |"))
(def toggle (atom true))

(defn checkWinner?
  "check whether these numbers are added in v1 or v2 and then
   tell whose the winner is. if no winner is there then return nil and
   ask again for user input."
  [user]
  (let [s [[1 4 7] [2 5 8] [3 6 9] [1 2 3]
           [4 5 6] [7 8 9] [1 5 9] [3 5 7]]]
    (some (fn [i] (set/subset? (set i) (set user))) s)))


(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))


(defn displayMetricBoard
  [num]
  (if @toggle
    (swap! displayBoard str/replace (str num) "X")
    (swap! displayBoard str/replace (str num) "O")))


(defn askInput
  "Add user's input to user array and adds comp number.
   And then sort the atom"
  ([user num]
   (if (in? @board num)
     (do (println "Place is already taken")
         (askInput user (Integer/parseInt (read-line))))
     (do (when (= (count @board) 8)
           (reset! winner "Its a tie"))
         (swap! user conj num)
         (reset! user (vec (sort @user)))
         (swap! board conj num)
         (displayMetricBoard num)
         (when (> (count @board) 3)
           (if @toggle
             (when (checkWinner? @user)
               (reset! winner "v1 WINS"))
             (when (checkWinner? @user)
               (reset! winner "v2 WINS"))))
         (swap! toggle not)))))


(defn main
  [msg]
  (println msg)
  (println "| 1 | 2 | 3 |\n| 4 | 5 | 6 |\n| 7 | 8 | 9 |")
  (println "--------------")
  (while (str/blank? @winner)
    (println (count @board))
    (if @toggle
      (askInput v1 (Integer/parseInt (read-line)))
      (askInput v2 (Integer/parseInt (read-line))))
    (println @displayBoard)
    (println "--------------"))
  (println @winner))

(main "user 1 => X and user 2 => O")
