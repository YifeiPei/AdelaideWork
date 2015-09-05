;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Advanced Programming Paradigms - Semester 2 2013
;      Assignment1
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Name: Yifei Pei
; Student ID number: a1611648
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Question 1 b)

(define (foldl op z ls)
  (if (null? ls)
      z
      (foldl op (op z (car ls)) (cdr ls))))

(define (foldr op z ls)
  (if (null? ls)
      z
      (op (car ls) (foldr op z (cdr ls)))))

(define (snoc x y) (cons y x))

(define (rev ls) (foldl snoc '() ls))

(define (g x y)
    (append y x))

(define (rev1 ls) (foldr g '() (map list ls)))

(define (rev2 ls) (foldl g '() (map list ls)))

(define (enum-range start finish)
  (if (> start finish)
      '()
      (cons start (enum-range (+ 1 start) finish))))

;;; Question 2)

(define (interleave xs ys)
  (cond ((null? xs)
         ys)
        ((null? ys)
         xs)
        (else
         (cons (car xs) (cons (car ys) (interleave (cdr xs) (cdr ys)))))))

;;; Question 3 a)

(define (repeat val n)
  (if (zero? n)
      '()
      (cons val (repeat val (- n 1)))))

;;; Question 3 b)

(define (scan op z ls)
  (if (null? ls)
      (list z)
      (cons z (scan op (op z (car ls)) (cdr ls)))))

(define (prefix-sum n)
  (cdr (scan + 0 (cdr (scan + 0 (repeat 1 n))))))

;;; Question 4)

(define (mirror ls)
  (if (null? (cdr ls))
      ls
      (cons
       (mirror (cdr ls))
       (car ls))))

(define a
  (cons 5 (cons (cons 6 7) 8)))

(define b
  (cons (cons 8 (cons 7 6)) 5))

;;; driver for all the answers

;;; Question 1 b) test

(begin (display "Question 1 b)")
       (newline)
       (display "first: test for rev1")
       (newline)
       (display "input: (list 1 2 3 4 5)")
       (newline)
       (display "expected output: (5 4 3 2 1)")
       (newline))
(define t11 (rev1 (list 1 2 3 4 5)))
t11
(begin (display "second: test for rev2")
       (newline)
       (display "input: (list 6 7 8 9 10)")
       (newline)
       (display "expected output: (10 9 8 7 6)")
       (newline))
(define t12 (rev2 (list 6 7 8 9 10)))
t12

;;; Question 2) test

(begin (display "Question 2)")
       (newline)
       (display "input: (list 1 2 3) and (list 4 5 6)")
       (newline)
       (display "expected output (1 4 2 5 3 6)")
       (newline))
(define t2 (interleave (list 1 2 3) (list 4 5 6)))
t2

;;; Question 3 a) test

(begin (display "Question 3 a)")
       (newline)
       (display "input: (repeat 3 5)")
       (newline)
       (display "expected output (3 3 3 3 3)")
       (newline))
(define t31 (repeat 3 5))
t31

;;; Question 3 b) test

(begin (display "Question 3 b)")
       (newline)
       (display "Case 1: n=0")
       (newline))
(define t321 (prefix-sum 0))
t321
(begin (display "Case 2: n=1")
       (newline))
(define t322 (prefix-sum 1))
t322
(begin (display "Case 3: n=5")
       (newline))
(define t323 (prefix-sum 5))
t323
(begin (display "Case 4: n=10")
       (newline))
(define t324 (prefix-sum 10))
t324

;;; Question 4) test
(begin (display "Question 4)")
       (newline)
       )