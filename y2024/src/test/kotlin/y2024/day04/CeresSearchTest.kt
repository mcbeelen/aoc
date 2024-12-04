package y2024.day04

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class CeresSearchTest {

    @Test
    fun examplePartOne() {
        val puzzle = CeresSearch(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(18))
    }

    @Test
    fun actualPartOne() {
        val puzzle = CeresSearch()
        assertThat(puzzle.solvePartOne(), equalTo(2551))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = CeresSearch(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(9))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = CeresSearch()
        assertThat(puzzle.solvePartTwo(), equalTo(1985))
    }

}


private const val testInput = """MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX"""
