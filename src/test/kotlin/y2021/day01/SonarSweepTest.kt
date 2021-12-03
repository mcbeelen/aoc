package y2021.day01

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class SonarSweepTest {

    @Test
    fun examplePartOne() {
        assertThat(solveSonarSweep(testInput), equalTo(7))
    }

    @Test
    fun examplePartTwo() {
        assertThat(solveSlidingSonarSweep(testInput), equalTo(5))
    }
}


private const val testInput = """199
200
208
210
200
207
240
269
260
263"""
