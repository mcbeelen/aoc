package y2021.day15

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
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
        assertThat(puzzle.solvePartTwo(), equalTo(315))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = ChitonCave()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
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
