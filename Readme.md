# connect4Py
Connect4 in python! For interviewing people who know some python.

## Information

* We have set the project up so you can focus on the requirements, no need to worry about rendering anything to the screen.  Anything in app.py is not part the test, though if you finish the test, feel free to refactor it.
* The scaffold class (connect_4.py) has some expectations for it to work correctly:
    * has a board consisting of an nested array of strings
    * the string is only one character long
    * any errors thrown in connect_4.py will not alternate to the other player
    
    
## Run the tests

```
➜  connect4py git:(master) ✗ python -m venv venv
➜  connect4py git:(master) ✗ source venv/bin/activate
(venv) ➜  connect4py git:(master) ✗ python -m unittest test/connect_4_test.py
.
```   
## User Requirements

```
As a Player,
I want to be able to select a column
So that I can play the game
```

```
As a Player,
I want to have a different identifier for my pieces
So that I know which pieces are mine
```

```
As a Player,
I want to be able to win by connecting 4 of my characters horizontally
So that I can beat my opponent
```

```
As a Player,
I want to be able to win by connecting 4 of my characters vertically
So that I can beat my opponent
```

```
As a Player,
I want to be able to win by connecting 4 of my characters diagonally
So that I can destroy my opponent
```

```
As a Games Master,
I want to be able to only accept column numbers
So that I know which column to insert the piece for that player
```

```
As a Games Master,
I do not want Players to be able to insert their pieces into columns which are already full
So that I can keep play within bounds
```

```
As a Games Master,
When there's no more room to insert pieces and there's no winner
I want to declare the game a draw
```


```
