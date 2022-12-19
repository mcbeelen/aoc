package y2022.day15

import util.grid.ORIGIN
import java.lang.Integer.parseInt
import java.lang.Math.abs
import util.grid.ScreenCoordinate
import util.puzzle.AdventOfCodePuzzle

class BeaconExclusionZone(testInput: String = "",
    private val y : Int = 2_000_000,
    private val range : Int = 4_000_000) : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {

        val excludedCoordinates = input.map { parseSensorReport(it) }
            .map { it.excludedPositions(y) }
            .fold(HashSet<Int>()) { R, T ->
                R.addAll(T)
                R
            }
        return excludedCoordinates.size
    }


    override fun solvePartTwo(): Int {
        val sensorReports = input.map { parseSensorReport(it) }

        val sortedSensors = sensorReports.sortedBy { it.sensor.top }
        sortedSensors.forEachIndexed { index, it ->
            val remainingSensors = sortedSensors.drop(index)
            remainingSensors.forEach { other ->
                val distanceBetweenSensors = it.sensor.distanceTo(other.sensor)
                val reachOfBothSensors = it.distanceTo() + other.distanceTo()
                if (distanceBetweenSensors - reachOfBothSensors in 1 until 3) {
                    println("Candidate for ridge: ${it.sensor} and ${other.sensor}" )
                    if (other.sensor.left > it.sensor.left) {
                        val i = other.sensor.distanceTo(ORIGIN) - other.distanceTo()
                        println("Formula for ridge is _LEFT_ + _TOP_ =${i}")
                    } else {
                        println("Formula for ridge is _TOP_ - _LEFT_ = ${other.distanceTo()}")
                    }
                }
            }
        }

/*
With the actual input:

Candidate for ridge: <3553843, 2822363> and <2939101, 3624676>
Formula for ridge is _TOP_ - _LEFT_ = 833072
Candidate for ridge: <3166958, 2890076> and <3758241, 3546895>
Formula for ridge is _LEFT_ + _TOP_ =6521461


Manually tried to resolve with:
val LEFT = 2844194
val TOP = 833072 + LEFT
TOP - LEFT == 833072
TOP + LEFT  == 6521461
TOP == 833072 + LEFT

833072 + 2*LEFT == 6521461
2*LEFT == 6521461 - 833072
(6521461 - 833072) / 2

LEFT
TOP

This did NOT yield a solution

 */
        return Int.MAX_VALUE
    }



    private fun parseSensorReport(sensorReportLine: String) : SensorReport {
        // Sensor at x=2, y=18: closest beacon is at x=-2, y=15
        val sensorInfo = sensorReportLine.substringBefore(":")
        val sensorX = sensorInfo.substringAfter("x=").substringBefore(",").asInt()
        val sensorY = sensorInfo.substringAfter("y=").asInt()

        val beaconInfo = sensorReportLine.substringAfter(":")
        val beaconX = beaconInfo.substringAfter("x=").substringBefore(",").asInt()
        val beaconY = beaconInfo.substringAfter("y=").asInt()

        return SensorReport(ScreenCoordinate(sensorX, sensorY), ScreenCoordinate(beaconX, beaconY))

    }

    fun singleNonExcluded(left: Int, top: Int): ScreenCoordinate {
        val screenCoordinates = (left - 10 until left + 5).flatMap { left ->
            (top - 10 until top + 5).map { top -> ScreenCoordinate(left, top) }
        }
        val sensorReports = input.map { parseSensorReport(it) }

        return screenCoordinates.single {
            sensorReports.none { report -> report.excludes(it) }
        }
    }

}

private fun String.asInt() = parseInt(this)

data class SensorReport(
    val sensor: ScreenCoordinate,
    val beacon: ScreenCoordinate) {

    fun distanceTo() = beacon.distanceTo(sensor)

    fun excludes(coordinate: ScreenCoordinate) : Boolean {
        return sensor.distanceTo(coordinate) <= distanceTo()
    }

    fun excludedPositions(y: Int) : Set<Int> {
        val offset = abs(sensor.top - y)
        if (offset <= distanceTo()) {
            val expansion = distanceTo() - offset
            val toSet = (Math.negateExact(expansion)..expansion)
                .map { sensor.left + it }
                .toMutableSet()

            if (beacon.top == y) {
                toSet.remove(beacon.left)
            }

            return toSet
        } else {
            return emptySet()
        }


    }
}

fun main() {
    BeaconExclusionZone().getAnswers()
}