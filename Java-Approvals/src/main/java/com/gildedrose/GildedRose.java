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

    private static void updateSellIn(Item item) {
        int increment = switch (item.name) {
            case SULFURAS -> 0;
            default -> -1;
        };
        item.sellIn = item.sellIn + increment;
    }

    private static void updateQuality(Item item) {
        int qualityIncrement = switch (item.name) {
            case SULFURAS -> 0;
            case BACKSTAGE_PASS -> switch (item) {
                case Item i when isExpired(i) -> -item.quality;
                case Item i when expiresInDays(i, 5) -> 3;
                case Item i when expiresInDays(i, 10) -> 2;
                default -> 1;
            };
            case AGED_BRIE -> switch (item) {
                case Item i when isExpired(i) -> 2;
                default -> 1;
            };
            default -> switch (item) {
                case Item i when isExpired(i) -> -2;
                default -> -1;
            };
        };
        incrementQuality(item, qualityIncrement);
    }

    private static boolean expiresInDays(Item item, int days) {
        return item.sellIn < days;
    }

    private static boolean isExpired(Item item) {
        return expiresInDays(item, 0);
    }

    private static void incrementQuality(Item item, int number) {
        if (number == 0)
            return;
        item.quality = limitQualityRange(item.quality + number);
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private static int limitQualityRange(int value) {
        return Math.min(Math.max(value, MIN_QUALITY), MAX_QUALITY);
    }
}
