# Sudoku-Game-with-SQL-Database


Business Logic description:
The sudoku.java class is responsible for handling all the activity on the sudoku
board including getting a free cell list, resetting the board, and calculating whether
there are available cells.
It also handles the overall logic of filling the board with exact solutions for the
puzzle and sets the board with appropriate values for level easy, medium and
hard.
We have used the algorithm similar to the N-queens problem to identify the correct
answer and each time the game is reset the values will also be changed according
to random numbers generated by the random number generator function in class
sudoku.
Persistence Logic Description:
The first implementation was the Highscore. Highscore was created by creating a
textfield through which a player could insert their Name and converting the already
inbuilt Timer of the game into a form of Score.
We have implemented the top 5 high scores as well as once a player finishes the
game, their previous best would be previewed.
The second implementation was the Load/Save Game. Since Sudoku
coincidentally works on Rows and Columns, I was able to insert the rows and
columns into a Database Table.
Two separate Tables were made, one to store the Grid (Actual values of the grid)
and another to store the User’s inputs + Values pre-filled for User.
Following the Principle of Single Responsibility, Each of a) Highscore, b) Saving
and Loading the Grid
And c) Saving and Loading the User Inputs were made as separate Classes which
conduct their own individual functionalities.
Following the Principle of Open Close, when creating the Database, extra effort
was made to not break any existing functionality but to extend it instead.
Front UI description:
Panal.java forms the crux of the application. Since we have implemented the
Facade Design pattern, the Panel is responsible for being the main class
interacting with the user. Panel also interacts with the persistence (GridLayout,
saveLoad) and game logic (sudoku.java).
Making use of java frameworks such as Swing and AWT, The UI has been created
to change colors on the event of a mouse click or hover.
The panel also helps create the empty grid layout and interacts with the sudoku
class to add and check values. It displays dialog boxes on submission or
whenever there are errors by the user.


![image](https://github.com/N1thin24/Sudoku-Game-with-SQL-Database/assets/107985125/ac9392ca-f351-4ce9-a24b-7f6a9cd04023)
![image](https://github.com/N1thin24/Sudoku-Game-with-SQL-Database/assets/107985125/07f2e8b1-4f79-4f17-b603-d5be42b51ec3)

