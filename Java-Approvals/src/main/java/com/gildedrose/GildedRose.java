package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item) || isBackstagePass(item)) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (isBackstagePass(item)) {
                        if (item.sellIn <= 10) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn <= 5) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            } else {
                if (item.quality > 0) {
                    if (!IsSulfuras(item)) {
                        item.quality = item.quality - 1;
                    }
                }
            }

            if (IsSulfuras(item)) {
            } else {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (isAgedBrie(item)) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                } else {
                    if (isBackstagePass(item)) {
                        item.quality = 0;
                    } else {
                        if (item.quality > 0) {
                            if (IsSulfuras(item)) {
                                continue;
                            }
                            item.quality = item.quality - 1;
                        }
                    }
                }
            }
        }
    }

    private static boolean IsSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }
}
