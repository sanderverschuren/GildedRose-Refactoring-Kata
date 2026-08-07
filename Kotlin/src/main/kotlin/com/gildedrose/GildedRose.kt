package com.gildedrose

const val AGED_BRIE = "Aged Brie"
const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        items.forEach { updateItemSellInAndQuality(it) }
    }

    private fun updateItemSellInAndQuality(item: Item): Item {
        item.sellIn = calculateSellIn(item)
        item.quality = calculateQuality(item)
        return item
    }

    private fun calculateSellIn(item: Item): Int = when (item.name) {
        SULFURAS -> item.sellIn
        else -> item.sellIn - 1
    }

    private fun calculateQuality(item: Item): Int = when (item.name) {
        SULFURAS -> 80
        AGED_BRIE -> calculateAgedBrieQuality(item)
        BACKSTAGE_PASSES -> calculateBackstagePassesQuality(item)
        else -> calculateNormalItemQuality(item)
    }

    private fun calculateAgedBrieQuality(item: Item): Int {
        val increase = if (item.isExpired) 2 else 1
        return (item.quality + increase).coerceIn(0, 50)
    }

    private fun calculateBackstagePassesQuality(item: Item): Int = when {
        item.isExpired -> 0
        item.sellIn < 5 -> item.quality + 3
        item.sellIn < 10 -> item.quality + 2
        else -> item.quality + 1
    }.coerceIn(0, 50)

    private fun calculateNormalItemQuality(item: Item): Int {
        val degradation = if (item.isExpired) 2 else 1
        return (item.quality - degradation).coerceIn(0, 50)
    }

    private val Item.isExpired get() = sellIn < 0

}
