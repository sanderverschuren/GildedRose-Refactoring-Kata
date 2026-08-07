package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            if (item.name == "Sulfuras, Hand of Ragnaros") continue

            when (item.name) {
                "Aged Brie" -> {
                    if (item.quality < 50) item.quality++
                    item.sellIn--
                    if (item.sellIn < 0 && item.quality < 50) item.quality++
                }

                "Backstage passes to a TAFKAL80ETC concert" -> {
                    if (item.quality < 50) {
                        item.quality++
                        if (item.sellIn < 11 && item.quality < 50) item.quality++
                        if (item.sellIn < 6 && item.quality < 50) item.quality++
                    }
                    item.sellIn--
                    if (item.sellIn < 0) item.quality = 0
                }

                else -> {
                    if (item.quality > 0) item.quality--
                    item.sellIn--
                    if (item.sellIn < 0 && item.quality > 0) item.quality--
                }
            }
        }
    }

}
