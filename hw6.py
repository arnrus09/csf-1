# Name: Keir Allison-Bourne, Tony Lauricella
# Evergreen Login: allkei20, lauton04
# Computer Science Foundations
# Programming as a Way of Life
# Homework 6

# You may do your work by editing this file, or by typing code at the
# command line and copying it into the appropriate part of this file when
# you are done.  When you are done, running this file should compute and
# print the answers to all the problems. 


###
### Problem 3
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 3 solution follows:"

dict1 = {'Sunday' : 1, 'Monday' : 2, 'Tuesday' : 3, 'Wednesday' : 4, 'Thursday' : 5, 'Friday' : 6, 'Saturday' : 7}

dict2 = {1 : 'January', 2 : 'February', 3 : 'March', 4 : 'April', 5 : 'May', 6 : 'June', 7 : 'July', 8 : 'August', 9 : 'September', 10 : 'October', 11 : 'November', 12 : 'December'}

print dict1, dict2

###
### Problem 4
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 4 solution follows:"

list = [dict1, dict2]

print list
 
#both elements are dictionaries
#the elements of dict1 are ints and the elements of dict2 are strings ( the elements are the values not the keys )


###
### Problem 5
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 5 solution follows:"


listx = [0,0,0,0,0,0,0,0]
for i in dict1:
    y = dict1[i]
    listx[y] = dict2[y]
    
listx.remove(0)    
print listx

###
### Problem 6
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 6 solution follows:"

#  lines 31 through 32 only pass in a list with one element. lines 34 to 36 pass in a list with two elements. both return dictionaries as their output.

assert state_edges(rows3) == {'WA': 0.9, 'CA': -1.1, 'OR' : 0.2}


###
### Problem 7
###

# DO NOT CHANGE THE FOLLOWING LINE
print "Problem 7 solution follows:"

def month_getter(dict1, dict2):
    listx = [0,0,0,0,0,0,0,0]
    for i in dict1:
        y = dict1[i]
        listx[y] = dict2[y]
    listx.remove(0)    
    return listx

###
### Collaboration
###

# ... List your collaborators and other sources of help here (websites, books, etc.),
# ... as a comment (on a line starting with "#").
