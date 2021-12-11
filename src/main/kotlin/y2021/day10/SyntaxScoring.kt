package y2021.day10

import arrow.core.extensions.list.functorFilter.filter
import util.collections.Stack
import util.puzzle.AdventOfCodePuzzle

class SyntaxScoring(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {

        return input.map { toSyntaxError(it) }
            .filterIsInstance<CorruptedSyntaxError>()
            .sumOf { it.firstWrongCharacter.syntaxErrorScore }
    }


    override fun getAnswerForPartTwo(): String {
        val autocompleteScores = input.map { toSyntaxError(it) }
            .filterIsInstance<IncompleteSyntaxError>()
            .map { toAutoCompleted(it.stack) }
            .map { it.map { it.autoCompletePoints } }
            .map { toAutoCompleteScore(it) }

        val middleScore = autocompleteScores.sorted()[(autocompleteScores.size - 1) / 2]
        return "${middleScore}"
    }
}

fun toAutoCompleteScore(it: List<Int>) : Long = it.fold(0L) { acc, curr -> acc * 5 + curr }

fun toAutoCompleted(it: Stack<NavigationSubsystemCodepoints>) : List<NavigationSubsystemCodepoints> {
    val autoComplete = ArrayList<NavigationSubsystemCodepoints>()
    while (!it.isEmpty()) {
        autoComplete.add(it.pop().counterpart())
    }
    return autoComplete
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
/*
): 1 point.
]: 2 points.
}: 3 points.
>: 4 points.
 */
enum class NavigationSubsystemCodepoints(
    val character: Char,
    val isOpening: Boolean = true,
    val syntaxErrorScore: Int = 0,
    val autoCompletePoints: Int = 0) {

    OPENING_CURLY_BRACKET('{', true ),
    CLOSING_CURLY_BRACKET('}', false, 1197, 3),
    OPENING_PARENTHESE('(', true),
    CLOSING_PARENTHESE(')', false, 3,1),
    OPENING_SQUARE_BRACKET('[', true,),
    CLOSING_SQUARE_BRACKET(']', false, 57, 2),
    OPENING_ANGULAR_BRACKET('<', true),
    CLOSING_ANGULAR_BRACKET('>', false, 25137, 4);

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