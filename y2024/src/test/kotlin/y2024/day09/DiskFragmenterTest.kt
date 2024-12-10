package y2024.day09

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class DiskFragmenterTest {

    @Test
    fun parseDiskMap() {
        val disk = Disk("12345")
        assertThat(disk.toString(), equalTo("0..111....22222"))
        disk.defrag()
        assertThat(disk.toString(), equalTo("022111222......"))
    }

    @Test
    fun examplePartOne() {
        val sampleDisk = Disk("2333133121414131402")
        sampleDisk.defrag()
        assertThat(sampleDisk.toString(), equalTo("0099811188827773336446555566.............."))


        val puzzle = DiskFragmenter(testInput)
        assertThat(puzzle.getAnswerForPartOne(), equalTo( "1928"))
    }

    @Test
    fun actualPartOne() {
        val puzzle = DiskFragmenter()
        assertThat(puzzle.getAnswerForPartOne(), equalTo( "6385338159127"))
    }

    @Test
    fun examplePartTwo() {
        val sampleDisk = Disk("2333133121414131402")
        assertThat(sampleDisk.toString(), equalTo("00...111...2...333.44.5555.6666.777.888899"))
        //sampleDisk.defragByFiles()
        //assertThat(sampleDisk.toString(), equalTo("00992111777.44.333....5555.6666.....8888.."))

    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = DiskFragmenter()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """2333133121414131402"""
