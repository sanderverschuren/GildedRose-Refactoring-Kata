package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void endDay() {
        items = Arrays.stream(items)
            .map(ItemAdapter::new)
            .map(ItemAdapter::progressToNextDay)
            .map(ItemAdapter::getItem)
            .toArray(Item[]::new);
    }
}
