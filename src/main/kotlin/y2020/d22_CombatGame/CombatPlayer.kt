package y2020.d22_CombatGame

import kotlin.collections.ArrayDeque


class CombatPlayer(private val startingDeck: String) {

    val cards : ArrayDeque<Int> by lazy {
        buildDeck()
    }

    private fun buildDeck() : ArrayDeque<Int> {
        val arrayDeque = ArrayDeque<Int>()
        arrayDeque.addAll(startingDeck.lines().map { it.toInt() }.toList())
        return arrayDeque
    }

    fun hasCards() = cards.isNotEmpty()

    fun drawCard() : Int = cards.removeFirst()

    fun keepCards(ownCard: Int, wonCard: Int) {
        cards.add(ownCard)
        cards.add(wonCard)
    }
}

fun main() {
    val scoreOfTheWinner = CombatGame(deckOfPlayerOne, deckOfPlayerTwo).play()
    println(scoreOfTheWinner)
}

const val deckOfPlayerOne = """10
21
37
2
47
13
6
29
9
3
4
48
46
25
44
41
23
20
24
12
45
43
5
27
50"""


const val deckOfPlayerTwo = """39
42
31
36
7
1
49
19
40
35
8
11
18
30
14
17
15
34
26
33
32
38
28
16
22"""
