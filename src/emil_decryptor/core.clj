(ns emil-decryptor.core)

(defn cleartext-char-p [ch]
  (or (Character/isLowerCase ch)
      (= ch \space)))

(defn parse-int [s]
   (Integer/parseInt (str s) 36))

(defn decode-last-character [str]
  (let [last-char (last str)]
    (if (cleartext-char-p last-char) 
      last-char
      (decode-last-character (drop-last (+ 1 (parse-int last-char)) str)))))

(defn decode-string [encoded-string]
  (loop [encoded encoded-string decoded ""]
    (if (= 0 (count encoded)) 
      (apply str decoded)
      (recur (drop-last encoded)
             (cons (decode-last-character encoded) decoded)))))

    
