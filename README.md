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

- **Factory Pattern** holds almost all the families in our app such that each game has its controller that inherits form the base controller same for game engine, drawer, pieces for each game and player classes for each game.
- **Template Method Pattern** the golden piece in our project it plays a vital role as it reduced number of lines in the chess pieces classes as almost all the pieces has the same logic but in different directions, so they have the same skeleton with some changes in the middle where the template method fits in like a charm.
- **Observer Pattern** holds the communication between the players and the game engine as the engine observes the players and the players hit the notify function when they play their move, then the engine checks if this was an available move or not after and if it was right it is executed and the engine flips the turn.
- **Mediator Pattern** where all classes talk to him, almost all calls from the players are redirected by it, for example when the player finishes his move, he calls the engine and tells him the move then the engine calls the controller to validate the move and if it was right the engine calls the drawer to draw the move.
- **Command Pattern** which it was used in small section, just to create the game player objects or get the new promoted piece in chess using a map.

## Design Decisions

- Each Queue has an ID numbered (0, 1, 2, …) and the last queue ID is -1.
    ```java
            @Override
            public void run() {
            boolean CheckFinish = false;
            while (!CheckFinish) {
            CheckFinish = true;
            
                    for (var Component : ChainBefore) {
                        CheckFinish &= Component.IsFinished();
                    }
            
                    if (ID == 0) CheckFinish &= TheSystem.FinishedAiring.get();
            
                    if (CheckFinish) {
                        MyQueue.BeforeFinished.set(true);
                    }
            
                    CheckFinish &= MyQueue.ToSend.isEmpty();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                IsFinished.set(true);
            }
            ```

## Features and User Guide

### Features

- A friendly GUI that allows user to insert queues, machines connect them with arrows, move them around, animation that
  shows products movement between the different components.

### User Guide

- In the beginning Q0 (is called **QStart**), Q-1 (is called **QEnd**) are placed on the right, left of the screen. They
  shouldn’t be removed or moved.

### UI Samples

- Main Menu.

![image](https://drive.google.com/uc?export=view&id=1nH8Jqw6ucJfhvAFgt9FOzC0o4LDx-pje)

- Chess Sample 1.

![image](https://drive.google.com/uc?export=view&id=1xGA4a0jNgUFaKdHJGVFGZpuerwVM-IaT)

- Chess Sample 2.

![image](https://drive.google.com/uc?export=view&id=1YML-Tncfp1HEaKKBlUNv0hfj8PwuUR5T)

- Chess Sample 3.

![image](https://drive.google.com/uc?export=view&id=1NbRPo1qg1Ypq_wQfOkNai8LQTgMmdasY)

- Connect-4.

![image](https://drive.google.com/uc?export=view&id=1xAKPkq2W0BToWjqYz8MRGqx30GUDrIZ0)

- Tic-Tac-Toe.

![image](https://drive.google.com/uc?export=view&id=1jCFwh8PBdieP5sas9DgD1d4T5GDIPir9)

- Gameplay.

![image](https://drive.google.com/uc?export=view&id=1SyAqIbW-H_23aWvRZdXwchLRBdOoDC9J)
