(ns tic-tac-toe.core 
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(def v1 (atom []))
(def v2 (atom []))
(def board (atom []))
(def winner (atom ""))

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

(defn askInput
  "Add user's input to user array and adds comp number.
   And then sort the atom"
  ([user]
   (if (>= (count @board) 8)
     (reset! winner "Its a tie")
     (let [num (Integer/parseInt (read-line))]
      ;;  VALIDATE THE NUMBER
       (if (in? @board num)
         (do (println "Place is already taken")
             (askInput v1))
         (do (swap! user conj num)
             (reset! user (vec (sort @user)))
             (swap! board conj num)
             (if (checkWinner? @v1)
               (reset! winner "v1 WINS")
               (askInput v2 (Integer/parseInt (read-line)))))))))
  ([user num]
   (if (in? @board num)
     (do (println "Place is already taken")
         (askInput v2 (Integer/parseInt (read-line))))
     (do (when (>= (count @board) 8)
           (reset! winner "Its a tie"))
         (swap! user conj num)
         (reset! user (vec (sort @user)))
         (swap! board conj num)
         (when (checkWinner? @v2)
           (reset! winner "v2 WINS"))))))


(defn main
  [msg]
  (println msg)
  (println "| 1 | 2 | 3 |\n| 4 | 5 | 6 |\n| 7 | 8 | 9 |")
  (println "--------------")
  (while (str/blank? @winner)
    (askInput v1)
    (println @board))
  (println @winner))

(main "user 1 => X and user 2 => O")