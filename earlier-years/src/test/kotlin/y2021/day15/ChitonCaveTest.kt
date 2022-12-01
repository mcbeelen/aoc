package y2021.day15

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import com.natpryce.hamkrest.lessThan
import org.junit.Ignore
import org.junit.Test
import util.grid.at
import kotlin.Int.Companion.MIN_VALUE

class ChitonCaveTest {

    @Test
    fun examplePartOne() {
        val puzzle = ChitonCave(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(40))
    }

    @Test
    fun actualPartOne() {
        val puzzle = ChitonCave()
        assertThat(puzzle.solvePartOne(), equalTo(363))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = ChitonCave(testInput, true)
        assertThat(puzzle.enlargedGrid.getValue(at(0, 49)), equalTo(6))
        assertThat(puzzle.enlargedGrid.getValue(at(1, 49)), equalTo(7))
        assertThat(puzzle.enlargedGrid.getValue(at(49, 49)), equalTo(9))
        assertThat(puzzle.enlargedGrid.getValue(at(49, 0)), equalTo(6))

        assertThat(puzzle.solvePartTwo(), equalTo(315))
    }

    @Test
    @Ignore("+11seconds")
    fun actualPartTwo() {
        val puzzle = ChitonCave(shouldEnlarge = true)
        assertThat(puzzle.solvePartTwo(), greaterThan(363))
        assertThat(puzzle.solvePartTwo(), greaterThan(2787))
        assertThat(puzzle.solvePartTwo(), lessThan(2838))
        assertThat(puzzle.solvePartTwo(), equalTo(2835))
    }

}


private const val testInput = """1163751742
1381373672
2136511328
3694931569
7463417111
1319128137
1359912421
3125421639
1293138521
2311944581"""
