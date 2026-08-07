package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            when (item.name) {
                "Sulfuras, Hand of Ragnaros" -> {}
                "Aged Brie" -> updateAgedBrie(item)
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePasses(item)
                else -> updateNormalItem(item)
            }
        }
    }

    private fun updateAgedBrie(item: Item) {
        if (item.quality < 50) item.quality++
        item.sellIn--
        if (item.sellIn < 0 && item.quality < 50) item.quality++
    }

    private fun updateBackstagePasses(item: Item) {
        if (item.quality < 50) {
            item.quality++
            if (item.sellIn < 11 && item.quality < 50) item.quality++
            if (item.sellIn < 6 && item.quality < 50) item.quality++
        }
        item.sellIn--
        if (item.sellIn < 0) item.quality = 0
    }

    private fun updateNormalItem(item: Item) {
        if (item.quality > 0) item.quality--
        item.sellIn--
        if (item.sellIn < 0 && item.quality > 0) item.quality--
    }

}
