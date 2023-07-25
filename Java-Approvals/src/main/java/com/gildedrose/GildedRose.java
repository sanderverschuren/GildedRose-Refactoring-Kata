package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (isSulfuras(item)) {
                continue;
            } else {
                item.sellIn = item.sellIn - 1;
            }

            if (isBackstagePass(item)) {
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
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
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
