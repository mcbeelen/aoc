package y2021.day17

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class TrickShotTest {

    @Test
    fun calculateMinimumVelocityToReach() {
        assertThat(calculateMinimumVelocityToReach(10), equalTo(4))
        assertThat(calculateMinimumVelocityToReach(11), equalTo(5))
    }

    @Test
    fun examplePartOne() {
        val puzzle = TrickShot(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(45))
    }

    @Test
    fun actualPartOne() {
        val puzzle = TrickShot()
        assertThat(puzzle.solvePartOne(), equalTo(25200))
    }

    @Test
    fun xPosAfterT() {
        assertThat(xPosAfterT(7, 1), equalTo(7))
        assertThat(xPosAfterT(7, 2), equalTo(13))
        assertThat(xPosAfterT(7, 3), equalTo(18))
        assertThat(xPosAfterT(7, 4), equalTo(22))
        assertThat(xPosAfterT(7, 5), equalTo(25))
        assertThat(xPosAfterT(7, 6), equalTo(27))
        assertThat(xPosAfterT(7, 7), equalTo(28))
        assertThat(xPosAfterT(7, 8), equalTo(28))
        assertThat(xPosAfterT(7, 9), equalTo(28))
    }

    @Test
    fun yPosAfterT() {
        assertThat(yPosAfterT(10, 1), equalTo(10))
        assertThat(yPosAfterT(10, 2), equalTo(19))
        assertThat(yPosAfterT(10, 3), equalTo(27))
        assertThat(yPosAfterT(10, 4), equalTo(34))
        assertThat(yPosAfterT(10, 5), equalTo(40))
        assertThat(yPosAfterT(10, 6), equalTo(45))
        assertThat(yPosAfterT(10, 7), equalTo(49))
        assertThat(yPosAfterT(10, 8), equalTo(52))
        assertThat(yPosAfterT(10, 9), equalTo(54))
        assertThat(yPosAfterT(10, 10), equalTo(55))
        assertThat(yPosAfterT(10, 11), equalTo(55))
        assertThat(yPosAfterT(10, 20), equalTo(10))
        assertThat(yPosAfterT(10, 21), equalTo(0))

    }

    @Test
    fun itHitsTarget() {
        val puzzle = TrickShot(testInput)
        assertTrue(puzzle.hitsTargetArea(Pair(6, 2)))
        assertTrue(puzzle.hitsTargetArea(Pair(6, 9)))
        assertTrue(puzzle.hitsTargetArea(Pair(23, -10)))
        assertTrue(puzzle.hitsTargetArea(Pair(27, -5)))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = TrickShot(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(112))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = TrickShot()
        assertThat(puzzle.solvePartTwo(), equalTo(3012))
    }

}


private const val testInput = """target area: x=20..30, y=-10..-5"""
