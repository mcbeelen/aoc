package y2021.day06

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class LanternfishTest {

    @Test
    fun examplePartOne() {
        val puzzle = Lanternfish(testInput)
        assertThat(puzzle.getAnswerForPartOne(), equalTo("5934"))
    }

    @Test
    fun actualPartOne() {
        val puzzle = Lanternfish()
        assertThat(puzzle.getAnswerForPartOne(), equalTo("372984"))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = Lanternfish(testInput)
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("26984457539"))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = Lanternfish()
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("1681503251694"))
    }
}


private const val testInput = """3,4,3,1,2"""
