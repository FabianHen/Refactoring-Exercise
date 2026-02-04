# Refactoring Exercise

## Table of Contents
1. [Golden Copy – General](#golden-copy--general)
2. [Gilded Rose](#gilded-rose)
   - [Golden Copy](#golden-copy)
   - [General Adjustments](#general-adjustments)
   - [Refactoring Patterns](#refactoring-patterns)
      - [Strategy Pattern](#strategy-pattern)
      - [Factory Pattern](#factory-pattern)
   - [Implementation of the Desired Changes](#implementation-of-the-desired-changes)
3. [Tennis](#tennis)
   - [Golden Copy](#golden-copy-1)
   - [General Adjustments](#general-adjustments-1)
   - [Refactoring Patterns](#refactoring-patterns-1)
   - [Implementation of the Desired Changes](#implementation-of-the-desired-changes-)
4. [Trip Service](#trip-service)
   - [Golden Copy](#golden-copy-2)
   - [General Adjustments](#general-adjustments-2)
   - [Refactoring Patterns](#refactoring-patterns-2)
   - [Implementation of the Desired Changes](#implementation-of-the-desired-changes-1)
5. [Expense Report](#expense-report)
   - [Golden Copy](#golden-copy-3)
   - [General Adjustments](#general-adjustments-3)
   - [Refactoring Patterns](#refactoring-patterns-3)
   - [Implementation of the Desired Changes](#implementation-of-the-desired-changes)

## Golden Copy – General
To ensure that the functionality of applications is preserved during refactoring, a "Golden Copy" should theoretically be created for each application. This Golden Copy serves as a reference point to ensure that all changes are correctly implemented and no unexpected errors are introduced.  

To create a Golden Copy, tests must be written for each behavior of the application to verify the expected behavior. These tests should cover all current behaviors and ensure that the application works as intended. For each behavior of the application, the following steps are performed:

1. The desired input is passed to the application.  
2. The result is recorded in a test case using an assert.  
3. If the result changes, the test fails and indicates that the change affected the application's behavior. If all tests pass, it can be assumed that the changes did not introduce unexpected errors.  

Refactorings should only be performed after the Golden Copy has been created. After each refactoring, the tests of the Golden Copy should be run again to ensure that the functionality of the application remains intact. The following sections briefly describe the theoretical approach for the respective Golden Copy of each application. As discussed, the Golden Copies in this repository are not implemented.

## Gilded Rose
### Golden Copy
In this project, tests should be written for each item and its behavior to create a Golden Copy. The following behaviors, in addition to boundary tests, should be covered:

1. **Normal Items** – daily quality decrease; twice as fast after SellIn date  
2. **Aged Brie** – quality increases; twice when SellIn < 0, capped at 50  
3. **Sulfuras** – immutable quality (80) and unchanged SellIn  
4. **Backstage passes** – +1 (>10d), +2 (≤10d), +3 (≤5d), 0 after event  
5. **Invariants** – Quality ∈ [0,50] (except Sulfuras 80), SellIn decrements (except Sulfuras)  

### General Adjustments
To improve code readability, a few general adjustments were made at the start of the refactoring. These include:

- Converting `for` loops to `foreach` loops for better readability.  
- Using `++` and `--` for incrementing/decrementing variables instead of `foo = foo + 1` or `foo = foo - 1`.  
- Extracting strings into constants for better readability and reduced error potential.  
- As mentioned in the project description, both the `updateQuality` method and the `items` list were made static to avoid the need to instantiate the `GildedRose` class.  
- Since the `Sulfuras` item does not change, a check with a `continue` statement was added to avoid unnecessary checks.  
- Instead of checking with `!item.name.equals(FOO)` combined with nesting and handling in the else block, the logic was reversed to use `item.name.equals(FOO)`. This reduces nesting and improves code readability.

[View changes](https://github.com/FabianHen/Refactoring-Exercise/commit/20f08b14118dadafe76c27edfbb7880fca61d419)

### Refactoring Patterns

#### Strategy Pattern
Since the `updateQuality` method was very long and complex, I used the Strategy Pattern to encapsulate the different behaviors of the items. For each item, a separate class was created that implements the behavior of that specific item. This allowed the `updateQuality` method to be significantly simplified, as it now only needs to check which item it is and then call the corresponding strategy object.

Instead of the usual approach of creating an interface for the strategies, I chose to use an abstract class. I did this because for all items, the `quality` must not be greater than `50` or less than `0`. I implemented this logic in the abstract class to avoid redundancy in the form of repeated checks like `item.quality < 50` or `item.quality > 0`.

Although no changes are made for the `Sulfuras` item, I still created a dedicated strategy class for it. This ensures consistency and makes future extensions or modifications easier.

[View changes](https://github.com/FabianHen/Refactoring-Exercise/commit/b50f308a45e0b32093b117b8626280d6f34ad77f)

#### Factory Pattern
After implementing the Strategy Pattern, the `updateQuality` method was reduced to only 9 lines and is already very clear. However, since the name of the `GildedRose` class suggests broad functionality, I decided to implement the Factory Pattern. This moves the responsibility for creating the item strategies from the `GildedRose` class to a separate factory class. This improves adherence to the Single Responsibility Principle and makes the code even more maintainable.

[View changes](https://github.com/FabianHen/Refactoring-Exercise/commit/c91894af506dc89ec9134231f9b587889894e85f)

### Implementation of the Desired Changes
Once the refactorings were completed, I implemented the requested changes. The extension involved adding a new item type called `Conjured`, which deteriorates twice as fast as normal items.

During the implementation, I was uncertain because, as described, there are three possible interpretations:
1. `Conjured` is an attribute of the `Item` class and would need to be considered in the existing strategy classes.
2. `Conjured` is part of the item names (e.g., `Conjured Cheddar`). In this case, the factory class would need to check if the name starts with `Conjured`.
3. `Conjured` is its own name and can simply be implemented as a separate strategy class and handled with a single line in the factory class.

Since the third interpretation seemed the most sensible in the context of the refactoring, I chose this approach. A dedicated strategy class called `ConjuredItemStrategy` was created to implement the behavior of the `Conjured` items. In the factory class, an additional condition was added to account for this new strategy.

[View changes](https://github.com/FabianHen/Refactoring-Exercise/commit/3bda9de45b177db68338ed7b415f12ffa99fdf78)

## Tennis
### Golden Copy
The project already contains the tests required for a golden copy. These describe the behavior of the tennis game using an array of points with the corresponding game results.
### General Adjustments
To improve code readability here as well, a few general adjustments were made at the beginning of the refactoring. These changes include:

- Replacing string comparisons using `==` with `equals` methods.
- Extracting the "magic number" `4` into the constant `WIN_THRESHOLD` to improve readability and reduce the risk of errors.
- Creating `getTieScore()`, `getWinScore()`, and `getNormalScore()` using the Extract Method refactoring within the `getScore()` method to improve readability.
- Using a `StringBuilder` instead of `+=` in the `getNormalScore()` method.

[View changes](https://github.com/FabianHen/Refactoring-Exercise/commit/c3cad12a1a1035253869223f9872dca9276d178e)

### Refactoring Patterns
#### State Pattern
Since the project is a simulation of a game with multiple states, I decided to use the State Pattern. For each possible game state, a separate class is created that implements the behavior for that state. This greatly simplifies the `getScore()` method, as it now only needs to query the current state and call the corresponding method. Transitions to other states are handled by methods within the respective state classes. The following states were implemented:

- `NormalState` – regular game score  
- `DeuceState` – tie after a regular game  
- `AdvantageState` – advantage for a player after a regular game  
- `WinState` – victory for a player  

During the implementation, I also made a few additional general adjustments:

- Extracted the names of the score values into a `String` array for more readable code. This logic is now found in `NormalState`.  
- Applied Extract Class with the `Player` class to encapsulate player information and facilitate planned future changes.

[View changes](https://github.com/FabianHen/Refactoring-Exercise/commit/cbd6d80d73df4bf90154356a449bc210d5806333)

### Implementation of the Desired Changes
The task description already pointed out that this error was intentionally introduced:

*"The player names are hard-coded as 'player1' and 'player2'. After refactoring, you should fix this issue and add appropriate test cases to verify that your correction works."*

As mentioned earlier, I had already extracted the `Player` class, which made this step easier. I only needed to access the names of the `Player` objects in the `TennisGame1` class instead of using the hard-coded strings.

Additionally, in this file, I converted the `else` block into an `else if` block. This does not change the fundamental behavior of the game. Now, only when an invalid player name is entered will the second player's score no longer be incremented.

Another change required by the given task was adjusting the `getScore()` method in the `AdvantageState` and `WinState` classes. These methods now also access the names of the `Player` objects.


[View Changes](https://github.com/FabianHen/Refactoring-Exercise/commit/64357bb7d1031c81344e39fa1cd605a20b8de86b)

### Adding and Adjusting Test Cases
With the introduction of the `Player` objects, the internal scoring logic changed:  
The `wonPoint()` method now compares the passed player names with the names of the `Player` objects.

The existing test cases did not need to be changed, as they still pass the correct player names. However, it is important that the strings used in the tests match exactly the names used when the TennisGame was created.

To ensure consistency, the player names in the tests could either be defined as constants or set globally once. This behavior can also be documented with an additional test case that verifies the scoring logic fails if names are used inconsistently.

### Handling the Other Files
After reviewing the remaining TennisGame classes, I found that they all represent the same business logic, but with different structure and style. The differences were exclusively in the way points are counted, how the score is displayed, and the internal variable naming:

- **TennisGame1** uses a long `getScore()` method with a for loop and switch statements.  
- **TennisGame2** used a massive if/else structure.  
- **TennisGame3** uses overly complex conditions to minimize the number of lines.  
- **TennisGame4** utilizes many chained `ResultProvider` objects, making the classes hard to understand and cluttered.  
- **TennisGame5** stores all possible scores up to `4` in a map and accesses them.  
- **TennisGame6** is similar to TennisGame1 but uses two `switch` statements instead of a double for loop.  
- **TennisGame7** is like TennisGame6 but additionally formats the score with a message such as "Current score: …, enjoy your game!"

Since all of these files serve the same function, but deleting them would cause tests to fail, I decided to implement the remaining versions (TennisGame2–7) by delegating to `TennisGame1`. This way, the observable behavior remains completely identical (all tests stay green) without having to refactor each implementation individually.

[View Changes](https://github.com/FabianHen/Refactoring-Exercise/commit/97f84f5ebf3d2f5c537096ea4509e519c67b53f4)


## Trip Service
### Golden Copy
### General Adjustments
### Refactoring Patterns
### Implementation of the Desired Changes

## Expense Report
### Golden Copy
### General Adjustments
### Refactoring Patterns
### Implementation of the Desired Changes