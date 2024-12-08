package y2024.day07

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class BridgeRepairTest {


    @Test
    fun singleValue() {
        assertTrue(equationCanBeSolved(4L, listOf(4L)))
        assertFalse(equationCanBeSolved(1L, listOf(4L)))

        // Negative aren't solvable
        assertFalse(equationCanBeSolved(-1L, listOf()))
    }

    @Test
    fun solvableByAddition() {
        assertTrue(equationCanBeSolved(11L, listOf(5L, 6L)))
        assertFalse(equationCanBeSolved(10L, listOf(5L, 6L)))
    }

    @Test
    fun solvableByMultiplication() {
        assertTrue(equationCanBeSolved(12L, listOf(2L, 6L)))
        assertFalse(equationCanBeSolved(12L, listOf(3L, 6L)))
    }

    @Test
    fun validateEntryFromTestInput() {
        val puzzle = BridgeRepair("3267: 81 40 27")
        assertThat(puzzle.getAnswerForPartOne(), equalTo("3267"))
    }

    @Test
    fun examplePartOne() {
        val puzzle = BridgeRepair(testInput)
        assertThat(puzzle.getAnswerForPartOne(), equalTo("3749"))
    }

    @Test
    fun actualPartOne() {
        val puzzle = BridgeRepair()
        assertThat(puzzle.getAnswerForPartOne(), equalTo("42283209483350"))
    }

    @Test
    fun simpleConcatenation() {
        assertThat(BridgeRepair("156: 15 6").getAnswerForPartTwo(), equalTo("156"))

        assertThat(BridgeRepair("7290: 6 8 6 15").getAnswerForPartTwo(), equalTo("7290")) {
            "Fails due to concatenation 8+6 instead if 48+6: Left-to-right"
        }
    }

    @Test
    fun examplePartTwo() {
        val puzzle = BridgeRepair(testInput)
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("11387"))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = BridgeRepair()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20"""
