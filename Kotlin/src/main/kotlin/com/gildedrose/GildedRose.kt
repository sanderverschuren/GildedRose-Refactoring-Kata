package com.gildedrose

private const val AGED_BRIE = "Aged Brie"
private const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
private const val SULFURAS = "Sulfuras, Hand of Ragnaros"

private val Item.isExpired get() = sellIn < 0

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            updateSellIn(item)
            updateItemQuality(item)
        }
    }

    private fun updateSellIn(item: Item) {
        if (item.name == SULFURAS) return
        item.sellIn--
    }

    private fun updateItemQuality(item: Item) {
        when (item.name) {
            SULFURAS -> return
            AGED_BRIE -> updateAgedBrie(item)
            BACKSTAGE_PASSES -> updateBackstagePasses(item)
            else -> updateNormalItem(item)
        }
    }

    private fun updateAgedBrie(item: Item) {
        val increase = if (item.isExpired) 2 else 1
        item.quality = (item.quality + increase).coerceAtMost(50)
    }

    private fun updateBackstagePasses(item: Item) {
        item.quality = when {
            item.isExpired -> 0
            item.sellIn < 5 -> item.quality + 3
            item.sellIn < 10 -> item.quality + 2
            else -> item.quality + 1
        }.coerceAtMost(50)
    }

    private fun updateNormalItem(item: Item) {
        val degradation = if (item.isExpired) 2 else 1
        item.quality = (item.quality - degradation).coerceAtLeast(0)
    }

}
