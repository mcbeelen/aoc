package y2023.day02

import util.puzzle.AdventOfCodePuzzle


enum class Color {
    RED,
    GREEN,
    BLUE
}


data class RecordedGame(val gameId: Int, val red: Int = 0, val green: Int = 0, val blue: Int = 0) {
    fun updateCountOfColor(amount: Int, color: String): RecordedGame {
        when (color) {
            "red" -> if (amount > red) { return this.copy(red = amount) }
            "green" -> if (amount > green) { return this.copy(green = amount) }
            "blue" -> if (amount > blue) { return this.copy(blue = amount) }
        }
        return this
    }
}

class CubeConundrum(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    /**
     * Which games are possible with only 12 red cubes, 13 green cubes, and 14 blue cubes
     */
    override fun solvePartOne(): Int {
        return input
            .map { parseRecordedGame(it) }
            .filter { it.red <= 12 && it.green <= 13 && it.blue <= 14 }
            .map { it.gameId }
            .sum()
    }

    private fun parseRecordedGame(recordOfGame: String): RecordedGame {

        val gameId = recordOfGame.substringAfter("Game ").substringBefore(":").toInt()
        var game = RecordedGame(gameId)
        val setsOfCubes = recordOfGame.substringAfter(":").split(";")
        setsOfCubes.forEach { handOfShownCubes ->
            val cubesShownInSet = handOfShownCubes.trim().split(",")
            cubesShownInSet.forEach {
                val amount = it.trim().substringBefore(" ").toInt()
                val color = it.trim().substringAfter(" ")
                game = game.updateCountOfColor(amount, color)
            }
        }
        return game
    }

    override fun solvePartTwo(): Int {
        return input
            .map { parseRecordedGame(it) }
            .map { it.red * it.green * it.blue }
            .sum()
    }
}

fun main() {
    CubeConundrum().getAnswers()
}
