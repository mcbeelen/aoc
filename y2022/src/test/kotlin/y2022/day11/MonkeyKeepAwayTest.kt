package y2022.day11

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class MonkeyKeepAwayTest {

    @Test
    fun examplePartOne() {
        val puzzle = MonkeyKeepAway(testInput)
        assertThat(puzzle.getAnswerForPartOne(), equalTo("10605"))
    }

    @Test
    fun actualPartOne() {
        val puzzle = MonkeyKeepAway()
        assertThat(puzzle.getAnswerForPartOne(), equalTo("110888"))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = MonkeyKeepAway(testInput)
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("2713310158"))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = MonkeyKeepAway()
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("25590400731"))
    }

}


private const val testInput = """Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0

Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3

Monkey 3:
  Starting items: 74
  Operation: new = old + 3
  Test: divisible by 17
    If true: throw to monkey 0
    If false: throw to monkey 1"""
