package y2021.day02

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DiveTest {

    @Test
    fun examplePartOne() {
        val dive = Dive(testInput)
        assertThat(dive.solvePartOne(), equalTo("150"))
    }
    @Test
    fun examplePartTwo() {
        val dive = Dive(testInput)
        assertThat(dive.solvePartTwo(), equalTo("900"))
    }

}


private const val testInput = """forward 5
down 5
forward 8
up 3
down 8
forward 2"""
