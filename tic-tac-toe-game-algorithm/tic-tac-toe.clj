;TODO
    ;[] display board
        ;[] generate slots
        ;[x] generate triples (rows, columns, diagonals)
    ;[] play turns
    ;[x] define winner


;define which triple contains x or o in all slots
(defn triple-winner? [triple]
    (if (every? #{:x} triple) :x
        (if (every? #{:o} triple) :o)))

(declare generate-triples)

;returns :x if x's win, :o if o's win, otherwise nill if no winner
(defn check-winner? [board]
    (first (filter #{:x :o} (map triple-winner? (generate-triples board)))))

;generate 'triples' (rows, columns, diagonals) out of a board
defn( generate-triples [board]
      (concat
          (partition-all 3 board)                           ;get rows
          (list
              (take-nth 3 board)                            ;first column
              (take-nth 3 (drop 1 board))                   ;second column
              (take-nth 3 (drop 2 board))                   ;third column
              (take-nth 4 board)                            ;top-left/bottom-right diagonal
              (take-nth 2 (drop-last 2 (drop 2 board))))))  ;top-right/bottom-left diagonal


;IN PROGRESS...
