package com.gildedrose

import org.approvaltests.Approvals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class GildedRoseTest {

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


