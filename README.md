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

[Show changes](https://github.com/FabianHen/Refactoring-Exercise/commit/20f08b14118dadafe76c27edfbb7880fca61d419)

### Refactoring Patterns

#### Strategy Pattern
Since the `updateQuality` method was very long and complex, I used the Strategy Pattern to encapsulate the different behaviors of the items. For each item, a separate class was created that implements the behavior of that specific item. This allowed the `updateQuality` method to be significantly simplified, as it now only needs to check which item it is and then call the corresponding strategy object.

Instead of the usual approach of creating an interface for the strategies, I chose to use an abstract class. I did this because for all items, the `quality` must not be greater than `50` or less than `0`. I implemented this logic in the abstract class to avoid redundancy in the form of repeated checks like `item.quality < 50` or `item.quality > 0`.

Although no changes are made for the `Sulfuras` item, I still created a dedicated strategy class for it. This ensures consistency and makes future extensions or modifications easier.

[Show changes](https://github.com/FabianHen/Refactoring-Exercise/commit/b50f308a45e0b32093b117b8626280d6f34ad77f)

#### Factory Pattern
After implementing the Strategy Pattern, the `updateQuality` method was reduced to only 9 lines and is already very clear. However, since the name of the `GildedRose` class suggests broad functionality, I decided to implement the Factory Pattern. This moves the responsibility for creating the item strategies from the `GildedRose` class to a separate factory class. This improves adherence to the Single Responsibility Principle and makes the code even more maintainable.

[Show changes](https://github.com/FabianHen/Refactoring-Exercise/commit/c91894af506dc89ec9134231f9b587889894e85f)

### Implementation of the Desired Changes
Once the refactorings were completed, I implemented the requested changes. The extension involved adding a new item type called `Conjured`, which deteriorates twice as fast as normal items.

During the implementation, I was uncertain because, as described, there are three possible interpretations:
1. `Conjured` is an attribute of the `Item` class and would need to be considered in the existing strategy classes.
2. `Conjured` is part of the item names (e.g., `Conjured Cheddar`). In this case, the factory class would need to check if the name starts with `Conjured`.
3. `Conjured` is its own name and can simply be implemented as a separate strategy class and handled with a single line in the factory class.

Since the third interpretation seemed the most sensible in the context of the refactoring, I chose this approach. A dedicated strategy class called `ConjuredItemStrategy` was created to implement the behavior of the `Conjured` items. In the factory class, an additional condition was added to account for this new strategy.

[Show changes](https://github.com/FabianHen/Refactoring-Exercise/commit/3bda9de45b177db68338ed7b415f12ffa99fdf78)

## Tennis
### Golden Copy
The project already contains the tests required for a golden copy. These describe the behavior of the tennis game using an array of points with the corresponding game results.
### General Adjustments
### Refactoring Patterns
### Implementation of the Desired Changes

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