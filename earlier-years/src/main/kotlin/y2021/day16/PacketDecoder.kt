package y2021.day16

import util.puzzle.AdventOfCodePuzzle
import java.lang.Long.MIN_VALUE
import java.lang.Long.parseLong

interface Packet {
    fun getSumOfVersionNumbers() : Long
    fun calculateValue() : Long

    val version: Long
    val type: Int
}

class OperatorPacket(
    override val version: Long,
    override val type: Int,
    val subpackets: List<Packet>) :Packet {
    override fun getSumOfVersionNumbers() = version + subpackets.sumOf { it.getSumOfVersionNumbers() }

    override fun calculateValue() : Long {

        return when (type) {
            0 -> subpackets.sumOf { it.calculateValue() }
            1 -> subpackets.fold(1L) {acc, packet -> acc * packet.calculateValue() }
            2 -> subpackets.minOf { it.calculateValue() }
            3 -> subpackets.maxOf { it.calculateValue() }
            5 -> if (subpackets.get(0).calculateValue() > subpackets.get(1).calculateValue()) { 1 } else { 0 }
            6 -> if (subpackets.get(0).calculateValue() < subpackets.get(1).calculateValue()) { 1 } else { 0 }
            7 -> if (subpackets.get(0).calculateValue() == subpackets.get(1).calculateValue()) { 1 } else { 0 }

            else -> MIN_VALUE
        }
    }

}

class LiteralValuePacket(
    override val version: Long,
    override val type: Int,
    val value: Long) :Packet {
    override fun getSumOfVersionNumbers() = version
    override fun calculateValue() = value
}

class PacketDecoder(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun getAnswerForPartOne(): String {
        val decodedPacket = decode(input.first())
        val sumOf = decodedPacket.sumOf { it.getSumOfVersionNumbers() }
        return "${sumOf}"
    }

    override fun getAnswerForPartTwo(): String {
        val decodedPacket = decode(input.first())
        val value = decodedPacket.sumOf { it.calculateValue() }
        return "${value}"
    }
}
internal fun decode(hexadecimal: String):  List<Packet> {
    val bits = toBits(hexadecimal)
    return decodeBits(BitsProtocolBuffer(bits)) {buffer, _ -> buffer.hasBitsToParse() }
}

internal fun decodeBits(buffer: BitsProtocolBuffer, predicate: (buffer : BitsProtocolBuffer, parsedPacket: List<Packet>) -> Boolean) : List<Packet> {

    val packets = ArrayList<Packet>().toMutableList()

    while (predicate.invoke(buffer, packets)) {
        val version = buffer.takeVersion()
        val type = buffer.takeType()

        if (type == 4) {
            val value = decodeLiteralValue(buffer)
            packets.add(LiteralValuePacket(version, type, value))
        } else {
            val lengthTypeID = buffer.takeLengthTypeID()
            if (lengthTypeID == '0') {
                val length = buffer.readLengthOfSubpackets()
                val subpacketsBits = buffer.takeAsString(length)
                val subpackets = decodeBits(BitsProtocolBuffer(subpacketsBits)) {buffer, _ -> buffer.hasBitsToParse() }
                packets.add(OperatorPacket(version, type, subpackets))
            } else {
                val numberOfSubpackets = buffer.readNumberOfSubpackets()

                val subpackets = decodeBits(buffer) {_, parsedPackets -> parsedPackets.size < numberOfSubpackets }
                packets.add(OperatorPacket(version, type, subpackets))
            }
        }
    }
    return packets
}


private fun decodeLiteralValue(buffer: BitsProtocolBuffer): Long {
    val valueBuilder = StringBuilder()
    var lastGroup: Boolean
    do {
        val group = buffer.takeGroup()
        lastGroup = group.first() == '0'
        valueBuilder.append(group.drop(1))

    } while (!lastGroup)

    return parseLong(valueBuilder.toString(), 2)
}

fun toBits(hexadecimal: String) : String{
    val sb = StringBuilder()
    hexadecimal.forEach {
        sb.append(toBitSet(it))
    }
    return sb.toString()
}

fun toBitSet(char: Char) : String {
    return when(char) {
         '0'    -> "0000";
         '1'    -> "0001";
         '2'    -> "0010";
         '3'    -> "0011";
         '4'    -> "0100";
         '5'    -> "0101";
         '6'    -> "0110";
         '7'    -> "0111";
         '8'    -> "1000";
         '9'    -> "1001";
         'A'    -> "1010";
         'B'    -> "1011";
         'C'    -> "1100";
         'D'    -> "1101";
         'E'    -> "1110";
         'F'    -> "1111";
        else -> {throw IllegalArgumentException("No valid hexadecimal character '${char}'")}
    }
}



fun main() {
    PacketDecoder().getAnswers()
}