package y2021.day12

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class PassagePathingTest {

    @Test
    fun examplePartOne() {
        val puzzle = PassagePathing(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(10))
    }

    @Test
    fun actualPartOne() {
        val puzzle = PassagePathing()
        assertThat(puzzle.solvePartOne(), equalTo(4573))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = PassagePathing(testInput, 1)
        assertThat(puzzle.solvePartTwo(), equalTo(36))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = PassagePathing(maximumNumberOfVisitsToSmallCave = 1)
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """start-A
start-b
A-c
A-b
b-d
A-end
b-end"""
