package com.gildedrose

const val AGED_BRIE = "Aged Brie"
const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"

private val Item.isExpired get() = sellIn < 0

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            item.sellIn = calculateSellIn(item)
            item.quality = calculateQuality(item)
        }
    }

}

private fun calculateSellIn(item: Item): Int = when (item.name) {
    SULFURAS -> item.sellIn
    else -> item.sellIn - 1
}

private fun calculateQuality(item: Item): Int = when (item.name) {
    SULFURAS -> 80
    AGED_BRIE -> agedBrieQuality(item)
    BACKSTAGE_PASSES -> backstagePassesQuality(item)
    else -> normalItemQuality(item)
}

private fun agedBrieQuality(item: Item): Int {
    val increase = if (item.isExpired) 2 else 1
    return (item.quality + increase).coerceIn(0, 50)
}

private fun backstagePassesQuality(item: Item): Int = when {
    item.isExpired -> 0
    item.sellIn < 5 -> item.quality + 3
    item.sellIn < 10 -> item.quality + 2
    else -> item.quality + 1
}.coerceIn(0, 50)

private fun normalItemQuality(item: Item): Int {
    val degradation = if (item.isExpired) 2 else 1
    return (item.quality - degradation).coerceIn(0, 50)
}
