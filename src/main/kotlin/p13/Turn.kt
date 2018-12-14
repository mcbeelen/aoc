package p13

enum class Turn {
    LEFT,
    STRAIGHT,
    RIGHT;

    fun next(): Turn {
        return when (this) {

            LEFT -> STRAIGHT
            STRAIGHT -> RIGHT
            RIGHT -> LEFT
        }

    }
}