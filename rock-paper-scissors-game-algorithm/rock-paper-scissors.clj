; TODO
    ; OK get guess
    ; OK define winner
    ; OK play game


;get user's guess
(defn get-user-guess []
    (println "Play your hand: (r)ock, (p)aper or (s)cissors")
    (let [guess (read-line)]
         (if (get {"r" true, "p" true, "s" true} guess) guess)))

;define winner
(defn define-winner [guess1 guess2]
    (let [guesses [guess1 guess2]]
         (cond
            (= guess1 guess2) 0
            (= guesses ["r" "s"]) 1
            (= guesses ["p" "r"]) 1
            (= guesses ["s" "p"]) 1
            (= guesses ["r" "p"]) 2
            (= guesses ["p" "s"]) 2
            (= guesses ["s" "r"]) 2)))
        
;generate computer's guess
(defn get-computer-guess []
    (["r" "p" "s"] (rand-int 2)))

;play hand
(defn play-hand[]
    (let [computer-guess (get-computer-guess)
            user-guess (get-user-guess)
            winner (define-winner computer-guess user-guess)]
        (println "The computer guessed: " computer-guess)
        (println "You guessed: " user-guess)
        (cond
            (= user-guess nil) (println "Your entry was invalid!")
            (= winner 0) (println "Game tied!")
            (= winner 1) (println "Computer wins!")
            (= winner 2) (println "You won!"))))

;play game indefinitely
; (loop []
;       (play-hand)
;       (recur))

;play game once
(play-hand)
