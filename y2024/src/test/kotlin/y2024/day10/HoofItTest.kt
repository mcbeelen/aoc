package y2024.day10

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test


class HoofItTest {

    @Test
    fun firstExample() {
        val simplePuzzle = HoofIt("""...0...
...1...
...2...
6543456
7.....7
8.....8
9.....9""")

        assertThat(simplePuzzle.solvePartOne(), equalTo(2))

    }

    @Test
    fun secondExample() {
        val simplePuzzle = HoofIt("""..90..9
...1.98
...2..7
6543456
765.987
876....
987....""")

        assertThat(simplePuzzle.solvePartOne(), equalTo(4))

    }

    @Test
    fun thirdExample() {
        val thirdPuzzle = HoofIt("""10..9..
2...8..
3...7..
4567654
...8..3
...9..2
.....01""")
        assertThat(thirdPuzzle.solvePartOne(), equalTo(3))

    }

    @Test
    fun examplePartOne() {
        val puzzle = HoofIt(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(36))
    }

    @Test
    fun actualPartOne() {
        val puzzle = HoofIt()
        assertThat(puzzle.solvePartOne(), equalTo(744))
    }

}


private const val testInput = """89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732"""
