package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void endDay() {
        items = Arrays.stream(items)
            .map(GildedRose::updateSellIn)
            .map(GildedRose::updateQuality)
            .toArray(Item[]::new);
    }

    private static Item updateSellIn(Item item) {
        int decrement = switch (item.name) {
            case SULFURAS -> 0;
            default -> 1;
        };
        return new Item(item.name, item.sellIn - decrement, item.quality);
    }

    private static Item updateQuality(Item item) {
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
            case CONJURED -> switch (item) {
                case Item i when isExpired(i) -> -4;
                default -> -2;
            };
            default -> switch (item) {
                case Item i when isExpired(i) -> -2;
                default -> -1;
            };
        };
        return new Item(item.name, item.sellIn, calculateQuality(item, qualityIncrement));
    }

    private static boolean expiresInDays(Item item, int days) {
        return item.sellIn < days;
    }

    private static boolean isExpired(Item item) {
        return expiresInDays(item, 0);
    }

    private static int calculateQuality(Item item, int increment) {
        if (increment == 0)
            return item.quality;
        return limitQualityRange(item.quality + increment);
    }

    private static int limitQualityRange(int value) {
        return Math.min(Math.max(value, MIN_QUALITY), MAX_QUALITY);
    }
}
