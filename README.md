# LSP Refactoring

## Table of Contents
1. [Golden Copy – General](#golden-copy--general)
2. [Gilded Rose](#gilded-rose)
   - [Golden Copy](#golden-copy)
   - [General Adjustments](#general-adjustments)
   - [Refactoring Patterns](#refactoring-patterns)
3. [Tennis](#tennis)
   - [Golden Copy](#golden-copy-1)
   - [General Adjustments](#general-adjustments-1)
   - [Refactoring Patterns](#refactoring-patterns-1)
4. [Trip Service](#trip-service)
   - [Golden Copy](#golden-copy-2)
   - [General Adjustments](#general-adjustments-2)
   - [Refactoring Patterns](#refactoring-patterns-2)
5. [Expense Report](#expense-report)
   - [Golden Copy](#golden-copy-3)
   - [General Adjustments](#general-adjustments-3)
   - [Refactoring Patterns](#refactoring-patterns-3)

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

### Refactoring Patterns
#### Strategy Pattern  
#### Factory Pattern  

## Tennis
### Golden Copy
### General Adjustments
### Refactoring Patterns

## Trip Service
### Golden Copy
### General Adjustments
### Refactoring Patterns

## Expense Report
### Golden Copy
### General Adjustments
### Refactoring Patterns
