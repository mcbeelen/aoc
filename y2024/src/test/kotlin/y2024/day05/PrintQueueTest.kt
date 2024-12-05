package y2024.day05

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class PrintQueueTest {

    @Test
    fun examplePartOne() {
        val puzzle = PrintQueue(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(143))
    }

    @Test
    fun actualPartOne() {
        val puzzle = PrintQueue()
        assertThat(puzzle.solvePartOne(), equalTo(5391))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = PrintQueue(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(123))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = PrintQueue()
        assertThat(puzzle.solvePartTwo(), equalTo(6142))
    }

}


private const val testInput = """47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47
"""
