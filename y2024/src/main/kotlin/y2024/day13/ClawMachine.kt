package y2024.day13

import org.apache.commons.math3.linear.*
import java.util.*


data class Button(val x: Int, val y: Int)


data class Location(val x: Int, val y: Int)

data class WayToWin(val timesA: Long, val timesB: Long) {
    fun calculateCost() = 3 * timesA + timesB
}

class ClawMachine(val buttonA: Button, val buttonB: Button, val price: Location ) {

    fun findCheapestWayToWin() : Optional<WayToWin> {
        val possibleWaysToWin = mutableSetOf<WayToWin>()
        val xTarget = price.x.toLong()
        val yTarget = price.y.toLong()

        for (a in 0L until  110L) {
            for (b in 0L until  110L) {
                if (buttonA.x * a + buttonB.x * b == xTarget &&
                    buttonA.y * a + buttonB.y * b == yTarget) {
                    possibleWaysToWin.add(WayToWin(a, b))
                }
            }
        }

        if (possibleWaysToWin.size == 0)
            return Optional.empty()

        return Optional.of(possibleWaysToWin.minByOrNull { it.calculateCost() }!!)
    }

    fun findSecondWayToWin() : Optional<WayToWin> {

        val xTarget = price.x + 10000000000000L
        val yTarget = price.y + 10000000000000L
        val coefficients: RealMatrix =
            Array2DRowRealMatrix(
                arrayOf<DoubleArray>(
                    doubleArrayOf(buttonA.x.toDouble(), buttonB.x.toDouble()),
                    doubleArrayOf(buttonA.y.toDouble(), buttonB.y.toDouble())
                ),
                false
            )
        val solver: DecompositionSolver = LUDecomposition(coefficients).getSolver()

        val constants: RealVector = ArrayRealVector(doubleArrayOf(xTarget.toDouble(), yTarget.toDouble()), false)
        val solution = solver.solve(constants)
        val timesA = solution.getEntry(0)
        val timesB = solution.getEntry(1)

        if (timesA.mod(1.0) <= 0.1 && timesB.mod(1.0) <= 0.1) {

            val longA = timesA.toLong()
            val longB = timesB.toLong()
            if (longA * buttonA.x + longB * buttonB.x == xTarget &&
                longA * buttonA.y + longB * buttonB.y == yTarget) {
                return Optional.of(WayToWin(longA, timesB.toLong()))

            } else {
                println("  Near miss")
            }

        }
        // 0.01 --> 113 machines

        return Optional.empty()
    }

    override fun toString(): String {
        return "ClawMachine(a=$buttonA, b=$buttonB, price=$price)"
    }
}