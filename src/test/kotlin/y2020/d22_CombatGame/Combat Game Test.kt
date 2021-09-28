package y2020.d22_CombatGame

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class `Combat Game Test` {

    @Test
    fun examplePartOne() {
        val game = CombatGame(testPlayerOne, testPlayerTwo)
        val score = game.play()
        assertThat(score, equalTo(306))
    }

    @Test
    fun partOne() {
        assertThat( CombatGame(deckOfPlayerOne, deckOfPlayerTwo).play(), equalTo(28886))
    }
}


const val testPlayerOne = """9
2
6
3
1"""

const val testPlayerTwo = """5
8
4
7
10"""
