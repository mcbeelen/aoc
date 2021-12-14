package y2021.day11

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class BioluminescentDumboOctopusTest {


    @Test
    fun simpleExampleWithCascade() {
        val dummyInput = """11111
19991
19191
19991
11111"""
        assertThat(BioluminescentDumboOctopus(dummyInput, 1).solvePartOne(), equalTo(9))
        assertThat(BioluminescentDumboOctopus(dummyInput, 2).solvePartOne(), equalTo(9))
    }

    @Test
    fun examplePartOne() {

        assertThat(BioluminescentDumboOctopus(testInput, 1).solvePartOne(), equalTo(0))
        assertThat(BioluminescentDumboOctopus(testInput, 2).solvePartOne(), equalTo(35))
        assertThat(BioluminescentDumboOctopus(testInput, 3).solvePartOne(), equalTo(80))
        assertThat(BioluminescentDumboOctopus(testInput, 4).solvePartOne(), equalTo(96))
        assertThat(BioluminescentDumboOctopus(testInput, 5).solvePartOne(), equalTo(104))
        assertThat(BioluminescentDumboOctopus(testInput, 6).solvePartOne(), equalTo(105))
        assertThat(BioluminescentDumboOctopus(testInput, 7).solvePartOne(), equalTo(112))
        assertThat(BioluminescentDumboOctopus(testInput, 8).solvePartOne(), equalTo(136))
        assertThat(BioluminescentDumboOctopus(testInput, 9).solvePartOne(), equalTo(175))
        assertThat(BioluminescentDumboOctopus(testInput, 10).solvePartOne(), equalTo(204))
        val puzzle = BioluminescentDumboOctopus(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(1656))
    }

    @Test
    fun actualPartOne() {
        val puzzle = BioluminescentDumboOctopus()
        assertThat(puzzle.solvePartOne(), equalTo(1683))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = BioluminescentDumboOctopus(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(195))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = BioluminescentDumboOctopus()
        assertThat(puzzle.solvePartTwo(), equalTo(788))
    }

}


private const val testInput = """5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526"""
