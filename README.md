#WordPuzzleSolver 

WordPuzzleSolver is a Java application that takes in a .txt file puzzle, scans the puzzle for given words, and prints out their location if found.

The input file mimics the following layout:
```
 5 6
turtle
waterb
elofem
evodek
pckrse
turtle
dove
pet
packers
water
fee
stlouisrams
eeeeee
```
where the first two numbers, ```5 6``` represent *x* rows and *y* columns in the puzzle. This must then be followed by *x* rows of characters that
do not exceed column *y*'s length.
This is then followed by any number of lines consisting of words to search the puzzle for.

The program outputs to the console and mimics the following layout:

```
"turtle" was found at row 0, column 0, going R.
"dove" was found at row 3, column 3, going L.
"pet" was not found.
"packers" was not found.
"water" was found at row 1, column 0, going R.
"fee" was found at row 2, column 3, going DR.
"stlouisrams" was not found.
"eeeeee" was not found.
```

WordPuzzleSolver was written for my Software Design III course at the University of Wisconsin - La Crosse.
