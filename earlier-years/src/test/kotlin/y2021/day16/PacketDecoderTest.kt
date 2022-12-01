package y2021.day16

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class PacketDecoderTest {

    @Test
    fun decodeType4Packet() {
        val example = "D2FE28"

        assertThat(toBits(example), equalTo("110100101111111000101000"))
        val packets = decode(example)
        val literalValuePacket = packets[0] as LiteralValuePacket
        assertLiteralValue(literalValuePacket, 6, 4, 2021)
    }

    @Test
    fun shortOperatorPacket() {
        val example = "38006F45291200"
        val packets = decode(example)
        val operatorPacket = packets[0] as OperatorPacket

        assertOperatorPacket(operatorPacket, 1, 6,
            LiteralValuePacket(6, 4, 10),
            LiteralValuePacket(2, 4, 20))
    }

    @Test
    fun operatorPacketWithThreesubpackets() {
        val example = "EE00D40C823060"
        val packets = decode(example)
        val operatorPacket = packets[0] as OperatorPacket

        assertOperatorPacket(operatorPacket, 7, 3,
            LiteralValuePacket(2, 4, 1),
            LiteralValuePacket(4, 4, 2),
            LiteralValuePacket(1, 4, 3),
        )


    }

    private fun assertLiteralValue(
        packet: LiteralValuePacket,
        version: Long,
        type: Int,
        value: Long
    ) {
        assertThat(packet.version, equalTo(version))
        assertThat(packet.type, equalTo(type))
        assertThat(packet.value, equalTo(value))
    }

    private fun assertOperatorPacket(
        packet: OperatorPacket,
        version: Long,
        type: Int,
        vararg packets: Packet
    ) {
        assertThat(packet.version, equalTo(version))
        assertThat(packet.type, equalTo(type))
        packet.subpackets.forEachIndexed { index, packet ->
            val subpacket = packets[index] as LiteralValuePacket
            assertLiteralValue(packet as LiteralValuePacket, subpacket.version, subpacket.type, subpacket.value)
        }
    }


    @Test
    fun examplePartOne() {
        assertThat(PacketDecoder("8A004A801A8002F478").getAnswerForPartOne().toInt(), equalTo(16))
        assertThat(PacketDecoder("620080001611562C8802118E34").getAnswerForPartOne().toInt(), equalTo(12))
        assertThat(PacketDecoder("C0015000016115A2E0802F182340").getAnswerForPartOne().toInt(), equalTo(23))
        assertThat(PacketDecoder("A0016C880162017C3686B18A3D4780").getAnswerForPartOne().toInt(), equalTo(31))
    }

    @Test
    fun actualPartOne() {
        val puzzle = PacketDecoder()
        assertThat(puzzle.getAnswerForPartOne().toInt(), equalTo(945))
    }

    @Test
    fun examplePartTwo() {
        assertThat(PacketDecoder("C200B40A82").getAnswerForPartTwo(), equalTo("3"))
        assertThat(PacketDecoder("04005AC33890").getAnswerForPartTwo(), equalTo("54"))
        assertThat(PacketDecoder("880086C3E88112").getAnswerForPartTwo(), equalTo("7"))
        assertThat(PacketDecoder("CE00C43D881120").getAnswerForPartTwo(), equalTo("9"))
        assertThat(PacketDecoder("D8005AC2A8F0").getAnswerForPartTwo(), equalTo("1"))
        assertThat(PacketDecoder("F600BC2D8F").getAnswerForPartTwo(), equalTo("0"))
        assertThat(PacketDecoder("9C005AC2F8F0").getAnswerForPartTwo(), equalTo("0"))
        assertThat(PacketDecoder("9C0141080250320F1802104A08").getAnswerForPartTwo(), equalTo("1"))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = PacketDecoder()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """PASTE_HERE"""
