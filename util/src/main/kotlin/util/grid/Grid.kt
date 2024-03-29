package util.grid


class Grid<T>(val margin : Int = 0) : HashMap<ScreenCoordinate, T>() {
    fun getWidth() = findMaxX(this.keys) + 1
    fun getHeight() = findMaxY(this.keys) + 1

    fun valuesOfNeighbors(key: ScreenCoordinate): List<T> {
        return allNeighbors(key).map { it.second }
    }

    fun allNeighbors(key: ScreenCoordinate) = key.allNeighbors()
        .filter { this.containsKey(it) }
        .map { Pair(it, this.getValue(it)) }

    fun bottomRight() : ScreenCoordinate = ScreenCoordinate(findMaxX(this.keys), findMaxY(this.keys))
}



fun <T> plotGrid(grid: Grid<T>, defaultCharForUnknown: Char = ' ', charFor: (T) -> Char) {
    val findMinY = findMinY(grid.keys) - grid.margin
    val findMaxY = findMaxY(grid.keys) + grid.margin
    val findMinX = findMinX(grid.keys) - grid.margin
    val findMaxX = findMaxX(grid.keys) + grid.margin

    for (y in findMinY..findMaxY) {
        print("${y.toString().padStart(8)}: ")
        for (x in findMinX..findMaxX) {
            val objectAtXY = grid[at(x, y)]

            if (objectAtXY == null) {
                print(defaultCharForUnknown)
            } else {
                print(charFor(objectAtXY))
            }
        }
        println()
    }

}

fun <T> parseToGrid(gridDefinition: String, fromSymbol: (Char) -> T) : Grid<T> {
    val lines = gridDefinition.trimIndent().lines()
    return parseToGrid(lines, fromSymbol)
}

fun <T> parseToGrid(lines: List<String>, fromSymbol: (Char) -> T): Grid<T> {
    val grid = Grid<T>()
    lines.forEachIndexed { topIndex, line ->
        line.trim().forEachIndexed { leftIndex, c ->
            val coordinate = at(leftIndex, topIndex)
            grid[coordinate] = fromSymbol(c)
        }
    }
    return grid
}