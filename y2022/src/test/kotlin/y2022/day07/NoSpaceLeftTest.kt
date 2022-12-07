package y2022.day07

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class NoSpaceLeftTest {

    @Test
    fun examplePartOne() {
        val puzzle = NoSpaceLeft(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(95437))
    }

    @Test
    fun actualPartOne() {
        val puzzle = NoSpaceLeft()
        assertThat(puzzle.solvePartOne(), equalTo(1390824))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = NoSpaceLeft(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(24933642))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = NoSpaceLeft()
        assertThat(puzzle.solvePartTwo(), equalTo(7490863))
    }

}


private const val testInput = """${'$'} cd /
${'$'} ls
dir a
14848514 b.txt
8504156 c.dat
dir d
${'$'} cd a
${'$'} ls
dir e
29116 f
2557 g
62596 h.lst
${'$'} cd e
${'$'} ls
584 i
${'$'} cd ..
${'$'} cd ..
${'$'} cd d
${'$'} ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k"""
