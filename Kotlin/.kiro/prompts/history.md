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

## 8. Refactoring the legacy code

> The specification as described in `Kotlin/.kiro/specification/gilded-rose-specification.md` should already be implemented and we assume it is correct, as proven by the golden master. The code however is very hard to understand. Refactor it into clean, readable, maintainable code. Take small steps. Validate after each step that the code still complies with the golden master. After each meaningfull step, commit the code with a clear commit message explaining what has been done and what has improved code quality wise.

## 9. Introducing domain language

> Introduce some for of domain language and let that be reflected in the code. When an items sellIn is less than zero, we say the item is `expired`.

## 10. Separating concerns - sellIn vs quality

> The methods are too big. It would be better of the updating of the sellIn and the updating of the quality is not mixed in every method.

## 11. Moving Sulfuras guard lower

> Move the early return for Sulfuras item lower in both the updating of the sellIn and the quality

## 12. Eliminating magic strings

> Make sure there are no magic strings where typos could creep into

## 13. Pure functions design

> I don't like the fact that every method is mutating the Item. Can we change the design so that most functions are pure functions?

## 14. Pure functions with Item as parameter

> I prefer to use pure functions as they are easier to reason about. The only function that is allowed to mutate an Item is `updateQuality`. It is OK to use item as a method parameter, no need to pass the name, sellIn and quality as separate parameters

## 15. Moving Sulfuras into calculation functions

> Move the Sulfuras exceptional case both in calculateQuality and the calculation of the SellIn

## 16. Adding edge case test for Aged Brie

> Add a specific unit test where Aged Brie has a negative quality and needs to be sold in 3 days. The test should prove that the quality of aged brie should not be negative and be corrected to 0.

## 17. Fixing the failing test

> Yes, fix the test

## 18. Finding other uncovered edge cases

> Are there any other edge cases that are currently not in line with the specification and not covered in the approval tests?

## 19. Adding tests and fixes for all edge cases

> Yes

## 20. Improving test readability

> Change the test `Aged Brie with negative quality should be corrected to 0` so I can see both the original Item: Item("Aged Brie", 3, -5) and the expected item: Item("Aged Brie", 2, 0) and compare this expectation with the actual item. We cannot change the equals method on Item itself.

## 21. Making all tests consistent

> Make the other unit tests follow the example of `Aged Brie with negative quality should be corrected to 0`

## 22. Updating golden master for Conjured items

> Let the new requirement in `conjured_items_specification.md` be reflected in the golden master. DO not update the production code yet.

## 23. Implementing Conjured items

> Implement the new requirement in the production code

## 24. Extracting quality bound constants

> Move the magic number for the quality bounds into constants

## 25. Updating prompt history

> Update my prompt history so it is up to date
