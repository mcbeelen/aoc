package y2021.day24

import util.collections.Stack
import util.puzzle.AdventOfCodePuzzle

class ArithmeticLogicUnitPuzzle(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {
        println(input[0])
        TODO("Solve me")
    }

    override fun solvePartTwo(): Int {
        TODO("Solve me")
    }

    fun quickValidModelNumber(candidate: String) : Boolean {

        candidate.takeLast(6)
        val digits = candidate.map { it.digitToInt() }
        val firstSeven = Stack<Int>()
        digits.take(7).forEach {
            firstSeven.push(it)
        }
        val lastSeven = digits.drop(7)


        return false

    }

    fun isValidModelNumber(candidate: String) : Boolean {

        val digits = candidate.map { it.digitToInt() }

        var w = 0
        var x = 0
        var y = 0
        var z : Int = 0

        // first digit
        val a = digits[0]

        y = w
        y += 7
        z = a + 7

        // second digit
        val b = digits[1]
        w = b
        x = 1
        y = 26
        z = (a + 7) * 26
        y = b + 8
        z += y

        val c = digits[2]
        x = z % 26
        x += 13
        x =1
        z *= 26
        y = c + 2
        z += y

        val d = digits[3]
        w = d
        z *= 26
        y = d + 11
        z += y


        // from 75
        val e = digits[4]
        w = e
        x = z
        z %= 26
        x -= 3

        x =1
        y = 26
        z *= 26
        y = e + 6
        z += y


        // from 93
        val f = digits[5]
        z *= 26
        y = f + 12
        z += y

        // from 111
        val g = digits[6]
        z *= 26
        y = g + 14
        z += y

        // from 129
        val h = digits[7]
        z *= 26
        y = h + 13
        z += y

        // from 147
        val i = digits[8]
        z *= 26
        y = h + 15
        z += y

        // from 165
        val j = digits[9]
        x = z % 26
        z = z / 26
        x -= 8
        if (x == j) {
            x = 1
        } else {
            x = 0
        }
        y = x * 25
        y = y + 1
        z *= y
        y = j + 10
        y *= x
        z += y


        // from 183
        val k = digits[10]
        x = z % 26
        z = z / 26
        x -= 12
        if (x == j) {
            x = 1
        } else {
            x = 0
        }
        y = x * 25
        y = y + 1
        z *= y
        y = k + 6
        y *= x
        z += y

        // from 201
        val l = digits[11]
        x = z % 26
        z = z / 26
        x -= 7
        if (x == j) {
            x = 1
        } else {
            x = 0
        }
        y = x * 25
        y = y + 1
        z *= y
        y = l + 10
        y *= x
        z += y
        /*
inp w
mul x 0
add x z
mod x 26
div z 26
add x -7
eql x w
eql x 0
mul y 0
add y 25
mul y x
add y 1
mul z y
mul y 0
add y w
add y 10
mul y x
add z y


        */
        return z == y
    }

}

fun main() {
    (1 ..9).forEach {
        println("${it} --> ${(14 % 26) -16} == it")
    }

//    ArithmeticLogicUnit().getAnswers()
}