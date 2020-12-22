package y2020.d19_Monster_Images_Rules

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class `Monster Image Rules Test` {

    @Test
    fun examplePartOne() {
        assertThat(countImageThatMatchRuleZero(testRules, testImages), equalTo(2))
    }

    @Test
    fun `it should parse simple rule`() {
        val ruleSeven = "7: \"b\"".asRule()
        assertThat(ruleSeven.ruleNumber, equalTo(7))
        assertThat("12: \"a\"".asRule().ruleNumber, equalTo(12))

        assertTrue(ruleSeven.matches("b"))
        assertFalse(ruleSeven.matches("a"))
        assertFalse(ruleSeven.matches("bb"))
    }

    @Test
    fun `it should support compounded rules`() {
        val rule112 = "112: 7 12".asRule()

        assertTrue(rule112 is CompountRule)

        val rulesByNumber = parseRules(testRules)




    }


}

const val testRules = """0: 4 1 5
1: 2 3 | 3 2
2: 4 4 | 5 5
3: 4 5 | 5 4
4: "a"
5: "b""""


const val testImages = """ababbb
bababa
abbbab
aaabbb
aaaabbb"""





