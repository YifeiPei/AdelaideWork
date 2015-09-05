#lang racket

;;; a mutable tree map
;;; same as a functional tree map but 
;;; contains functionality to update the left
;;; and right branches
(define (mut-tree-map k v l r)
  (define (set-left new-l)
    (set! l new-l))
  (define (set-right new-r)
    (set! r new-r))
  (define (dispatch m)
    (cond ((eq? m 'key) k)
          ((eq? m 'val) v)
          ((eq? m 'left) l)
          ((eq? m 'right) r)
          ((eq? m 'set-left) set-left)
          ((eq? m 'set-right) set-right)
          (else (error "mut-tree-map: unknown message"))))
  dispatch)




;;; Accessors
;;; same as with functional tree map
(define (key t) (t 'key))
(define (val t) (t 'val))
(define (left t) (t 'left))
(define (right t) (t 'right))


;;; Mutators for tree maps
(define (set-left! l t)
  ((t 'set-left) l)) ;;; set the left sub-tree
(define (set-right! r t)
  ((t 'set-right) r)) ;;; set the right sub-tree

;;; get procedure - same as for functional tree-map
;;; returns false if value not found
;;; otherwise returns value associated with key
(define (get k t)
  (cond ((null? t) #f)
        ((= k (key t)) (val t))
        ((> k (key t)) (get k (right t)))
        (else (get k (left t)))))


;;; put on mutable tree-maps 
;;; - only works on non-empty trees
(define (mut-put k v t)
   (cond  ((null? t) (error "can't mutate empty tree"))
        ((= k (key t)) );; key found do nothing
        ((> k (key t)) 
         (if (null? (right t))
             (set-right! (mut-tree-map k v '() '()) t)
             (mut-put k v (right t)))) ;; put in rhs
        (else 
         (if (null? (left t))
             (set-left! (mut-tree-map k v '() '()) t)
             (mut-put k v (left t)))))) ;; put in lhs


;;; 
;;; WRITE SOME UNIT TESTS FOR MUT TREE MAPS HERE


;;; now for a memoised-fib with mutational tree
;;; initialise the fib-map
(define fib-map (mut-tree-map -1 0 '() '()))

;;; Memoising Fibonnaci - you must write this procedure
;;; to easily run the test cases below. This procedure
;;; must save the results of its calls to fib-map above
;;; and retrieve these results when they are available.
;;; NOTE: you should have immediate access to any updates
;;; to fib-map - you don't have to pass it around through
;;; the code
;;;(define (mut-fib x)
;;;UNCOMMENT THE LINE ABOVE AND PUT YOUR CODE HERE

;;; These should run fast and easily
;;; and they should be even faster next time around
;;(mut-fib 4)
;;(mut-fib 20)
;;(mut-fib 200)
;;(mut-fib 300)
;;(mut-fib 1000)
;;(mut-fib 2000)
;;(mut-fib 3000)
;;(mut-fib 3000) ;;; this one should be fast!!!

