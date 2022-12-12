package y2022.day11

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

import org.junit.Assert.*

class MonkeyTest {

    @Test
    fun getThrowItem() {

        val monkey = Monkey(0, listOf(79, 98), Operation.TIMES, 19, 23, Pair(2, 3), true)
        val (worryLevel, destination) = monkey.getThrowItem()
        assertThat(worryLevel, equalTo(500))
        assertThat(destination, equalTo(3))
    }
}