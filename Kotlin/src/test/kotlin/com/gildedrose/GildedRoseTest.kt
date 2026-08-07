package com.gildedrose

import org.approvaltests.Approvals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class GildedRoseTest {

    @Test
    fun `Aged Brie with negative quality should be corrected to 0`() {
        val item = Item("Aged Brie", 3, -5)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        val expected = Item("Aged Brie", 2, 0)
        assertEquals(expected.toString(), item.toString())
    }

    @Test
    fun `Backstage passes with negative quality should be corrected to 0`() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 15, -5))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(0, items[0].quality)
    }

    @Test
    fun `Normal item with quality above 50 should be corrected to 50`() {
        val items = listOf(Item("+5 Dexterity Vest", 10, 55))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(50, items[0].quality)
    }

    @Test
    fun `Sulfuras quality is always 80`() {
        val items = listOf(Item("Sulfuras, Hand of Ragnaros", 0, 40))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(80, items[0].quality)
    }

    @Test
    fun thirtyDays() {
        val output = captureStdout {
            main(arrayOf("30"))
        }

        Approvals.verify(output)
    }

    private fun captureStdout(block: () -> Unit): String {
        val originalOut = System.out
        val buffer = ByteArrayOutputStream()
        System.setOut(PrintStream(buffer))
        try {
            block()
        } finally {
            System.setOut(originalOut)
        }
        return buffer.toString()
    }

}


