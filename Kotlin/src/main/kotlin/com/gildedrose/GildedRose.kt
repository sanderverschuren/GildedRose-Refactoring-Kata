package com.gildedrose

const val AGED_BRIE = "Aged Brie"
const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"
const val CONJURED = "Conjured Mana Cake"

private const val MIN_QUALITY = 0
private const val MAX_QUALITY = 50
private const val LEGENDARY_QUALITY = 80

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            updaterFor(item).update(item)
        }
    }
}

private fun updaterFor(item: Item): ItemUpdater = when (item.name) {
    SULFURAS -> LegendaryItemUpdater
    AGED_BRIE -> AgedBrieUpdater
    BACKSTAGE_PASSES -> BackstagePassUpdater
    CONJURED -> ConjuredItemUpdater
    else -> NormalItemUpdater
}

private interface ItemUpdater {
    fun update(item: Item)
}

private object LegendaryItemUpdater : ItemUpdater {
    override fun update(item: Item) {
        item.quality = LEGENDARY_QUALITY
    }
}

private object AgedBrieUpdater : ItemUpdater {
    override fun update(item: Item) {
        item.sellIn--
        val increase = if (item.expired) 2 else 1
        item.quality = (item.quality + increase).coerceIn(MIN_QUALITY, MAX_QUALITY)
    }
}

private object BackstagePassUpdater : ItemUpdater {
    override fun update(item: Item) {
        item.sellIn--
        item.quality = when {
            item.expired -> 0
            item.sellIn < 5 -> item.quality + 3
            item.sellIn < 10 -> item.quality + 2
            else -> item.quality + 1
        }.coerceIn(MIN_QUALITY, MAX_QUALITY)
    }
}

private object ConjuredItemUpdater : ItemUpdater {
    override fun update(item: Item) {
        item.sellIn--
        val degradation = if (item.expired) 4 else 2
        item.quality = (item.quality - degradation).coerceIn(MIN_QUALITY, MAX_QUALITY)
    }
}

private object NormalItemUpdater : ItemUpdater {
    override fun update(item: Item) {
        item.sellIn--
        val degradation = if (item.expired) 2 else 1
        item.quality = (item.quality - degradation).coerceIn(MIN_QUALITY, MAX_QUALITY)
    }
}

private val Item.expired get() = sellIn < 0
