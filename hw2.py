# Name: Tony Lauricella
# Evergreen Login: lauton04
# Computer Science Foundations
# Programming as a Way of Life
# Homework 2

# You may do your work by editing this file, or by typing code at the
# command line and copying it into the appropriate part of this file when
# you are done.  When you are done, running this file should compute and
# print the answers to all the problems.

# comment
###
### Problem 1
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 1 solution follows:"

import hw2_test

total = 0 
currentTerm = 0

# Sums the numbers from 0 to n and shows the total for each step.  
while (currentTerm < hw2_test.n + 1):
    total += currentTerm
    print "Term " + str(currentTerm) + ":  " + str(total)
    currentTerm += 1

###
### Problem 2
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 2 solution follows:"

n = 10

# Divides 1 by the numbers 1 through n and shows each answer as a decimal. 
for i in range(1, n+1):
    print "1/" + str(i) + " = " + str(1 / float(i))

###
### Problem 3
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 3 solution follows:"

n = 10
total = 0

# Finds the triangular number n (the sum of all natural numbers up to n).  
for i in range(0, n+1):
    total += i
print "Triangular number " + str(n) + " via loop: " + str(total)
print "Triangular number " + str(n) + " via formula: " + str(n * (n + 1) / 2)

###
### Problem 4
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 4 solution follows:"

n = 10
total = 1

# Finds the factorial of n.
for i in range(1, n+1):
    total *= i
print str(n) + "! = " + str(total)

###
### Problem 5
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 5 solution follows:"

numlines = 10

# Calculates the factorials of the numbers from the value of numlines to 1, 
# and lists them in descending order.
for i in range(numlines, 0, -1):
    n = i
    total = 1
    for j in range(1, n+1):
        total *= j
    print str(n) + "! = " + str(total)

###
### Problem 6
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 6 solution follows:"

total = 0
denominator = 10

# Calculates the sum of 1 + 1/1! + 1/2! + 1/3! + ... + 1/denominator!
for i in range(1, denominator+1):
    n = i
    factorial = 1
    for j in range(1, n+1):
        factorial *= j
    total += (1 / float(factorial))
print str(1 + total)


###
### Collaboration
###

# I used the Introduction to Computation and Programming Using Python book.

###
### Reflection
###

# This assignment took me about 3 to 4 hours.  The readings and lectures
# contained everything I needed to complete it.  
