package com.gildedrose;

class GildedRose {
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void endDay() {
        for (Item item : items) {
            updateSellIn(item);
            updateQuality(item);
        }
    }

    private static void updateQuality(Item item) {
        if (isSulfuras(item)) {
        } else if (isBackstagePass(item)) {
            increaseQuality(item, 1);
            if (expiresInDays(item, 10)) {
                increaseQuality(item, 1);
            }
            if (expiresInDays(item, 5)) {
                increaseQuality(item, 1);
            }
            if (isExpired(item)) {
                increaseQuality(item, -item.quality);
            }
        } else if (isAgedBrie(item)) {
            increaseQuality(item, 1);
            if (isExpired(item)) {
                increaseQuality(item, 1);
            }
        } else {
            increaseQuality(item, -1);
            if (isExpired(item)) {
                increaseQuality(item, -1);
            }
        }
    }

    private static boolean expiresInDays(Item item, int days) {
        return item.sellIn < days;
    }

    private static boolean isExpired(Item item) {
        return expiresInDays(item, 0);
    }

    private static void updateSellIn(Item item) {
        if (isSulfuras(item)) {
        } else {
            item.sellIn = item.sellIn - 1;
        }
    }

    private static void increaseQuality(Item item, int number) {
        item.quality = limitQualityRange(item.quality + number);
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static int limitQualityRange(int value) {
        return Math.min(Math.max(value, MIN_QUALITY), MAX_QUALITY);
    }
}
