package y2022.day09

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class RopeBridgeTest {

    @Test
    fun examplePartOne() {
        val puzzle = RopeBridge(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(13))
    }

    @Test
    fun actualPartOne() {
        val puzzle = RopeBridge()
        assertThat(puzzle.solvePartOne(), equalTo(6522))
    }

    @Test
    fun examplePartTwo() {
        assertThat(RopeBridge(testInput).solvePartTwo(), equalTo(1))
        assertThat(RopeBridge(secondTestInput).solvePartTwo(), equalTo(36))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = RopeBridge()
        assertThat(puzzle.solvePartTwo(), equalTo(2717))
    }

}


private const val testInput = """R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2"""


private const val secondTestInput = """R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20"""