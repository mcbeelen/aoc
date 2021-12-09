package y2021.day09

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class SmokeBasinTest {

    @Test
    fun examplePartOne() {
        val puzzle = SmokeBasin(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(15))
    }

    @Test
    fun actualPartOne() {
        val puzzle = SmokeBasin()
        assertThat(puzzle.solvePartOne(), equalTo(575))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = SmokeBasin(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(0))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = SmokeBasin()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """2199943210
3987894921
9856789892
8767896789
9899965678"""
