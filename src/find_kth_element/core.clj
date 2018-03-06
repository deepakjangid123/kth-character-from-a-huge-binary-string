(ns find-kth-element.core
  (:require [clojure.java.io :as io]
            [clojure.string :as s])
  (:gen-class))

(defn- log-with-base-2
  "Returns log result with base 2"
  [number]
  (/ (Math/log number) (Math/log 2)))

(defn search-index
  "Searches for the index, It is like binary search, we will have to update mid
  every time and if index is in left then update mid and value with 0, else if it
  is right to mid then update mid and value will be 1, else if index is at the mid
  then return the value"
  [low high index value]
  (let [mid (long (/ (+ low high) 2))]
    (cond
      (= index mid) value
      (< index mid) (recur low (dec mid) index 0)
      (> index mid) (recur (inc mid) high index 1))))

(defn kth-character
  "Finds kth character from a huge binary string,
  Where S0 = ''
        S1 = '0'
        S2 = '001'
        S3 = '0010011'
        S4 = '001001100011011'
        ...
        SN = SN-1 + '0' + switch(reverse(SN-1))"
  [k]
  (cond
    (= k 1) 0
    :else (let [log-value (Math/floor (log-with-base-2 k))
                low (long (Math/pow 2 log-value))
                high (long (Math/pow 2 (inc log-value)))
                index (long (- k low))
                [low high] [1 (- (dec high) low)]
                char-at-index (if (zero? index) 0
                                (search-index low high index 1))]
            char-at-index)))

(defn read-file
  "Reads file and returns data"
  [filepath]
  (map read-string (s/split-lines (slurp filepath))))

(defn perform-operations
  "Perform operations on data"
  [data]
  (let [entries (first data)
        queries (take entries (rest data))
        result (map kth-character queries)]
    result))

(defn write-result-to-file
  [filename result]
  (if (.exists (io/file filename))
    (io/delete-file filename))
  (map #(spit filename (str "Case #" %1 ": " %2 "\n") :append true)
       (iterate inc 1) result))

(defn run-on-small-values
  []
  (write-result-to-file "small-output.txt"
                        (perform-operations
                          (read-file "resources/B-small-practice.in"))))

(defn run-on-large-values
  []
  (write-result-to-file "large-output.txt"
                        (perform-operations
                          (read-file "resources/B-large-practice.in"))))

(run-on-small-values)
(run-on-large-values)

