package y2021.day02

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import util.input.parseInput
import kotlin.Int.Companion.MIN_VALUE

class DiveTest {

    @Test
    fun examplePartOne() {
        assertThat(solveIt(testInput), equalTo(150))
    }


}


private const val testInput = """forward 5
down 5
forward 8
up 3
down 8
forward 2"""
