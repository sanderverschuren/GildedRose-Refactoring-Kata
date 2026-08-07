package com.gildedrose

import org.approvaltests.Approvals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class GildedRoseTest {

    @Test
    fun `Aged Brie with negative quality should be corrected to 0`() {
        val agedBrie = Item(AGED_BRIE, 3, -5)
        val app = GildedRose(listOf(agedBrie))

        app.updateQuality()

        assertItemsEqual(agedBrie, Item(AGED_BRIE, 2, 0))
    }

    @Test
    fun `Backstage passes with negative quality should be corrected to 0`() {
        val item = Item(BACKSTAGE_PASSES, 15, -5)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertItemsEqual(item, Item(BACKSTAGE_PASSES, 14, 0))
    }

    @Test
    fun `Normal item with quality above 50 should be corrected to 50`() {
        val item = Item("normal item", 10, 55)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertItemsEqual(item, Item("normal item", 9, 50))
    }

    @Test
    fun `Sulfuras quality is always 80`() {
        val item = Item(SULFURAS, 0, 40)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertItemsEqual(item, Item(SULFURAS, 0, 80))
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

    private fun assertItemsEqual(actual: Item, expected:Item) = assertEquals(expected.toString(), actual.toString())

}


