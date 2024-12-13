package y2024.day12

import util.grid.ScreenCoordinate

class Region(val identifier: Char) {

    val plots : MutableSet<ScreenCoordinate> = mutableSetOf()

    fun addPlot(screenCoordinate: ScreenCoordinate) {
        this.plots.add(screenCoordinate)
    }

    fun addPlots(neighboursInSameRegion: List<ScreenCoordinate>) {
        this.plots.addAll(neighboursInSameRegion)
    }

    fun calculateArea() : Long {
        return plots.size.toLong()
    }

    fun calculatePerimeter() : Long {
        return plots
            .map { 4 - it.allNeighbors().count { plots.contains(it) } }
            .sum().toLong()
    }

    fun getPrice(): Long {
        val area = calculateArea()
        val perimeter = calculatePerimeter()
        return area * perimeter
    }


}