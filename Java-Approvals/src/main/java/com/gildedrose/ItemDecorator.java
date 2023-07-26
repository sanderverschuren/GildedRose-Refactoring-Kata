package com.gildedrose;

public class ItemDecorator {
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED = "Conjured Mana Cake";
    private final Item item;
    private final String name;
    private final int quality;
    private final int sellIn;

    public ItemDecorator(Item item) {
        this.item = item;
        this.name = item.name;
        this.sellIn = item.sellIn;
        this.quality = item.quality;
    }

    public Item getItem(){
        return item;
    }

    public ItemDecorator progressToNextDay(){
        return updateSellIn().updateQuality();
    }

    private ItemDecorator updateSellIn() {
        int decrement = switch (name) {
            case SULFURAS -> 0;
            default -> 1;
        };
        return new ItemDecorator(new Item(name, sellIn - decrement, quality));
    }

    private ItemDecorator updateQuality() {
        int qualityIncrement = switch (name) {
            case SULFURAS -> 0;
            case BACKSTAGE_PASS -> {
                if (isExpired()) yield -quality;
                else if (isExpiredInDays(5)) yield 3;
                else if (isExpiredInDays(10)) yield 2;
                yield 1;
            }
            case AGED_BRIE -> {
                if (isExpired()) yield 2;
                yield 1;
            }
            case CONJURED -> {
                if (isExpired()) yield -4;
                yield -2;
            }
            default -> {
                if (isExpired()) yield -2;
                yield -1;
            }
        };
        return new ItemDecorator(new Item(name, sellIn, calculateQuality(qualityIncrement)));
    }

    private boolean isExpiredInDays(int days) {
        return sellIn < days;
    }

    private boolean isExpired() {
        return isExpiredInDays(0);
    }

    private int calculateQuality(int increment) {
        if (increment == 0)
            return quality;
        return limitQualityRange(quality + increment);
    }

    private static int limitQualityRange(int value) {
        return Math.min(Math.max(value, MIN_QUALITY), MAX_QUALITY);
    }
}
