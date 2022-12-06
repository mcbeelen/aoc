package y2022.day06

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class TuningTroubleTest {

    @Test
    fun examplePartOne() {
        assertThat(TuningTrouble("mjqjpqmgbljsphdztnvjfqwrcgsmlb").solvePartOne(), equalTo(7))
        assertThat(TuningTrouble("bvwbjplbgvbhsrlpgdmjqwftvncz").solvePartOne(), equalTo(5))
        assertThat(TuningTrouble("nppdvjthqldpwncqszvftbrmjlhg").solvePartOne(), equalTo(6))
        assertThat(TuningTrouble("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").solvePartOne(), equalTo(10))
        assertThat(TuningTrouble("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").solvePartOne(), equalTo(11))
    }

    @Test
    fun actualPartOne() {
        val puzzle = TuningTrouble()
        assertThat(puzzle.solvePartOne(), equalTo(1356))
    }


    @Test
    fun actualPartTwo() {
        val puzzle = TuningTrouble()
        assertThat(puzzle.solvePartTwo(), equalTo(2564))
    }

}


