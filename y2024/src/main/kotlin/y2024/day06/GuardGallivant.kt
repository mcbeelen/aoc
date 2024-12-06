package y2024.day06


import util.grid.*
import util.grid.Turn.RIGHT
import util.puzzle.AdventOfCodePuzzle

class GuardGallivant(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val mapOfTheLab: Grid<Char> by lazy { parseToGrid(input) { it } }
    private val startingPositionOfTheGuard = mapOfTheLab.entries.single { it.value == '^' }.key

    override fun solvePartOne(): Int {

        mapOfTheLab[startingPositionOfTheGuard] = '.'
        val visitedPositions = findAllPositionGuardWillVisit(startingPositionOfTheGuard).toMutableSet()
        visitedPositions.add(startingPositionOfTheGuard)

        return visitedPositions.size
    }

    private fun isObstacleDirectInFrontOfGuard(guard: GridWalker, map: Grid<Char> ) =
        map.getValue(guard.getNextPosition()) != '.'


    override fun solvePartTwo(): Int {

        mapOfTheLab[startingPositionOfTheGuard] = '.'

        val findPotenialPositionsForObstacles = findAllPositionGuardWillVisit(startingPositionOfTheGuard)
            .minus(startingPositionOfTheGuard)
        println("Going to scan ${findPotenialPositionsForObstacles.size} positions")

        return findPotenialPositionsForObstacles
            .count { causesGuardToWalkInLoop(it) }
    }

    private fun causesGuardToWalkInLoop(potentialPositionForObstacle: ScreenCoordinate): Boolean {
        val modifiedMap = mapOfTheLab.clone()
        modifiedMap[potentialPositionForObstacle] = 'X'

        return doesGuardWalkInALoop(modifiedMap)
    }

    private fun findAllPositionGuardWillVisit(startingPositionOfTheGuard: ScreenCoordinate): Set<ScreenCoordinate> {
        val visitedPositions = mutableSetOf<ScreenCoordinate>()

        val guard = GridWalker(startingPositionOfTheGuard)
        while (mapOfTheLab.contains(guard.getNextPosition())) {
            if (isObstacleDirectInFrontOfGuard(guard, mapOfTheLab)) {
                guard.turn(RIGHT)
            } else {
                guard.move {
                    visitedPositions.add(it)
                }
            }
        }
        return visitedPositions
    }

    private fun doesGuardWalkInALoop(modifiedMap: Grid<Char>): Boolean {
        val guard = GridWalker(startingPositionOfTheGuard)
        val previousSituations = mutableSetOf<GridWalker>()
        previousSituations.add(guard)
        var loopDetected = false


        while (!loopDetected && modifiedMap.contains(guard.getNextPosition()) ) {
            if (isObstacleDirectInFrontOfGuard(guard, modifiedMap)) {
                guard.turn(RIGHT)
            } else {
                guard.move()
            }
            if (previousSituations.contains(guard)) {
                loopDetected = true
            } else {
                previousSituations.add(guard)
            }
        }

        return loopDetected
    }
}

fun main() {
    GuardGallivant().getAnswers()
}