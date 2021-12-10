package y2021.day10

import arrow.core.extensions.list.functorFilter.filter
import util.collections.Stack
import util.puzzle.AdventOfCodePuzzle

class SyntaxScoring(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {

        return input.map { toSyntaxError(it) }
            .filter { it is CorruptedSyntaxError }
            .map { it as CorruptedSyntaxError }
            .map { it.firstWrongCharacter.syntaxErrorScore }
            .sum()
    }


    override fun solvePartTwo(): Int {
        TODO("Solve me")
    }
}

fun toSyntaxError(line: String) : SyntaxError{
    val stack = Stack<NavigationSubsystemCodepoints>()
    line.forEach {
        val codepoint = NavigationSubsystemCodepoints[it]
        if (codepoint.isOpening) {
            stack.push(codepoint)
        } else {
            if (codepoint.counterpart() != stack.peek()) {
                return CorruptedSyntaxError(stack, codepoint)
            }
            stack.pop()
        }
    }

    return IncompleteSyntaxError(stack)
}


interface SyntaxError {
    val stack : Stack<NavigationSubsystemCodepoints>
}
data class CorruptedSyntaxError(override val stack : Stack<NavigationSubsystemCodepoints>, val firstWrongCharacter: NavigationSubsystemCodepoints) : SyntaxError
data class IncompleteSyntaxError(override val stack : Stack<NavigationSubsystemCodepoints>): SyntaxError



//{}: curly brackets) --> }: 1197 points.
//[]: square brackets --> ]: 57 points.
//(): parentheses --> ): 3 points.
//<>: angular brackets -->>: 25137 points.
enum class NavigationSubsystemCodepoints(
    val character: Char,
    val isOpening: Boolean = true,
    val syntaxErrorScore: Int) {

    OPENING_CURLY_BRACKET('{', true, 0 ),
    CLOSING_CURLY_BRACKET('}', false, 1197),
    OPENING_PARENTHESE('(', true, 0 ),
    CLOSING_PARENTHESE(')', false, 3),
    OPENING_SQUARE_BRACKET('[', true, 0 ),
    CLOSING_SQUARE_BRACKET(']', false, 57),
    OPENING_ANGULAR_BRACKET('<', true, 0 ),
    CLOSING_ANGULAR_BRACKET('>', false, 25137);

    fun counterpart() : NavigationSubsystemCodepoints {
        return when (this) {
            OPENING_CURLY_BRACKET -> CLOSING_CURLY_BRACKET
            CLOSING_CURLY_BRACKET -> OPENING_CURLY_BRACKET
            OPENING_PARENTHESE -> CLOSING_PARENTHESE
            CLOSING_PARENTHESE -> OPENING_PARENTHESE
            OPENING_SQUARE_BRACKET -> CLOSING_SQUARE_BRACKET
            CLOSING_SQUARE_BRACKET -> OPENING_SQUARE_BRACKET
            OPENING_ANGULAR_BRACKET -> CLOSING_ANGULAR_BRACKET
            CLOSING_ANGULAR_BRACKET -> OPENING_ANGULAR_BRACKET
        }
    }

    companion object {
        private val map = values().associateBy {it.character}
        operator fun get(value: Char) = map.getValue(value)
    }
}








fun main() {
    SyntaxScoring().getAnswers()
}