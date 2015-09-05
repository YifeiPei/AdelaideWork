#lang racket


;;;; functional tree-map node constructor
;;;; takes a key: k; a value: v; a left tree: l and
;;;; a right tree: r and builds a tree-map node.
;;;; If the tree map is built as a binary search tree
;;;; it will have average O(log n) access time

(define (tree-map k v l r)
  ;;; behaviour defined by dispatch
  ;;; dispatch takes a message and responds
  ;;; with the desired value
  (define (dispatch m)
    (cond ((eq? m 'key) k)    ;;; get key
          ((eq? m 'val) v)    ;;; get val
          ((eq? m 'left) l)   ;;; get left
          ((eq? m 'right) r)  ;;; get right
          (else (error "tree-map: incorrect message"))))
  dispatch);;; return the procedure dispatch

;;; accessor procedures 
;;; all take a tree-map node: t
(define (key t) (t 'key))   ;;; get the key
(define (val t) (t 'val))   ;;; get the value
(define (left t) (t 'left))  ;;; get the left sub-tree
(define (right t) (t 'right)) ;;; get the right sub-tree


;;; Some small unit tests
;;; uncomment to run
;;;(define a (tree-map 3 4 '() '())) ;; procedural value
;;;(define b (tree-map 5 6 a '()))   ;; procedural value
;;;(val a)      ;; returns 4
;;;(right b)    ;; returns '()
;;;(key a)      ;; returns 3
;;;(val (left b))  ;; returns 6
;;;(key (left b))  ;; returns 5


;;; put key/value pair into tree-map
;;; maintains BST ordering
;;; assumes keys are numeric
;;; values can be anything
(define (put k v t)
  (cond ((null? t) (tree-map k v '() '())) ;;; empty tree
                                           ;;; insert node
        ((= k (key t)) t)           ;;; equal key
                                    ;;; ignore - could 
                                    ;;; insert new  
                                    ;;; val but consider
                                    ;;; immutable for now
        ((> k (key t))              ;;; k greater than key
                                    ;;; go down right tree
         (tree-map (key t)
                   (val t)
                   (left t)
                   (put k v (right t))))
        (else                       ;;; otherwise go down
         (tree-map (key t)          ;;; left tree
                   (val t)
                   (put k v (left t))
                   (right t)))))


;;; some more unit test cases
;(define t1 (put 5 10 '()))
;(define t2 (put 3 20 t1))
;(define t3 (put 7 30 t2))


;;; get procedure on tree-map
;;; returns false if value not found
;;; otherwise returns value associated with key
(define (get k t)
  (cond ((null? t) #f)
        ((= k (key t)) (val t))
        ((> k (key t)) (get k (right t)))
        (else (get k (left t)))))

;;; more unit test cases
;;; test get
;(get 5 t3) ;; returns 10
;;;(get 7 t3) ;; returns 30
;;;(get 3 t3) ;; returns 20
;;;(get 2 t3) ;; returns #f

;;; plain fib: returns fibonnaci of x
(define (fib x)
  (if (< x 2)
      x
      (+ (fib (- x 1)) (fib (- x 2)))))

(display "running plain fib")
(fib 1)
(fib 2)
(fib 3)
(fib 4)
(fib 5)
(fib 10)
(fib 20)
(fib 35) ;;; uncomment to run for longer time


;;; memo fib - uses a tree-map to remember and retrieve
;;; values of past calls to speed up execution dramatically
;;; makes a call to a helper method with the input value
;;; x and an empty tree-map
(define (fib-m x)
  (car (fib-mem x '())))

(define (fib-mem x t)
  (cond ((get x t) (cons (get x t) x))
        ((< x 2) (fib-mem x (put x x t)))
        (else (fib-mem x (put x (+ (car (fib-mem (- x 2) t)) (car (fib-mem (- x 1) t))) t)))))

;;; fib-mem - THIS IS THE PART THAT YOU HAVE TO DO
;;; write fib-mem so that it can easily run the test
;;; cases below quickly. fib-mem must save results of 
;;; all previous calls in t. These saved results can be
;;; used to avoid recursive calls if the result has already
;;; been saved. You will have pass the latest version of 
;;; the tree-map carefully through the code to ensure
;;; that you have access to the latest saved value.
;;; NOTE: fib-mem returns a pair consisting of its result
;;; and the latest version of the tree-map
;;;(define (fib-mem x t)
;;; UNCOMMENT THE LINE ABOVE AND PUT YOUR CODE HERE

;;; unit tests... should all run fast
;;; uncomment once you have written fib-mem above
;;; should return the same results as for fib-mem above
(fib-m 1)
(fib-m 2)
(fib-m 3)
(fib-m 4)
(fib-m 5)
(fib-m 10)
(fib-m 20)
(fib-m 35)
;;(fib-m 50)
;;(fib-m 100)