package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void endDay() {
        items = Arrays.stream(items)
            .map(ItemDecorator::new)
            .map(ItemDecorator::progressToNextDay)
            .map(ItemDecorator::getItem)
            .toArray(Item[]::new);
    }
}
