package y2021.day10

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import org.junit.Ignore
import org.junit.Test
import util.collections.Stack
import y2021.day10.NavigationSubsystemCodepoints.*
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
    fun `it should properly auto complete`() {
        val stack = Stack<NavigationSubsystemCodepoints>()
        stack.push(OPENING_ANGULAR_BRACKET)
        stack.push(OPENING_CURLY_BRACKET)
        stack.push(OPENING_PARENTHESE)
        stack.push(OPENING_SQUARE_BRACKET)
        val toAutoCompleted = toAutoCompleted(stack)

        assertThat(toAutoCompleted, equalTo(listOf(
            CLOSING_SQUARE_BRACKET,
            CLOSING_PARENTHESE,
            CLOSING_CURLY_BRACKET,
            CLOSING_ANGULAR_BRACKET
        )))
    }

    @Test
    fun `it should properly calculate score`() {
        val points = listOf(2, 1, 3, 4)

        assertThat(toAutoCompleteScore(points), equalTo(294))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = SyntaxScoring(testInput)
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("288957"))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = SyntaxScoring()
        // greaterThen 173651156
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("3249889609"))
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
