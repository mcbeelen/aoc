package y2021.day13

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class TransparentOrigamiTest {

    @Test
    fun examplePartOne() {
        val puzzle = TransparentOrigami(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(17))
    }

    @Test
    fun actualPartOne() {
        val puzzle = TransparentOrigami()
        assertThat(puzzle.solvePartOne(), equalTo(775))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = TransparentOrigami()
        assertThat(puzzle.solvePartTwo(), equalTo(102))
    }
}


private const val testInput = """6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5"""
