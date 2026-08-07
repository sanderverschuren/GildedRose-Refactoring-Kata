# Prompt History

## 1. Exploring options for capturing current behaviour

> I have this legacy code in Kotlin/src/main/kotlin/com/gildedrose/GildedRose.kt that I want to refactor into something more maintable, understandeable. Before changing the code itself, I want to capture the current behaviour in tests to make sure we're not changing the behaviour during the refactoring. What options are there to capture the current behaviour?

## 2. Choosing Option A with ApprovalTests

> Plan looks good, now execute this

(Referring to Option A: Approval/Golden Master Testing using ApprovalTests)

## 3. Removing combination tests from the plan

> There is no need for the combination approval tests, the golden master test is sufficient and already covers the edge cases

## 4. Planning with ApprovalTests

> Plan for option A using Approvaltests

## 5. Approving and executing the plan

> This plan looks good. Save it and execute it

## 6. Avoiding code repetition and reusing TexttestFixture

> There was already a TexttestFixture.kt that could have been used to generate the golden master. Create a steering file in .kiro/steering that makes it explicit that we want to avoid code repetition. After updating the steering docs, update the approval tests so it uses the TexttestFixture and captures the system output in a golden master text file.

## 7. Writing prompt history

> Write my prompt history to .kiro/prompts
