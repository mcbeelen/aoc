package y2024.day02

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class RedNosedReportTest {

    @Test
    fun examplePartOne() {
        val puzzle = RedNosedReport(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(2))
    }

    @Test
    fun actualPartOne() {
        val puzzle = RedNosedReport()
        assertThat(puzzle.solvePartOne(), equalTo(660))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = RedNosedReport(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(4))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = RedNosedReport()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9"""
