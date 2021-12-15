package y2021.day14

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class ExtendedPolymerizationTest {

    @Test
    fun examplePartOne() {
        val puzzle = ExtendedPolymerization(testInput)
        assertThat(puzzle.getAnswerForPartOne(), equalTo("1588"))
    }

    @Test
    fun actualPartOne() {
        val puzzle = ExtendedPolymerization()
        assertThat(puzzle.getAnswerForPartOne(), equalTo("2712"))
    }

    @Test
    fun parseExamplePolymer() {
        val polymer = parsePolymer("NNCB")
        assertThat(polymer.elementPairCount.getValue("NN"), equalTo(1))
        assertThat(polymer.elementPairCount.getValue("NC"), equalTo(1))
        assertThat(polymer.elementPairCount.getValue("CB"), equalTo(1))

        assertThat(polymer.duplicateElementCount.getValue('N'), equalTo(1))
        assertThat(polymer.duplicateElementCount.getValue('C'), equalTo(1))
    }

    @Test
    fun parseActualPolymer() {
        val polymer = parsePolymer("SCVHKHVSHPVCNBKBPVHV")
        assertThat(polymer.elementPairCount.getValue("SC"), equalTo(1))
        assertThat(polymer.elementPairCount.getValue("HV"), equalTo(2))
        assertThat(polymer.elementPairCount.getValue("VH"), equalTo(2))

        assertThat(polymer.duplicateElementCount.getValue('S'), equalTo(1))
        assertThat(polymer.duplicateElementCount.getValue('H'), equalTo(4))
        assertThat(polymer.duplicateElementCount.getValue('V'), equalTo(4))
    }

    @Test
    fun parseExamplePairInsertionStep() {
        val insertionStep = parsePairInsertionStep("CH -> B")

        assertThat(insertionStep.inputElementPair, equalTo("CH"))
        assertThat(insertionStep.outputElementPairs, equalTo(listOf("CB", "BH")))
        assertThat(insertionStep.duplicatedElement, equalTo('B'))
    }


    @Test
    fun examplePartTwo() {
        val puzzle = ExtendedPolymerization(testInput)
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("2188189693529"))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = ExtendedPolymerization()
        assertThat(puzzle.getAnswerForPartTwo(), equalTo("8336623059567"))
    }

}


private const val testInput = """NNCB

CH -> B
HH -> N
CB -> H
NH -> C
HB -> C
HC -> B
HN -> C
NN -> C
BH -> H
NC -> B
NB -> B
BN -> B
BB -> N
BC -> B
CC -> N
CN -> C"""
