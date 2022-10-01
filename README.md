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

| **Design Patterns** |
|:-------------------:|
|  Observer Pattern   |

- **Observer** pattern is used, publisher: QueueController, observers: MachineComponents, Queue loops over the next
  machines and updates them.

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
      the best move in the case of AI player then it notifys the engine -its observer- to validate the move.


- In each game, there can be a pieces which are separate components that carry the data of each piece. Each piece is
  assigned to a player which can control it in his game.

en passant - rule 50

- We didn't use some rules in the Chess game like en passant and rule fifty , but we put many other rules and features
  like castling and promotion.

## Features and User Guide

### Features

- A friendly GUI that allows user to choose a game to play then change a mode among three modes :
    - PVP : player versus player
    - PVA : player versus AI
    - AVP : AI versus player


- The application support three games : Chess , Tic-tac-toe and Connect-4.


- In Chess game, every time you click on a piece of yours the board highlights the valid moves for this piece and if you
  make a move the tiles that the piece moved from and two are highlighted.

### User Guide

- Choose the mode that you want to play in then choose the game that you desire to play.

### UI Samples

- Full Program Simulation.

![image](https://drive.google.com/uc?export=view&id=1tz_cxeQzo1uPOD6al4LZMYnCPtGluSzw)
