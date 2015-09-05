;(#%require srfi/41)

#lang racket
(require racket/stream)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Advanced Programming Paradigms - Semester 2 2013
;      Assignment2
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Name: Yifei Pei
; Student ID number: a1611648
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;; Question 1 b)

;;; The foldr procedure from lecture
(define (foldr op z ls)
  (if (null? ls)
      z
      (op (car ls) (foldr op z (cdr ls)))))

;;; filtercons prepare for filter. cons the elements that fit predicate

(define (filtercons predicate)
  (lambda (x y)
    (if (predicate x)
        (cons x y)
        y)))

;;; Question 1 c)

(define (filter predicate xs)
  (foldr (filtercons predicate) '() xs))

;;; testing for filter
(filter even? (list 1 2 3 4 5 6 7 8 9 10))

(filter odd? (list 1 2 3 4 5 6 7 8 9 10))

;;; Question 2 a)

;;; some definitions from lectures
(define head stream-first)
(define tail stream-rest)
(define the-empty-stream '() )
(define (stream-null? stream)
  (if (null? stream)
      #t
      #f))

;reorder to do the required question 2 a
(define (reorder order-stream data-stream)
  (cond ((stream-null? order-stream) the-empty-stream)
        ((stream-null? data-stream) the-empty-stream)
        (else (stream-cons (stream-ref data-stream (- (head order-stream) 1)) (reorder (tail order-stream) data-stream)))))

(define o (stream 3 1 4 2))     ;the order-stream
(define d (stream 4 13 2 8))    ;the data-stream

;;; Question 2 b)

;;; definitions from lectures
(define (plusone x) (+ 1 x))
(define integers (stream-cons 1 (stream-map plusone integers)))

;(print-first-n (reorder integers d) 4)

;(print-first-n (reorder d integers) 4)

;;; Question 2 c)

;;; definition to solve the question 2 c
(define (print-first-n s n)
  (cond ((= n 0) (newline))
        ((= n 1) (begin
                   (display (head s))
                   (print-first-n (tail s) (- n 1))))
        (else (begin
                (display (head s) )
                (display ", ")
                (print-first-n (tail s) (- n 1))))
      )
  )

;;; implementation for question 2 b
(print-first-n (reorder integers d) 4)
(print-first-n (reorder d integers) 4)

;;; testing for print-first-n
(print-first-n integers 4)

;;; Question 2 d)

;;; definitions from lectures
(define (addL x y)
   (stream-cons (+ (head x) (head y))
                (addL (tail x) (tail y))))
(define fibs
   (stream-cons 1 
          (stream-cons 1
                 (addL (tail fibs) fibs))))

;;;implementation of question 2 d
(print-first-n (reorder (tail fibs) (tail fibs)) 7)

;;; testing for question 2 d
(print-first-n (tail fibs) 7)
(print-first-n (tail fibs) 21)

;;; Question 3)

;;; question 3 a and question 3 b put together in make-account
(define (make-account balance)
  
  (define (counter initial)
    (let ((number initial))
      (lambda (transaction)
        (set! number (+ number transaction))
        number)))
  
  (define count (counter 0) )
  
  (define (withdraw amount)
    (if (>= balance amount)
        (begin (set! balance (- balance amount))
               (count 1)
               balance)
        "Insufficient funds"))
  
  (define (deposit amount)
    (set! balance (+ balance amount))
    (count 1)
    balance
    )
  
  (define (dispatch m)
    (cond ((eq? m 'withdraw) withdraw)
          ((eq? m 'deposit) deposit)
          ((eq? m 'balance) balance)
          ((eq? m 'transaction) (count 0))
          (else (error "Unknown request -- MAKE-ACCOUNT"
                       m))))
  dispatch)

;;;testing for question 3

;;;make two accounts, acc1 with 200 balance, acc2 with 300 balance
(define acc1 (make-account 200))
(define acc2 (make-account 300))

;;; testing of counter
(acc1 'transaction)
((acc1 'withdraw) 50)
(acc1 'transaction)
((acc1 'deposit) 300)
(acc1 'transaction)
((acc1 'deposit) 10)
(acc1 'transaction)
((acc1 'withdraw) 200)
(acc1 'transaction)

;;; testing of balance
(acc2 'balance)
((acc2 'withdraw) 50)
(acc2 'balance)
((acc2 'deposit) 300)
(acc2 'balance)
((acc2 'deposit) 10)
(acc2 'balance)
((acc2 'withdraw) 200)
(acc2 'balance)