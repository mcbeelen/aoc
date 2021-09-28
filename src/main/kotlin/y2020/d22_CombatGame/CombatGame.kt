package y2020.d22_CombatGame

class CombatGame(deckOfPlayerOne: String, deckOfPlayerTwo: String) {

    private val playerOne = CombatPlayer(deckOfPlayerOne)
    private val playerTwo = CombatPlayer(deckOfPlayerTwo)

    fun play(): Int {

        while (playerOne.hasCards() && playerTwo.hasCards()) {
            playRound()
        }
        val winner = wonBy(playerOne, playerTwo)
        return calculateScore(winner)
    }

    private fun playRound() {
        val cardFromPlayerOne = playerOne.drawCard()
        val cardFromPlayerTwo = playerTwo.drawCard()

        if (cardFromPlayerOne > cardFromPlayerTwo) {
            playerOne.keepCards(cardFromPlayerOne, cardFromPlayerTwo)
        } else {
            playerTwo.keepCards(cardFromPlayerTwo, cardFromPlayerOne)
        }
    }

    private fun calculateScore(winner: CombatPlayer): Int {
        var score = 0;
        while (winner.hasCards()) {
            score += winner.cards.size * winner.drawCard()
        }
        return score
    }

    private fun wonBy(vararg players: CombatPlayer): CombatPlayer = players.first { it.hasCards() }

}


fun main() {

}
