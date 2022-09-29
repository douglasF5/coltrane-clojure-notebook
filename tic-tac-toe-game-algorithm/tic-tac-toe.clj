;TODO
    ;[x] display/update board
        ;[x] generate slots/starting the board
        ;[x] print board
        ;[x] generate triples (rows, columns, diagonals)
    ;[x] check if the board is full
    ;[x] parse keywords into strings
    ;[x] play turns
        ;[x] get user move
    ;[x] define winner
    ;[x] play game
    

;global data
(def board-slots [1 2 3 4 5 6 7 8])
(def player-sequence (cycle [:x :o]))


;generate 'triples' (rows, columns, diagonals) out of a board
defn(generate-triples [board]
      (concat
          (partition-all 3 board)                           ;get rows
          (list
              (take-nth 3 board)                            ;first column
              (take-nth 3 (drop 1 board))                   ;second column
              (take-nth 3 (drop 2 board))                   ;third column
              (take-nth 4 board)                            ;top-left/bottom-right diagonal
              (take-nth 2 (drop-last 2 (drop 2 board))))))  ;top-right/bottom-left diagonal

;define which triple contains x or o in all slots
(defn triple-winner? [triple]
    (if (every? #{:x} triple) :x
        (if (every? #{:o} triple) :o)))

;returns :x if x's win, :o if o's win, otherwise nill if no winner
(defn check-winner? [board]
    (first (filter #{:x :o} (map triple-winner? (generate-triples board)))))

;print board
(defn print-board [board]
    (let [board (map #(if (keyword? %) (subs (str %) 1) %) board)]
         (println (nth board 0) (nth board 1) (nth board 2))
         (println (nth board 3) (nth board 4) (nth board 5))
         (println (nth board 6) (nth board 7) (nth board 8))))

;check if the board is full
(defn is-board-full? [board]
    (every? #{:x :o} board))

;parse :x and :o keywords into their string representation "x" and "o"
(defn get-player-name [player-keyword]
    (subs (str player-keyword) 1)

;validate and return user move
(defn get-user-move [board]
    (let [input (try
            (. Integer parseInt (read-line))
            (catch Exeption e nil))]
         (if (some #{input} board)
             board
             nil)))
 
;take turns
(defn take-turn [player board]
    (println "Select your move, player " (get-player-name player) " (press 1 - 9 and hit enter):")
    (loop [move (get-user-move board)]
          (if move
              (assoc board (dec move) player)
              (do
                  (println "Move was invalid. Select your move, player " (get-player-name player) ":")
                  (recur (get-user-move board))))))
 
;play game 
(defn play-game []
     (loop [board board-slots player-sequence player-sequence]
           (let [winner (check-winner? board)]
                (println "Current board:")
                (print-board board)
                (cond
                    winner (println "Player " (get-player-name winner) "wins!")
                    (is-board-full? board) (println "Game is a draw.")
                    :else
                        (recur
                            (take-turn (first player-sequence) board)
                            (rest player-sequence))))))
