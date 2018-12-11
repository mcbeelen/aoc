package day11_chronal_charge_power_grid

import util.grid.ScreenCoordinate
import kotlin.system.measureTimeMillis

class PowerGrid(private val gridSerialNumber: Int) {


    private val fuelCellsInGrid : Map<ScreenCoordinate, FuelCell> by lazy {
        calculatePowerLevelsForGrid()
    }


    fun powerLevelAt(screenCoordinate: ScreenCoordinate): Int = fuelCellsInGrid[screenCoordinate]!!.powerLevel


    fun findTopLevelCornerOfMostPowerfulSquare(): ScreenCoordinate {
        val squares : MutableMap<ScreenCoordinate, Int> = HashMap()

        for (x in 0 .. 298) {
            for (y in 0 .. 298) {
                squares[ScreenCoordinate(x, y)] = sumPowerLevels(x, y)
            }
        }

        return squares.maxBy { it.value }!!.component1()


    }

    private fun sumPowerLevels(x: Int, y: Int): Int {

        var sum = 0

        for (xOffset in 0 .. 2) {
            for (yOffset in 0 .. 2) {

                sum += fuelCellsInGrid[ScreenCoordinate(x + xOffset, y + yOffset)]!!.powerLevel

            }
        }

        return sum

    }

    private fun calculatePowerLevelsForGrid() : Map<ScreenCoordinate, FuelCell> {
        val grid : MutableMap<ScreenCoordinate, FuelCell> = HashMap()

        for (x in 0 .. 300) {
            for (y in 0 .. 300) {
                grid[ScreenCoordinate(x, y)] = FuelCell(gridSerialNumber, x, y)
            }
        }

        return grid
    }

}


class ChronalChargeSolver {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val time = measureTimeMillis {
                val grid = PowerGrid(8561)
                val findTopLevelCornerOfMostPowerfulSquare = grid.findTopLevelCornerOfMostPowerfulSquare()
                println("Most powerfull square is located at $findTopLevelCornerOfMostPowerfulSquare")


            }
            println("Solved part one in ${time}ms")


        }

    }
}





