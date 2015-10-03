;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname HW1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
;; Assignment 1 
;;Zhang, Zeqing 
;;NEU User Name: zhang.ze 

;;Problem 1:

;;[Listof Number] is one of:
;;-empty
;;-(cons Number [Listof Number])
(define lon0 empty)
(define lon1 (list 1 2 3 4))
(define lon2 (list 2 1 2 2))
;;[Listof Number] -> Number
;;Given a [Listof Number], sum them together.
(define (sum lon)
  (cond [(empty? lon) 0]
        [else (+ (first lon)
                 (sum (rest lon)))]))
(check-expect (sum lon0) 0)
(check-expect (sum lon1) 10)
(check-expect (sum lon2) 7)

;;[Listof Number]-> Number
;;Given a list of number square each number in the list and add them together.
(define (sum-sqrs lon)
  (cond [(empty? lon) 0]
        [else (+ (sqr (first lon))
                 (sum-sqrs (rest lon)))]))
(check-expect (sum-sqrs lon0) 0)
(check-expect (sum-sqrs lon1) 30)
(check-expect (sum-sqrs lon2) 13)

;;[Listof Number] [Listof Number] -> [Listof Number]
;;Given two lists of equal length produce single list of interleaved elements.
(define (interleave lon1 lon2)
  (cond [(empty? lon1) empty]
        [else (cons (first lon1)
                    (cons (first lon2)
                          (interleave (rest lon1) (rest lon2))))]))
(check-expect (interleave (list 1 2 3) (list 4 5 6)) (list 1 4 2 5 3 6))
(check-expect (interleave lon1 lon2) (list 1 2 2 1 3 2 4 2))
(check-expect (interleave lon1 lon1) (list 1 1 2 2 3 3 4 4))
(check-expect (interleave lon0 lon0) empty)

;;Problem 2:
;; dist: Posn Posn -> Number
;; compute the distance between the two given points 
(define (dist p1 p2)
  (sqrt (+ (sqr (- (posn-x p1) (posn-x p2)))
             (sqr (- (posn-y p1) (posn-y p2))))))

;; examples
(check-expect (dist (make-posn 2 3) (make-posn 5 3)) 3)
(check-expect (dist (make-posn 1 1) (make-posn 4 5)) 5)

;;a.
;;[Listof posn] -> Number
;;computes the total distance by accumulating the total
;;distance traveled so far as it traverses over the list of Posns.
(define (total-distance lop)
  (local [;;[Listof posn] Posn Number -> Number
          ;;Use accumulator to calculate the travel distance so far.
          (define (helper lop pos acc)
            (cond [(empty? lop) acc]
                  [(cons? lop) (helper (rest lop) 
                                       (first lop)
                                       (+ acc (dist pos (first lop))))]))]
    (cond [(empty? lop) 0]
          [else
           (helper (rest lop) (first lop) 0)])))
(check-expect (total-distance empty) 0)
(check-expect (total-distance (list (make-posn 0 0) (make-posn 3 4) (make-posn 0 0)))
              10)
(check-expect (total-distance (list (make-posn 13 23) (make-posn 16 27))) 5)

;;b.
;;[Listof Posn] -> Boolean
;;Check if the path traverses through some point more than once.
(define (has-loop lop)
  (cond [(or (empty? lop)
            (empty? (rest lop))) false]
        [else (or (member? (first lop) (rest lop))
                   (has-loop (rest lop)))]))
(check-expect (has-loop empty) false)
(check-expect (has-loop (list (make-posn 0 0) (make-posn 3 4))) false)
(check-expect (has-loop (list (make-posn 5 6) (make-posn 7 8) (make-posn 7 8)
                    (make-posn 13 56))) true)

;;c.
;;[Listof posn] -> [Listof posn]
;;Given a list of posn, produces the list of all 
;;Posns along this route, but lists every point only once
(define (all-visited lop)
  (local [;;[Listof posn] -> {listof posn]
          ;;Given a list of posn,get the overlapping posn off, listing
          ;;lists every point only once 
          (define (newlist lop)
            (cond [(empty? lop) empty]
                  [(cons? lop) (if (member? (first lop) (rest lop))
                                   (newlist (rest lop))
                                   (cons (first lop) (newlist (rest lop))))]))]
    (if (has-loop lop) 
        (newlist lop)
        lop)))
(check-expect (all-visited empty) empty)
(check-expect (all-visited (list (make-posn 5 6) (make-posn 7 8) (make-posn 5 6)))
              (list (make-posn 7 8) (make-posn 5 6)))
(check-expect (all-visited (list (make-posn 1 2) (make-posn 5 6)))
              (list (make-posn 1 2) (make-posn 5 6)))

;;Problem 3:
;;A vehicle is one of:
;;- Car
;;- Truck
;;- Bus

(define-struct ca (size consumption trailer?))
;;A Car is (make-ca Number Number Boolean)
;;(make-ca size consumption trailer?) which size is the 
;;the size of the fuel tank of this car in gallons, the consumption
;;is the estimated fuel consumption given in miles per gallon, trailer? is whether
;;the car has a trailer.

(define-struct bus (size consumption capacity))
;;A bus is (make-bus Number Number Number)
;;(make-bus size consumption capacity) whcih size is the 
;;the size of the fuel tank of this car in gallons, the consumption
;;is the estimated fuel consumption given in miles per gallon, capacity is the number of
;;passengers a bus can take.


(define-struct truck (size consumption load))
;;A truck is (make-truck Number Number Number)
;;(make-truck size consumption load)  whcih size is the 
;;the size of the fuel tank of this car in gallons, the consumption
;;is the estimated fuel consumption given in miles per gallon, load is 
;; some specified maximum load.

;;a. 
;;Vehicle -> Number
;;Given a Vehicle, calsulate the maximum distance it can go when the 
;;fuel box is full.

(define car1 (make-ca 40 2 true))
(define car2 (make-ca 50 1.5 false))
(define bus1 (make-bus 80 1 25))
(define bus2 (make-bus 100 1 50))
(define truck1 (make-truck 120 0.5 5000))
(define truck2 (make-truck 200 0.5 10000))

(define (distance vehicle)
  (cond [(ca? vehicle) (* (ca-size vehicle) (ca-consumption vehicle))]
        [(bus? vehicle) (* (bus-size vehicle) (bus-consumption vehicle))]
        [(truck? vehicle) (* (truck-size vehicle) (truck-consumption vehicle))]))
(check-expect (distance car1) 80)
(check-expect (distance car2) 75)
(check-expect (distance bus1) 80)
(check-expect (distance bus2) 100)
(check-expect (distance truck1) 60)
(check-expect (distance truck2) 100)

;;b.
;;Vehicle Number-> Boolean
;;Given a vehicle and a number, determine whether the 
;;vehicle can travel the given distance.
(define (check? vehicle n)
  (cond [(ca? vehicle) (>= (* (ca-size vehicle) (ca-consumption vehicle)) n)]
        [(bus? vehicle) (>= (* (bus-size vehicle) (bus-consumption vehicle)) n)]
        [(truck? vehicle) (>= (* (truck-size vehicle) (truck-consumption vehicle)) n)]))
(check-expect (check? car1 100) false)
(check-expect (check? bus1 40) true)
(check-expect (check? truck1 30) true)

;;c.
;;Vehicle [Listof Vehicle] -> Boolean
;;whether the given Vehicle can travel farther than some other vehicle.
(define (farther? v lov)
  (local (;;Vehicle -> boolean
          ;;Compare the given vehicle to the vehicle in the list.
          (define (helper vehicle)
            (> (distance v) (distance vehicle ))))
    (andmap helper lov) ))
(check-expect (farther? bus2 (list car1 truck1)) true)
(check-expect (farther? car1 (list car2 bus1)) false)
            
    


                                              

  

  
  
  
  
  
  
  