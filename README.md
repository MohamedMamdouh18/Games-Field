# ***Games-Field***

- This project was built with [Scala](https://www.scala-lang.org/download/2.13.8.html) version 2.13.8.

## Authors:

> **Mohamed Mamdouh Rashad**
>
> **Youssef Ali Bazina**

## Table of Contents

- [Setup](#Setup)
- [Design Patterns](#Design-Patterns)
- [Design Decisions](#Design-Decisions)
- [Features and User Guide](#Features-and-User-Guide)
    - [Features](#Features)
    - [User Guide](#User-Guide)
    - [UI Samples](#UI-Samples)

## Setup

- The `build.sbt` will do the package installation for the app.

## Design Patterns

|   **Design Patterns**   |
|:-----------------------:|
|     Factory Pattern     |
| Template Method Pattern |
|    Observer Pattern     |
|    Mediator Pattern     |
|     Command Pattern     |

- **Factory Pattern** holds almost all the families in our app such that each game has its controller that inherits form
  the base controller same for game engine, drawer, pieces for each game and player classes for each game.
- **Template Method Pattern** the golden piece in our project it plays a vital role as it reduced number of lines in the
  chess pieces classes as almost all the pieces has the same logic but in different directions, so they have the same
  skeleton with some changes in the middle where the template method fits in like a charm.
- **Observer Pattern** holds the communication between the players and the game engine as the engine observes the
  players and the players hit the notify function when they play their move, then the engine checks if this was an
  available move or not after and if it was right it is executed and the engine flips the turn.
- **Mediator Pattern** where all classes talk to him, almost all calls from the players are redirected by it, for
  example when the player finishes his move, he calls the engine and tells him the move then the engine calls the
  controller to validate the move and if it was right the engine calls the drawer to draw the move.
- **Command Pattern** which it was used in small section, just to create the game player objects or get the new promoted
  piece in chess using a map.

## Design Decisions

- For each game in the application, We made mainly 4 components :
    - Engine
    - Controller
    - Drawer
    - Player


- The engine component is the master of components because it uses other components to play the game :
    - The controller component is used to validate the moves that each player does and check if the game is ended either
      by winning of one of the players or tie.
    - The drawer component is in charge of drawing board, pieces and update them according to the valid moves that was
      made.
    - The player components is responsible for taking the input of each player in the case of human players and to make
      the best move in the case of AI player then it notifies the engine -its observer- to validate the move.


- In each game, there can be a pieces which are separate components that carry the data of each piece. Each piece is
  assigned to a player which can control it in his game.


- We didn't use some rules in the Chess game like en passant and rule fifty , but we put many other rules and features
  like castling and promotion.

## Features and User Guide

### Features

- A friendly GUI that allows user to choose a game to play then change a mode among three modes :
    - PVP : player versus player and this is the default mode if you didn't choose any mode
    - PVA : player versus AI
    - AVP : AI versus player


- The application support three games : Chess , Tic-tac-toe and Connect-4.


- In each game if you decided to play with the AI you can select the difficulty of the AI from Easy, Normal -the default
  difficulty- and Hard but , it won't make any change if you choose a difficulty in PVP mode.


- In Chess game, every time you click on a piece of yours the board highlights the valid moves for this piece and if you
  make a move the tiles that the piece moved from and two are highlighted.

### User Guide

- Choose the mode that you want to play in then choose the game that you desire to play.


- **Note** When choosing hard difficulty in chess game, It may take up to three minutes for the AI to calculate the best
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

![image](https://drive.google.com/uc?export=view&id=1itVk68lyXHdWMnsHG1UkY1DBDA98NQom)
