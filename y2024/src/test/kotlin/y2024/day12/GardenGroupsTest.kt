package y2024.day12

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class GardenGroupsTest {

    @Test
    fun examplePartOne() {
        assertThat(GardenGroups(firstExampleForPartOne).solvePartOne(), equalTo(140L))
    }

    @Test
    fun secondExampleForPartOne() {
        assertThat(GardenGroups(secondExampleForPartOne).solvePartOne(), equalTo(1930L))
    }

    @Test
    fun actualPartOne() {
        val puzzle = GardenGroups()
        assertThat(puzzle.solvePartOne(), equalTo(MIN_VALUE))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = GardenGroups(firstExampleForPartOne)
        assertThat(puzzle.solvePartTwo(), equalTo(0))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = GardenGroups()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val firstExampleForPartOne = """AAAA
BBCD
BBCC
EEEC"""


private const val secondExampleForPartOne = """RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE"""