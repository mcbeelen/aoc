package y2022.day03

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class RucksackReorganizationTest {

    @Test
    fun splitIntoPairOFHalfString() {

        assertThat(calculatePriority(testInput.lines().get(0)), equalTo(16))
        assertThat(calculatePriority(testInput.lines().get(1)), equalTo(38))
        assertThat(calculatePriority(testInput.lines().get(2)), equalTo(42))
        assertThat(calculatePriority(testInput.lines().get(3)), equalTo(22))
        assertThat(calculatePriority(testInput.lines().get(4)), equalTo(20))
        assertThat(calculatePriority(testInput.lines().get(5)), equalTo(19))

    }



    @Test
    fun examplePartOne() {
        val puzzle = RucksackReorganization(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(157))
    }

    @Test
    fun actualPartOne() {
        val puzzle = RucksackReorganization()
        assertThat(puzzle.solvePartOne(), equalTo(7878))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = RucksackReorganization(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(70))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = RucksackReorganization()
        assertThat(puzzle.solvePartTwo(), equalTo(2760))
    }

}


private const val testInput = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"""
