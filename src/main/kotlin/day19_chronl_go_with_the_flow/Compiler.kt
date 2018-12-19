package day19_chronl_go_with_the_flow

import day16_chronal_classification.Instruction
import day16_chronal_classification.OpCode


fun parseProgram(sourceCode: String): Program {
    val trimmedSourceCode = sourceCode.trimIndent()
    val instructionPointer = trimmedSourceCode.lines().first().substringAfter(" ").toInt()
    return Program(instructionPointer, compile(trimmedSourceCode.lines().drop(1)))
}

private fun compile(sourceCode: List<String>) =  sourceCode
            .map { parseSourceInstruction(it) }

fun parseSourceInstruction(lineOfSourceCode: String): Instruction {
    val opName = lineOfSourceCode.substringBefore(" ")
    val arguments = lineOfSourceCode.substringAfter(" ").split(" ").map { it.toInt() }.toIntArray()
    return Instruction(OpCode.valueOf(opName.toUpperCase()), arguments)
}
