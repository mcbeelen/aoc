package y2020.d19_Monster_Images_Rules

import java.lang.IllegalArgumentException

typealias RuleNumber = String

fun countImageThatMatchRuleZero(rules: String, images: String): Int {
    return 2
}


interface RuleForValidImage {
    val ruleNumber: RuleNumber
    fun matches(imageMessage: String) : Boolean
}

data class SimpleRule(override val ruleNumber: RuleNumber, val validPartial: String) : RuleForValidImage {
    override fun matches(imageMessage: String) = validPartial == imageMessage
}

class CompountRule(override val ruleNumber: RuleNumber, val subRules: List<RuleNumber>) : RuleForValidImage {
    override fun matches(imageMessage: String): Boolean {
        TODO("Not yet implemented")
    }
}
fun String.asRule(): RuleForValidImage {
    val ruleNumber = this.substringBefore(":")
    return when {
        this.contains('"') -> SimpleRule(ruleNumber, this.substringAfter('"').substringBefore('"'))
        else -> throw IllegalArgumentException()
    }
}


fun parseRules(ruleDefinition: String) = ruleDefinition.lines()
    .map { it.asRule() }
    .associateBy { it.ruleNumber }


fun main() {
    val rulesByNumber = parseRules(y2020d19_rules).toMutableMap()
    while (rulesByNumber.any { it.value is CompountRule }) {
        rulesByNumber
            .filter { it.value is CompountRule }

    }

}
