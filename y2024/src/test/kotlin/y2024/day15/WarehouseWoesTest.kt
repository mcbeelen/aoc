package y2024.day15

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class WarehouseWoesTest {

    @Test
    fun examplePartOne() {
        val puzzle = WarehouseWoes(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(0))
    }

    @Test
    @Ignore
    fun actualPartOne() {
        val puzzle = WarehouseWoes()
        assertThat(puzzle.solvePartOne(), equalTo(MIN_VALUE))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = WarehouseWoes(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(0))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = WarehouseWoes()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """PASTE_HERE"""
