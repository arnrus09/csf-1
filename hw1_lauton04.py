# Name: Tony Lauricella
# Evergreen Login: lauton04
# Computer Science Foundations
# Programming as a Way of Life
# Homework 1

# You may do your work by editing this file, or by typing code at the
# command line and copying it into the appropriate part of this file when
# you are done.  When you are done, running this file should compute and
# print the answers to all the problems.

import math                     # makes the math.sqrt function available


###
### Problem 1
###

print "Problem 1 solution follows:"

# assigns the coefficients for the quadratic equation 
A = 1
B = -5.86
C = 8.5408

# computes both values of x
x1 = (-B + math.sqrt(B**2 - 4*A*C)) / (2*A)
x2 = (-B - math.sqrt(B**2 - 4*A*C)) / (2*A)

# converts the values of x to strings and outputs them
print str(x1)
print str(x2)


###
### Problem 2
###

print "Problem 2 solution follows:"

import hw1_test

# converts the values of variables a,b,c,d,e and f to strings and outputs them
print str(hw1_test.a)
print str(hw1_test.b)
print str(hw1_test.c)
print str(hw1_test.d)
print str(hw1_test.e)
print str(hw1_test.f)


###
### Problem 3
###

print "Problem 3 solution follows:"

# evaluates a Boolean expression based on the values in hw1_test
print str((hw1_test.a and hw1_test.b) or (not hw1_test.c) and not (hw1_test.d or hw1_test.e or hw1_test.f))

###
### Collaboration
###

# ... List your collaborators here, as a comment (on a line starting with "#").
