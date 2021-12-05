package y2021.day05

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class HydrothermalVentureTest {

    @Test
    fun examplePartOne() {
        val puzzle = HydrothermalVenture(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(5))
    }

    @Test
    fun actualPartOne() {
        val puzzle = HydrothermalVenture()
        assertThat(puzzle.solvePartOne(), equalTo(7142))
    }


    @Test
    fun examplePartTwo() {
        val puzzle = HydrothermalVenture(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(12))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = HydrothermalVenture()
        assertThat(puzzle.solvePartTwo(), equalTo(20012))
    }
}


private const val testInput = """0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2"""
