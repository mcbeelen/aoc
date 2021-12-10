package y2021.day10

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class SyntaxScoringTest {

    @Test
    fun examplePartOne() {
        val puzzle = SyntaxScoring(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(26397))
    }

    @Test
    fun actualPartOne() {
        val puzzle = SyntaxScoring()
        assertThat(puzzle.solvePartOne(), equalTo(370407))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = SyntaxScoring(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(0))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = SyntaxScoring()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]"""
