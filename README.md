# Library 

CS 213 Software Methodology at Rutgers University

Please follow both Rutgers University's [Principles of Academic Integrity](http://academicintegrity.rutgers.edu/) and the Rutgers Department of Computer Science's [Academic Integrity Policy](https://www.cs.rutgers.edu/academic-integrity/introduction)

**Project Description**

In this project, you will implement an array-based container class **Library** and use it to hold the information of a list
of books owned by a library. An instance of the Library class is a growable bag data structure with an initial capacity
of 4 , and automatically grows (increases) the capacity by 4 whenever it is full. You are **NOT allowed** to use the
**ArrayList class, or** you will **get 0 points** for this project. A Kiosk class must be included in the project to provide
library services and process the command lines read from the console. The library services shall include **adding** ,
**removing** books to/from the library catalog, **checking out** and **returning** books, as well as **displaying** the list of
books by the date published or by the books’ serial numbers.

**Scanner class** and **StringTokener class** should be used to read the command lines from the console. When your
project starts running, it shall display "Library Kiosk running."; next, it will read and process the command
lines continuously until the user enters the “Q” command to quit. Before the project stops running, display "Kiosk
session ended."

A command line always begins with a command and followed by a comma and some data tokens. Each data token is
also delimited by a comma. Some examples of valid command lines are demonstrated below. All commands are case-
sensitive, which means the commands with lowercase letters are invalid. You are required to deal with bad commands
that are not supported.

- Adding a book

```
A,Programming in Java,11/20/
A is a command for adding a book to the library, followed by the name of the book, and the date published. If the
bag is full, the bag automatically grows the capacity by 4. A serial number will be automatically generated to create
the book instance with the name and the date published. The serial number is a five-digit number starting 10001,
which will be increased by 1 for each subsequent instance of Book. The date will always be in mm/dd/yyyy format.
However, you must check if the date is valid with the isValid() method in the Date class (see page #3 below.)
Display “Invalid Date!” if the date is invalid; otherwise, display “Programming in Java added to the
Library.” on the console when the book is added.
```

- Removing a book

```
R, 10005
R is a command for removing a book from the library given a book’s serial number. If the book doesn’t exist,
display "Unable to remove, the library does not have this book."; otherwise, display
“Book# 10005 removed.”.
```
- Checking out a book

```
O,
O is a command for checking out a book from the library. If the library doesn’t own the book, or the book has
already been checked out, display “Book#1005 is not available.”, otherwise, display “You’ve
checked out Book#10005. Enjoy!”.
```
- Returning a book

```
I,
I is a command for returning a book to the library. If the book doesn’t belong to the library, or the book is not
checked out, display "Unable to return Book#10005.”, otherwise, display “"Book# 10005 return
has completed. Thanks!"
```
- Printing the library catalog

```
PA //output the list of books to the console with the current sequence
PD //output the list of books by the dates published in ascending order
PN //output the list of books by the book numbers in ascending order
```
- **Q** command will stop the program and display "Kiosk session ended."
