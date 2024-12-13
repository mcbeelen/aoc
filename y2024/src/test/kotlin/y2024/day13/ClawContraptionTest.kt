package y2024.day13

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import org.junit.Ignore
import org.junit.Test
import kotlin.Long.Companion.MIN_VALUE

class ClawContraptionTest {

    @Test
    fun examplePartOne() {
        val puzzle = ClawContraption(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(480L))
    }

    @Test
    fun actualPartOne() {
        val puzzle = ClawContraption()
        assertThat(puzzle.solvePartOne(), equalTo(36250))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = ClawContraption(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(875318608908L))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = ClawContraption()
        assertThat(puzzle.solvePartTwo(), greaterThan(64320834487968L))
    }

}


private const val testInput = """Button A: X+94, Y+34
Button B: X+22, Y+67
Prize: X=8400, Y=5400

Button A: X+26, Y+66
Button B: X+67, Y+21
Prize: X=12748, Y=12176

Button A: X+17, Y+86
Button B: X+84, Y+37
Prize: X=7870, Y=6450

Button A: X+69, Y+23
Button B: X+27, Y+71
Prize: X=18641, Y=10279"""
