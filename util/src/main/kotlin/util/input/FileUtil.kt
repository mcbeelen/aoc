package util.input

class FileUtils


fun <T> loadLines(origin: Any, fileName: String, converter: (String) -> T ): List<T> =
        loadLines("/" + origin.javaClass.getPackage().name.replace(".", "/") + "/" + fileName, converter)

fun loadLines(origin: Any, fileName: String): List<String> =
        loadLines(origin, fileName) {it}


fun loadLines(fileOnClasspath: String) = loadLines(fileOnClasspath) {it}

fun <T> loadLines(fileOnClasspath: String, converter: (String) -> T ): List<T> {
    val lineList = mutableListOf<T>()
    FileUtils::class.java.getResourceAsStream(fileOnClasspath)?.let {
        it.bufferedReader()
        .useLines { lines ->
            lines.forEach { line ->
                lineList.add(converter(line))
            }
        }
    }
    return lineList
}
