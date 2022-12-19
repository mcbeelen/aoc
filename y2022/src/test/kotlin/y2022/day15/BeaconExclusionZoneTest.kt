package y2022.day15

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import util.grid.ScreenCoordinate
import kotlin.Int.Companion.MIN_VALUE

class BeaconExclusionZoneTest {

    @Test
    fun examplePartOne() {
        val puzzle = BeaconExclusionZone(testInput, 10)
        assertThat(puzzle.solvePartOne(), equalTo(26))
    }

    @Test
    fun actualPartOne() {
        val puzzle = BeaconExclusionZone()
        assertThat(puzzle.solvePartOne(), equalTo(MIN_VALUE))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = BeaconExclusionZone(testInput, 10, 20)
       // assertThat(puzzle.singleNonExcluded(14, 11), equalTo(ScreenCoordinate(14, 10)))
       assertThat(puzzle.solvePartTwo(), equalTo(0))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = BeaconExclusionZone()

        //assertThat(puzzle.singleNonExcluded(2844194, 3677266), equalTo(ScreenCoordinate(14, 10)))
        assertThat(puzzle.solvePartTwo(), equalTo(0))

    }

}


private const val testInput = """Sensor at x=2, y=18: closest beacon is at x=-2, y=15
Sensor at x=9, y=16: closest beacon is at x=10, y=16
Sensor at x=13, y=2: closest beacon is at x=15, y=3
Sensor at x=12, y=14: closest beacon is at x=10, y=16
Sensor at x=10, y=20: closest beacon is at x=10, y=16
Sensor at x=14, y=17: closest beacon is at x=10, y=16
Sensor at x=8, y=7: closest beacon is at x=2, y=10
Sensor at x=2, y=0: closest beacon is at x=2, y=10
Sensor at x=0, y=11: closest beacon is at x=2, y=10
Sensor at x=20, y=14: closest beacon is at x=25, y=17
Sensor at x=17, y=20: closest beacon is at x=21, y=22
Sensor at x=16, y=7: closest beacon is at x=15, y=3
Sensor at x=14, y=3: closest beacon is at x=15, y=3
Sensor at x=20, y=1: closest beacon is at x=15, y=3"""
