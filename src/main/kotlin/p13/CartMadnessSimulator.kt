package p13

import p13.Section.*
import util.grid.Direction
import util.grid.Direction.*
import util.grid.ScreenCoordinate
import java.util.concurrent.atomic.AtomicInteger

class CartMadnessSimulator(input: String) {

    private var carts : Set<Cart> = HashSet()
    lateinit var track: Map<ScreenCoordinate, Section>

    val counter = AtomicInteger(0)


    init {

        val trackInProgress: MutableMap<ScreenCoordinate, Section> = HashMap();

        val initialCarts : MutableSet<Cart> = HashSet()

        input.trimIndent().lines().withIndex()
                .forEach { line ->
                    val y = line.index
                    line.value.withIndex()
                            .forEach {
                                val x = it.index

                                when (it.value) {
                                    '^' -> {
                                        val cart = Cart(ScreenCoordinate(x, y), UP)
                                        initialCarts.add(cart)
                                        trackInProgress[ScreenCoordinate(x, y)] = STRAIGHT_UP_DOWN
                                    }
                                    '>' -> {
                                        val cart = Cart(ScreenCoordinate(x, y), RIGHT)
                                        initialCarts.add(cart)
                                        trackInProgress[ScreenCoordinate(x, y)] = STRAIGHT_LEFT_RIGHT
                                    }
                                    'v' -> {
                                        val cart = Cart(ScreenCoordinate(x, y), DOWN)
                                        initialCarts.add(cart)
                                        trackInProgress[ScreenCoordinate(x, y)] = STRAIGHT_UP_DOWN
                                    }
                                    '<' -> {
                                        val cart = Cart(ScreenCoordinate(x, y), RIGHT)
                                        initialCarts.add(cart)
                                        trackInProgress[ScreenCoordinate(x, y)] = STRAIGHT_LEFT_RIGHT
                                    }
                                    '|' -> {
                                        trackInProgress[ScreenCoordinate(x, y)] = STRAIGHT_UP_DOWN
                                    }
                                    '-' -> {
                                        trackInProgress[ScreenCoordinate(x, y)] = STRAIGHT_LEFT_RIGHT
                                    }
                                    '/' -> {
                                        trackInProgress[ScreenCoordinate(x, y)] = CURVE_UP_RIGHT
                                    }
                                    '\\' -> {
                                        trackInProgress[ScreenCoordinate(x, y)] = CURVE_UP_LEFT
                                    }

                                    '+' -> {
                                        trackInProgress[ScreenCoordinate(x, y)] = INTERSECTION
                                    }
                                    ' ' -> {
                                    }


                                    else -> {
                                        println("Unsupported char")
                                    }
                                }


                            }

                    this.track = trackInProgress
                    this.carts = initialCarts.toSortedSet(compareBy( { it.position.top }, {it.position.left}))
                }
    }


    fun tick() : ScreenCoordinate? {

        println("Tick() # ${counter.incrementAndGet()}")

        val cartsAtNextPosition : MutableSet<Cart> = HashSet()
        carts
                .sortedWith(compareBy({ it.position.top }, { it.position.left }))
                .forEach {
                    val newPositionForMovingCart = it.move(track)
                    if (isCartPresentAt(newPositionForMovingCart.position)
                            || cartsAtNextPosition.any { it.position == newPositionForMovingCart.position }) {
                        return newPositionForMovingCart.position
                    } else {
                        cartsAtNextPosition.add(newPositionForMovingCart)
                    }
                }
        this.carts = cartsAtNextPosition.toSortedSet(compareBy( { it.position.top }, {it.position.left}))
        return null
    }



    fun findLocationOfFirstCrash(): ScreenCoordinate {

        var coordinate = tick()
        while (coordinate == null) {
            coordinate = tick()
        }
        return coordinate!!
    }

    fun isCartPresentAt(screenCoordinate: ScreenCoordinate) = carts.any { it.position == screenCoordinate }

}


data class Cart(val position: ScreenCoordinate, val direction: Direction, val turn: Turn = Turn.LEFT) : Comparable<Cart> {
    override fun compareTo(other: Cart): Int {
        return position.compareTo(other.position)
    }


    fun move(track: Map<ScreenCoordinate, Section>) : Cart {

        val nextPosition = position.next(direction)

        val section = track[nextPosition]
        return when(section) {

            INTERSECTION -> copy(position = nextPosition, direction = direction.turn(turn), turn = turn.next())
            CURVE_UP_RIGHT -> copy(position = nextPosition, direction = followTheUpRightCurve(direction))
            CURVE_UP_LEFT -> copy(position = nextPosition, direction = followTheUpLeftCurve(direction))
            STRAIGHT_UP_DOWN -> copy(position = nextPosition)
            STRAIGHT_LEFT_RIGHT -> copy(position = nextPosition)
            null -> {throw IllegalStateException("We should not go off the track")}
        }

    }


    private fun followTheUpLeftCurve(direction: Direction): Direction {
        return when (direction) {
            UP -> LEFT
            RIGHT -> DOWN
            DOWN -> RIGHT
            LEFT -> UP
        }

    }

    private fun followTheUpRightCurve(direction: Direction): Direction {
        return when (direction) {
            UP -> RIGHT
            RIGHT -> UP
            DOWN -> LEFT
            LEFT -> DOWN
        }

    }
}




enum class Section {
    INTERSECTION,
    CURVE_UP_RIGHT,
    CURVE_UP_LEFT,
    STRAIGHT_UP_DOWN,
    STRAIGHT_LEFT_RIGHT
}