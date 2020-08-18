# snakeandladder
<b> A snake and ladder program, <b>

<b>Input:</b><br>
The program takes input from command line and the user can define the snakes and ladder, the board size is fixed to 100

<b>Output: </b><br>
1. Game start 
2. Rolls thrown, re-roll in case of 6
3. Position after a total throw 
4. Error or informative messages
5. User input to play with the same configuration again

<b>Assumptions: </b><br>
1. The tokens can be moved once a dice throw of 1 or 6 is done
2. Snakes and ladders cannot have a common starting point
3. Ladders cannot start at position 0
4. Snakes and ladders cannot end at position 100
5. To complete the game the accurate figure of the board size has to be achieved so 95 + 6 = 101, doesn't finish the game on a board size of 100. Has to be 95 + 5. 
6. The dice moves to the total position, considering currentpostion = 1 + dice throw = 6 + 6 + 4 could have landed him on a ladder at position 7 (end point 98) but the user cannot land on the ladder and the final position will be 17
7. If 6 is rolled thrice the users total for the throw becomes 0 and the chance is taken <i>(Note: There is a bug here in the program, that this re-roll doesn't terminate on the third six but when the user throws a number apart from 6, but the counts are calculated properly) </i>

<b>Betterments: </b><br>
1. Unit test cases - Facing a problem using testNG, command line arguments are not being passed. Official JetBrains site mentions -Deditable.java.test.console=true in VM options as a solution, but doesn't work. Tried System.in with ByteArrayInputStream too but it didnt work 
2. Handle biased dice
3. Make Ladder and dice class inherit from Shortcut class and implement their own methods for extensibility
4. Multiplayer
5. Fix for Assumptions point 7
