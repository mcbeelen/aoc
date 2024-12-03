package y2024.day03

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import com.natpryce.hamkrest.lessThan
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class MullItOverTest {

    @Test
    fun regexTest() {
        assertTrue(REAL_MULTIPLICATION.matches("(2,4)%&"))
        assertFalse(REAL_MULTIPLICATION.matches("[3,7]!@^do_not_"))
        assertTrue(REAL_MULTIPLICATION.matches("(5,5)+"))
        assertFalse(REAL_MULTIPLICATION.matches("(32,64]then("))
        assertTrue(REAL_MULTIPLICATION.matches("(11,8)"))
        assertTrue(REAL_MULTIPLICATION.matches("(8,5))"))


    }

    @Test
    fun examplePartOne() {
        val puzzle = MullItOver(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(161))
    }

    @Test
    fun actualPartOne() {
        val puzzle = MullItOver()
        assertThat(puzzle.solvePartOne(), equalTo(167090022))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = MullItOver(testInputPartTwo)
        assertThat(puzzle.solvePartTwo(), equalTo(48))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = MullItOver()
        assertThat(puzzle.solvePartTwo(), greaterThan(38384677))
        assertThat(puzzle.solvePartTwo(), lessThan(93994894))
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"""
private const val testInputPartTwo = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"