package y2021.day07

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class TreacheryOfWhalesTest {
    
    @Test
    fun examplePartOne() {
        val puzzle = TreacheryOfWhales(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(37))
    }

    @Test
    fun actualPartOne() {
        val puzzle = TreacheryOfWhales()
        assertThat(puzzle.solvePartOne(), equalTo(343605))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = TreacheryOfWhales(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(168))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = TreacheryOfWhales()
        assertThat(puzzle.solvePartTwo(), equalTo(96744904))
    }

}


private const val testInput = """16,1,2,0,4,2,7,1,2,14"""
