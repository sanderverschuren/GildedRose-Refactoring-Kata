package com.gildedrose;

class GildedRose {
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
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
            return;
        }
        int qualityIncrement;
        switch (item.name) {
            case BACKSTAGE_PASS:
                if (isExpired(item)) {
                    qualityIncrement = -item.quality;
                } else if (expiresInDays(item, 5)) {
                    qualityIncrement = 3;
                } else if (expiresInDays(item, 10)) {
                    qualityIncrement = 2;
                } else {
                    qualityIncrement = 1;
                }
                break;
            case AGED_BRIE:
                if (isExpired(item)) {
                    qualityIncrement = 2;
                } else {
                    qualityIncrement = 1;
                }
                break;
            default:
                if (isExpired(item)) {
                    qualityIncrement = -2;
                } else {
                    qualityIncrement = -1;
                }
                break;
        }
        increaseQuality(item, qualityIncrement);
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
        return item.name.equals(SULFURAS);
    }

    private static int limitQualityRange(int value) {
        return Math.min(Math.max(value, MIN_QUALITY), MAX_QUALITY);
    }
}
