package y2022.day04

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class CampCleanupTest {

    @Test
    fun examplePartOne() {
        val puzzle = CampCleanup(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(2))
    }

    @Test
    fun actualPartOne() {
        val puzzle = CampCleanup()
        assertThat(puzzle.solvePartOne(), equalTo(MIN_VALUE))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = CampCleanup(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(4))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = CampCleanup()
        assertThat(puzzle.solvePartTwo(), equalTo(815))
    }

}


private const val testInput = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"""
