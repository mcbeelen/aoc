package y2022.day11

import java.lang.IllegalArgumentException
import java.util.ArrayDeque
import java.util.Queue

/***
 * Monkey 0:
 *   Starting items: 54, 89, 94
 *   Operation: new = old * 7
 *   Test: divisible by 17
 *     If true: throw to monkey 5
 *     If false: throw to monkey 3
 */
class Monkey(
    val id: MonkeyIndex,
    startingItems: List<WorryLevel>,
    val operation: Operation,
    val value: Long?,
    val divisible: Int,
    val destinations: Pair<MonkeyIndex, MonkeyIndex>,
    val applyRelief: Boolean
) {


    public var numberOfInspections = 0L

    private val items: Queue<WorryLevel> = ArrayDeque()

    init {
        items.addAll(startingItems)
    }

    fun hasItems() = items.isNotEmpty()
    fun getThrowItem(): ThrownItem {

        numberOfInspections++

        val oldWorryLevel = items.poll()

        var newWorryLevel = applyOperation(oldWorryLevel)
        if (applyRelief) {
            newWorryLevel = newWorryLevel / 3
        }
        val destination = determineDestination(newWorryLevel)

        return ThrownItem(newWorryLevel, destination)

    }

    private fun determineDestination(newWorryLevel: WorryLevel): MonkeyIndex {
        val destination = if (newWorryLevel % divisible == 0L) {
            destinations.first
        } else {
            destinations.second
        }
        return destination
    }

    private fun applyOperation(oldWorryLevel: WorryLevel): WorryLevel {
        val secondHandValue = if (value != null) value else oldWorryLevel
        return when (operation) {
            Operation.PLUS -> oldWorryLevel + secondHandValue
            Operation.TIMES -> oldWorryLevel * secondHandValue
        }
    }

    fun catchItem(item: WorryLevel) {
        items.offer(item)
    }


    override fun toString(): String {
        return "${id} + ${numberOfInspections}"
    }
}

enum class Operation {
    PLUS,
    TIMES;

    companion object {
        fun by(it: String): Operation {
            return when(it) {
                "+" -> PLUS
                "*" -> TIMES
                else -> throw IllegalArgumentException()
            }
        }
    }
}