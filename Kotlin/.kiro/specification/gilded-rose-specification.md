# Gilded Rose Requirements Specification

## High level context

Hi and welcome to team Gilded Rose. As you know, we are a small inn with a prime location in a
prominent city ran by a friendly innkeeper named Allison. We also buy and sell only the finest goods.
Unfortunately, our goods are constantly degrading in `Quality` as they approach their sell by date.

We have a system in place that updates our inventory for us. It was developed by a no-nonsense type named
Leeroy, who has moved on to new adventures. Your task is to add the new feature to our system so that
we can begin selling a new category of items. 

## Base Rules

- All `items` have a `SellIn` value which denotes the number of days we have to sell the `items`
- All `items` have a `Quality` value which denotes how valuable the item is
- At the end of each day our system lowers both values for every item
- Once the sell by date has passed, `Quality` degrades twice as fast
- The `Quality` of an item is never negative
- The `Quality` of an item is never more than `50`

## Exceptions

- "Aged Brie" actually increases in `Quality` the older it gets
- "Sulfuras" is a legendary item and as such its `Quality` is `80` and it never alters.
- "Sulfuras", being a legendary item, never has to be sold or decreases in `Quality`
- "Backstage passes", like aged brie, increases in `Quality` as its `SellIn` value approaches;
  - `Quality` increases by `2` when there are `10` days or less and by `3` when there are `5` days or less but
  - `Quality` drops to `0` after the concert
