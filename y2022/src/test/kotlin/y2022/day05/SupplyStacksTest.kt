package y2022.day05

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import java.util.*
import kotlin.Int.Companion.MIN_VALUE

class SupplyStacksTest {

    @Test
    fun isShouldParseInitialStacking() {
        val puzzle = SupplyStacks(testInput)

        val stack : Stack<Crate> = puzzle.stacks.getValue(1)
        assertThat(stack.size, equalTo(2))
        assertThat(stack.pop(), equalTo('N'))
        assertThat(stack.pop(), equalTo('Z'))

        assertThat(puzzle.moveInstructions[0], equalTo(MoveInstruction(1, 2, 1)))
        assertThat(puzzle.moveInstructions[1], equalTo(MoveInstruction(3, 1, 3)))

    }

    @Test
    fun itShouldParseTheMoveInstructions() {
        val puzzle = SupplyStacks(testInput)
        assertThat(puzzle.moveInstructions[0], equalTo(MoveInstruction(1, 2, 1)))
        assertThat(puzzle.moveInstructions[1], equalTo(MoveInstruction(3, 1, 3)))
    }

    @Test
    fun itShouldStackIntoCMZ() {
        val puzzle = SupplyStacks(testInput)
        assertThat(puzzle.getAnswerForPartOne(), equalTo("CMZ"))
    }

    @Test
    fun actualPartOne() {
        val puzzle = SupplyStacks()
        assertThat(puzzle.getAnswerForPartOne(), equalTo("ZRLJGSCTR"))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = SupplyStacks(testInput)
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("MCD"))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = SupplyStacks()
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("PRTTGRFPB"))
    }

}


private const val testInput = """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2"""
