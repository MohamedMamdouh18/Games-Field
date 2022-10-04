# ***Games-Field***

- This project was built with [Scala](https://www.scala-lang.org/download/2.13.8.html) version 2.13.8
  and [Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) version 17.0.2.
- The Executable file [link](https://github.com/MohamedMamdouh18/Games-Field/tree/master/out/artifacts/Games_Field_jar).
    - If you have Java 17 or above, only double-click on th JAR file
    - otherwise, open the cmd where the file lies in and write this command java -jar GamesField.jar

## Authors
> **Youssef Ali Bazina**
> 
> **Mohamed Mamdouh Rashad**

## Table of Contents

- [Setup](#Setup)
- [UML Diagram](#UML-Diagram)
- [Design Patterns](#Design-Patterns)
- [Design Decisions](#Design-Decisions)
- [Features and User Guide](#Features-and-User-Guide)
    - [Features](#Features)
    - [User Guide](#User-Guide)
    - [UI Samples](#UI-Samples)

## Setup

- The `build.sbt` will do the package installation for the app.

## UML Diagram

![image](https://drive.google.com/uc?export=view&id=1yInaMkfGBgFTBl58ueqNilCCnLjsEYHh)

## Design Patterns

|   **Design Patterns**   |
|:-----------------------:|
|     Factory Pattern     |
| Template Method Pattern |
|    Observer Pattern     |
|    Mediator Pattern     |
|     Command Pattern     |
|    Singleton Pattern    |

- **Factory Pattern** holds almost all the families in our app such that each game has its controller that inherits from
  the base controller, same for the game engine, drawer, pieces for each game and player classes for each game.
- **Template Method Pattern** is the golden piece in our project. It reduced the number of lines in the chess pieces
  classes, as almost these pieces have the same logic but in different manners, so they have the same skeleton with
  some changes in the middle where the template method fits in like a charm.
- **Observer Pattern** holds the communication between the players and the game engine. The engine observes the players,
  and the players hit the notify function when they play their move and then the engine checks if this was an available
  move. After that, if the movement is valid, it is executed, and the engine flips the turn.
- **Mediator Pattern** where all classes talk to him, almost all calls from the players go to the engine then the engine
  redirects them. For example, when the player finishes his move, he calls this engine and then sends it to this
  movement. After that engine will call the controller to validate this move. And if it is valid, the engine will call
  the drawer to draw this movement.
- **Command Pattern** is used in a small section to create the game player objects or get the new promoted piece in
  chess using a map.
- **Singleton Pattern** is the backbone of this game because the engine, the controller and the drawer should have one
  instance for the entire session.

## Design Decisions

- For each game in the application, We made mainly four components :
    - Engine
    - Controller
    - Drawer
    - Player


- The engine component is the master of these components because it holds the communication between other ones to
  run the game:
    - The controller component validates the movements that each player does. And check if the game is ended either
      by winning one of the players or by a tie.
    - The drawer component is in charge of drawing the board, and the pieces, also updating them according to the recent
      moves.
    - The player component is responsible for taking the input of each player in the case of the human player and making
      the best move in the case of the AI player. Then it notifies the engine -its observer- to validate this move.


- In each game, there can be pieces which are separate components that carry the data of each piece. Each piece goes to
  a player who can control it in his game (for chess specifically).


- We didn't use some rules in the Chess game like en passant and rule 50, but we handled the basic rules and features
  like castling, promotion, etc.

## Features and User Guide

### Features

- A friendly GUI that allows users to choose a game to play and then change a mode among three modes:
    - PVP: player versus player. It is the default mode if you don't select anything.
    - PVA: player versus AI.
    - AVP: AI versus player.


- The application supports three games: Chess, Tic-tac-toe and Connect-4.

- In each game session, if you decide to play against an AI, you can select the difficulty of the AI from Easy, Normal
  -the
  default difficulty- and Hard, but it won't make any changes if you choose a difficulty level in PVP mode.

- In a Chess game, every time you click on a piece of yours. The board highlights the valid movements for this piece,
  and if you make one move, the tiles that this piece moved from and to will get highlighted.

### User Guide

- Choose the mode that you want to play in, then choose the game that you desire to play.


- **Note** When choosing hard difficulty in a chess game, It may take up to three minutes for the AI to calculate the
  best
  move.

### UI Samples

- Main Menu 1.

![image](https://drive.google.com/uc?export=view&id=1SxRkbAeN9FQrAsRVoOFxzdX3RYv1F1tu)

- Main Menu 2.

![image](https://drive.google.com/uc?export=view&id=18DyBMSBORJMSVyC51hk1X9_gTFXjHdUB)

- Chess.

![image](https://drive.google.com/uc?export=view&id=1ijrYQpYjwks6fBdw4pPxz6MuAUMSPlTZ)

- Tic-Tac-Toe.

![image](https://drive.google.com/uc?export=view&id=1cchQZHvFvYJE5WzqTekQ9eSYf4Ab9lP_)

- Connect-4.

![image](https://drive.google.com/uc?export=view&id=1cZmmaWHwLWmqelnP9I5j-Fl_OCpJcQns)

- Gameplay.

![image](https://drive.google.com/uc?export=view&id=19rq8MakA5EYObI2usjN9zuV4YmS2hrn4)
