package y2024.day14

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import org.junit.Ignore
import org.junit.Test
import util.grid.ScreenCoordinate
import y2024.day14.Quadrant.*
import java.util.*
import kotlin.Int.Companion.MIN_VALUE

class RestroomRedoubtTest {

    @Test
    fun itShouldFindQuadrant() {
        val puzzle = RestroomRedoubt(testInput, 11, 7)
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(0, 2)), equalTo(Optional.of(TOP_LEFT)))
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(1, 3)), equalTo(Optional.empty()))
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(1, 6)), equalTo(Optional.of(BOTTOM_LEFT)))
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(2, 3)), equalTo(Optional.empty()))
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(3, 5)), equalTo(Optional.of(BOTTOM_LEFT)))
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(4, 5)), equalTo(Optional.of(BOTTOM_LEFT)))
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(5, 5)), equalTo(Optional.empty()))
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(6, 0)), equalTo(Optional.of(TOP_RIGHT)))
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(6, 6)), equalTo(Optional.of(BOTTOM_RIGHT)))
        assertThat(puzzle.determineQuadrant(ScreenCoordinate(9, 0)), equalTo(Optional.of(TOP_RIGHT)))
    }

    @Test
    fun examplePartOne() {
        val puzzle = RestroomRedoubt(testInput, 11, 7)
        assertThat(puzzle.solvePartOne(), equalTo(12))
    }

    @Test
    @Ignore
    fun actualPartOne() {
        val puzzle = RestroomRedoubt()
        assertThat(puzzle.solvePartOne(), equalTo(MIN_VALUE))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = RestroomRedoubt(testInput, 11, 7)
        assertThat(puzzle.solvePartTwo(), equalTo(555))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = RestroomRedoubt()
        assertThat(puzzle.solvePartTwo(), greaterThan(7036))
    }

}





private const val testInput = """p=0,4 v=3,-3
p=6,3 v=-1,-3
p=10,3 v=-1,2
p=2,0 v=2,-1
p=0,0 v=1,3
p=3,0 v=-2,-2
p=7,6 v=-1,-3
p=3,0 v=-1,-2
p=9,3 v=2,3
p=7,3 v=-1,2
p=2,4 v=2,-3
p=9,5 v=-3,-3"""
