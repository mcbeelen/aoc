package y2022.day08

import util.grid.ScreenCoordinate
import util.grid.Vector

data class TallTree(
    val coordinate: ScreenCoordinate,
    val height: Int,
    val patchOfTallTrees: PatchOfTallTrees) {

    fun isVisible(): Boolean {
        return isVisibleFromAbove() || isVisibleFromTheRight() || isVisibleFromTheLeft() || isVisibleFromBelow()
    }


    fun getScenicScore(): Int {
        return numberOfTreesVisibleLookingUp() *
                numberOfTreesVisibleLookingRight() *
                numberOfTreesVisibleLookingDown() *
                numberOfTreesVisibleLookingLeft()
    }

    private fun numberOfTreesVisibleLookingDown() =
        countVisibleTreesInDirection(Vector(0, 1))

    private fun numberOfTreesVisibleLookingRight()=
        countVisibleTreesInDirection(Vector(1, 0))

    private fun numberOfTreesVisibleLookingUp() =
        countVisibleTreesInDirection(Vector(0, -1))

    private fun numberOfTreesVisibleLookingLeft() =
        countVisibleTreesInDirection(Vector(-1, 0))

    private fun countVisibleTreesInDirection(direction: Vector): Int {
        var visibleTrees = 0
        var nextCoordinateToCheck = coordinate.next(direction)

        while (patchOfTallTrees.containsKey(nextCoordinateToCheck)) {
            visibleTrees++
            val tree = patchOfTallTrees.getValue(nextCoordinateToCheck)
            if (tree.height >= height) {
                return visibleTrees
            }
            nextCoordinateToCheck = nextCoordinateToCheck.next(direction)
        }

        return visibleTrees
    }


    private fun isVisibleFromTheRight(): Boolean {
        if (coordinate.left == patchOfTallTrees.getWidth() - 1) {
            return true
        }
        return (coordinate.left + 1 until patchOfTallTrees.getWidth())
            .map {ScreenCoordinate(it, coordinate.top) }
            .all { patchOfTallTrees.getValue(it).height < height }

    }

    private fun isVisibleFromTheLeft(): Boolean {
        if (coordinate.left == 0) {
            return true
        }
        return (0 until coordinate.left)
            .map {ScreenCoordinate(it, coordinate.top) }
            .all { patchOfTallTrees.getValue(it).height < height }

    }

    private fun isVisibleFromAbove(): Boolean {
        if (coordinate.top == 0) {
            return true
        }
        return (0 until coordinate.top)
            .map {ScreenCoordinate(coordinate.left, it) }
            .all { patchOfTallTrees.getValue(it).height < height }
    }

    private fun isVisibleFromBelow(): Boolean {
        if (coordinate.top == patchOfTallTrees.getHeight() -1 ){
            return true
        }
        return (coordinate.top + 1  until patchOfTallTrees.getHeight())
            .map {ScreenCoordinate(coordinate.left, it) }
            .all { patchOfTallTrees.getValue(it).height < height }
    }


}

