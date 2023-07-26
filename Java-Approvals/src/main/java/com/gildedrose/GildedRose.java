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
            if (isSulfuras(item)) {
            } else {
                item.sellIn = item.sellIn - 1;
            }

            if (isSulfuras(item)) {
            } else if (isBackstagePass(item)) {
                increaseQuality(item);
                if (item.sellIn < 10) {
                    increaseQuality(item);
                }
                if (item.sellIn < 5) {
                    increaseQuality(item);
                }
                if (item.sellIn < 0) {
                    item.quality = 0;
                }
            } else if (isAgedBrie(item)) {
                increaseQuality(item);
                if (item.sellIn < 0) {
                    increaseQuality(item);
                }
            } else {
                decreaseQuality(item);
                if (item.sellIn < 0) {
                    decreaseQuality(item);
                }
            }
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality = item.quality - 1;
        }
    }

    private static void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
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
}
