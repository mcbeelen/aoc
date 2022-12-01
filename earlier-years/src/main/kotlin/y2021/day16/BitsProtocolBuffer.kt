package y2021.day16

import util.collections.Queue
import java.lang.Integer.parseInt
import java.lang.Long.parseLong

class BitsProtocolBuffer(inputString: String) {

    val bits : Queue<Char> by lazy {
        val queue = Queue<Char>()
        queue.addAll(inputString.asIterable())
        return@lazy queue
    }
    fun hasBitsToParse() = bits.any { it == '1' }
    fun takeVersion() = takeAsLong(3)
    fun takeType() = takeAsInt(3)
    fun takeGroup() = takeAsString(5)
    fun takeLengthTypeID() = bits.dequeue()
    fun readLengthOfSubpackets() = takeAsInt(15)
    fun readNumberOfSubpackets() = takeAsInt(11)

    private fun takeAsLong(n: Int) = parseLong(takeAsString(n), 2)
    private fun takeAsInt(n: Int) = parseInt(takeAsString(n), 2)
    fun takeAsString(n: Int) = bits.dequeue(n).joinToString("")
}