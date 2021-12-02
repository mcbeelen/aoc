package placeholderForPackage

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class PuzzleNameTest {

    @Test
    fun examplePartOne() {
        val puzzle = PuzzleName(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(""))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = PuzzleName(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(""))
    }
}


private const val testInput = """PASTE_HERE"""
