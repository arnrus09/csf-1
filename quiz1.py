# Graded by: Zach
# Login: linzac03

import lab2

n = lab2_n
x = 1

while (x < n+1):
  print "Hello World"
  x += 1

#lab2_n is not defined, lab2.n calls element n from lab2


# corrected code below:

import lab2

n = lab2.n
x = 1

while (x < n+1):
  print "Hello World"
  x += 1
