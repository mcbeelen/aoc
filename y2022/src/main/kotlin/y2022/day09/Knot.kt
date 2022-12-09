package y2022.day09

import com.google.common.eventbus.EventBus
import com.google.common.eventbus.Subscribe
import util.grid.ORIGIN
import util.grid.Vector

class Knot(
    private val eventBus: EventBus,
    private val id: KnotIdentifier,
    private val following: KnotIdentifier? = null) {

    var position = ORIGIN
    private val tracker = TrailTracker(ORIGIN)

    private var rope = Vector(0, 0)

    @Subscribe
    fun onKnotMovedEvent(event: KnotMovedEvent) {
        if (event.knot == following) {
            followKnot(event.vector)
        }
    }

    private fun followKnot(vector: Vector) {

        rope = rope.plus(vector)

        val followingMoving = responseOfTail[rope]
        followingMoving?.let {
            moveThisKnot(it)
            eventBus.post(KnotMovedEvent(id, it))
        }
    }

    private fun moveThisKnot(it: Vector) {
        position = position.next(it)
        rope = rope.minus(it)
        tracker.trackMovement(position)
    }

    fun numberOfVisitedPositions(): Int {
        return tracker.numberOfVisitedPositions()
    }


    private val responseOfTail = mapOf(
        Vector(-2, -2) to Vector(-1, -1),
        Vector(-2, -1) to Vector(-1, -1),
        Vector(-2, 0) to Vector(-1, 0),
        Vector(-2, 1) to Vector(-1, 1),
        Vector(-2, 2) to Vector(-1, 1),


        Vector(-1, -2) to Vector(-1, -1),
        Vector(-1, 2) to Vector(-1, 1),

        Vector(0, -2) to Vector(0, -1),
        Vector(0, 2) to Vector(0, 1),

        Vector(1, -2) to Vector(1, -1),
        Vector(1, 2) to Vector(1, 1),

        Vector(2, -2) to Vector(1, -1),
        Vector(2, -1) to Vector(1, -1),
        Vector(2, 0) to Vector(1, 0),
        Vector(2, 1) to Vector(1, 1),
        Vector(2, 2) to Vector(1, 1),
    )


    override fun toString(): String {
        return "Knot(${id} @ ${position} being pulled by ${rope}"
    }
}