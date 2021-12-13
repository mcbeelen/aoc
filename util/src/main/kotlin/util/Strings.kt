package util

fun countLetters(text: String): Map<Char, Int> {
    return text
            .groupingBy { it }
            .eachCount()
}

fun CharSequence.distinctChars() = this.asIterable().distinct()

fun String.withoutWhitespace() = replace("\\s*".toRegex(), "")


fun transpose(input: List<String>): List<String> {
    val width = input[0].trim().length
    val transposeMatrixBuilder = (0 until width).map { StringBuilder() }.toList()
    input.forEach {

        it.trim().forEachIndexed { index, c -> transposeMatrixBuilder[index].append(c) }
    }

    val transposedInput = transposeMatrixBuilder.map {
        it.toString()
    }
    return transposedInput
}
