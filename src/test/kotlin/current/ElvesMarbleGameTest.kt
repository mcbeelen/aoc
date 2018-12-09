package current

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class ElvesMarbleGameTest {


    @Test
    fun winnerOfTheExampleGames() {

        assertThat(ElvesMarbleGame(9, 25).scoreOfWinningElf(), equalTo(32))

        assertThat(ElvesMarbleGame(10, 1618).scoreOfWinningElf(), equalTo(8317))

//        10 players; last marble is worth 1618 points: high score is 8317
//        13 players; last marble is worth 7999 points: high score is 146373
//        17 players; last marble is worth 1104 points: high score is 2764
//        21 players; last marble is worth 6111 points: high score is 54718
//        30 players; last marble is worth 5807 points: high score is 37305


    }
}