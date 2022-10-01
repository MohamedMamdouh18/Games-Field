# ***Games-Field***

- This project was built with [Scala](https://www.scala-lang.org/download/2.13.8.html) version 2.13.8.

## Authors:

> **Mohamed Mamdouh Rashad**
>
> **Youssef Ali Bazina**
>

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

- Full Program Simulation.

![image](https://drive.google.com/uc?export=view&id=1tz_cxeQzo1uPOD6al4LZMYnCPtGluSzw)
