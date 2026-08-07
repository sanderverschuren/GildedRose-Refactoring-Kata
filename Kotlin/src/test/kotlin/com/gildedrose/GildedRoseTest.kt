package com.gildedrose

import org.approvaltests.Approvals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class GildedRoseTest {

    @Test
    fun `Aged Brie with negative quality should be corrected to 0`() {
        val items = listOf(Item("Aged Brie", 3, -5))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(0, items[0].quality)
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


