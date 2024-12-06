package y2024.day06

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.lessThan
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class GuardGallivantTest {

    @Test
    fun examplePartOne() {
        val puzzle = GuardGallivant(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(41))
    }

    @Test
    fun actualPartOne() {
        val puzzle = GuardGallivant()
        assertThat(puzzle.solvePartOne(), equalTo(4515))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = GuardGallivant(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(6))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = GuardGallivant()
        assertThat(puzzle.solvePartTwo(), lessThan(4164))
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#..."""
