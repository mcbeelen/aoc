package y2019.day08

import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import y2019.day08.PixelColor.BLACK
import y2019.day08.PixelColor.TRANSPARENT
import y2019.day08.PixelColor.WHITE

enum class PixelColor(val colorCode: Int) {
    BLACK(0),
    WHITE(1),
    TRANSPARENT(2);

    override fun toString() = colorCode.toString()

}

fun decode(colorCode: Char): PixelColor {
    return when (colorCode) {
        '0' -> BLACK
        '1' -> WHITE
        '2' -> TRANSPARENT
        else -> throw IllegalArgumentException("$colorCode")
    }
}

fun decodeImage(image: String, width: Int, height: Int): Layer {
    val layers = splitIntoLayers(image, width, height)
            .map { toLayer(it, width, height) }
            .toList()

    return renderImage(layers)
}

fun renderImage(layers: List<Layer>): Layer {
    val pixelMap: Table<Int, Int, PixelColor> = HashBasedTable.create()
    val height = layers[0].pixelMap.rowKeySet().size
    val width = layers[0].pixelMap.columnKeySet().size
    for (left in 0 until width) {
        for (top in 0 until height) {
            pixelMap.put(top, left, renderPixel(top, left, layers))
        }
    }
    return Layer(pixelMap)
}

fun renderPixel(top: Int, left: Int, layers: List<Layer>): PixelColor {
    return layers.first { it.colorAt(top, left) != TRANSPARENT }
            .colorAt(top, left)
}

fun toLayer(encodedLayer: String, width: Int, height: Int): Layer {
    val pixelMap: Table<Int, Int, PixelColor> = HashBasedTable.create()
    for (left in 0 until width) {
        for (top in 0 until height) {
            pixelMap.put(top, left, decode(encodedLayer[top * width + left]))
        }
    }
    return Layer(pixelMap)

}

data class Layer(val pixelMap: Table<Int, Int, PixelColor>) {
    fun colorAt(top: Int, left: Int): PixelColor {
        return pixelMap.get(top, left)!!
    }

    override fun toString(): String {
        val builder = StringBuilder()
        val height = pixelMap.rowKeySet().size
        val width = pixelMap.columnKeySet().size
        for (top in 0 until height) {
            for (left in 0 until width) {
                builder.append(pixelMap[top, left])
            }
        }
        return builder.toString()
    }
}

fun main() {
    println(partOne(SIF_ENCODED_IMAGE))

    val image = decodeImage(SIF_ENCODED_IMAGE, WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE)
    drawImage(image)

}

fun drawImage(image: Layer) {
    val height = image.pixelMap.rowKeySet().size
    val width = image.pixelMap.columnKeySet().size
    for (top in 0 until height) {
        for (left in 0 until width) {
            print(image.colorAt(top, left))
        }
        println()
    }


}

internal fun partOne(sifEncodedImage: String): Int {
    val layerWithFewestZeros = splitIntoLayers(sifEncodedImage, WIDTH_OF_IMAGE, HEIGHT_OF_IMAGE)
            .map {
                val zeros = countZeros(it)
                Pair(it, zeros)
            }
            .minByOrNull { it.second }

    val pixesOnLayer = layerWithFewestZeros!!.first
    val ones = pixesOnLayer.count { it == '1' }
    val twos = pixesOnLayer.count { it == '2' }

    val product = ones * twos
    return product
}

fun countZeros(layer: String) = layer.count { it == '0' }


fun splitIntoLayers(sifEncodedImage: String, width: Int, height: Int): Sequence<String> = sequence {
    val pixesPerLayer = width * height
    for (layer in 0 until sifEncodedImage.length / pixesPerLayer) {
        yield(sifEncodedImage.substring(layer * pixesPerLayer, (layer + 1) * pixesPerLayer))
    }
}

const val WIDTH_OF_IMAGE = 25
const val HEIGHT_OF_IMAGE = 6

const val SIF_ENCODED_IMAGE = "221222222221222222222222012220222122222222222122222222222222222021222212220222222222222220102022202222222222121222220222122222022022222220202210222122221222222220222222222222012210220222222222222222222222222222222121222222220222222222220220222222222222222222020222222222022222022022222221202221222022222222222221222222222222122210221022222222222022222222222222222121222202222222222222220220102222212222222222120222221222222222022122222220202221222122222222222220222222222222202201220022222222222122222222222222222221222222221222222222220221000122222222222222222222221222102222122222222220222202222222220222222221222222222222112221220222222222222022222222222222222121222201222222222222221221102022221222222222220222220222022222122222222220202210222222220222222221222222222222112211222122222222222122222222222222222120222201221222222222222222021022211222222222021222222222112222022122222220220211222222222222222020222222222222112200220022222222222222122222222222222122222200222222222222222022121022212222222222222222220222212222022022222220201201222222222222222220222222222222022221220122222222222222022222222222222022222222221222222222220120010222222222222222221222221222112222022022222222210200222122222222222220222222222222022201220022222222222022122222222222222121222210220222222222221022010121201222222202221222221222022222022222222222222211222222220222222122222222222222202202220222222222222022122222222222222220222221222222222222222022212222201222222202022222221222112222022222222221212211222122220222222121222222202222112220221122222222222022122222222222222120222221222222222222221021011022202222222222120222222222212222022022222221212221222122220222222020222222202222012001221022222222222022022222222222222122222222222222222222220122100220210222222222122222220222122222122022222220201222222022221221222022222222212222002100221022222222222122222222222222222121222221222222222222221202022022210222222202020222221222022222122222222221202221222122220220222022222222212222202222221222222222222222222222222222222020222221222222222222220011121222220222222202021222222222222222222022222220210222222122220221222222222222212222012112222022022222222022022222222220222122222200221222222222222110121120210222222202220222221222202222022122222221202221222122222220222120222222222222002022222022122222222222222222222220222121222202220222222222220121111121201222222212100222220222122222122122222221212211222022220221222020222222202222012001220222022222222022122222222222222121222202222222222222222002001022211222222212101222222222212222122222222220212212222022220221222021222222222222212012221222022222222022122222222222122021222212222222221222220221010021200222222202011222221222022222122122222220200221222122221220222220222222211222222021220122122222222222022222222221022222222200222222222222220000101220222222222202022222221222122222222022222221201221222022220220222121222222222222202022222122022222222122222202222222122021222211222222221222221022120021221222222222200202221222212222022222222220210201222022222222222021222222221222112012221022220222222122022222222220222120222202221222221222222102111022221222222222000202221222222222122222222222201220222222222220222220222222221222212010220122022222222222022222222222022021222210222222220222220000200022221222222202022222220222222222222222222222200212222222221220222122222222221222222101221022222222222002222202221220122222222210222222220222222010000222220222222212212212220222102222022122222222212211222022221221222021222222202222122202220122120222222112221202222220122022222200220222220222221222002222210222222202101222222222202222022222222220221202222022222221222121222222202122002101220222222222222022020222222221222220222212222222220222221010112122222222222200120212221222122222222022212221221221222022221220222122222222202022102101221222021222222022121222220222122120222201222222220222222102001222211222222200202222221222122222122122202221202210222222221221222120222222200222012212220022121222222022200222220220122022222220221222221222221201110220200222222221122212220222102202222222222220221201222122221221222222222222212222202111221022221222222122021222220222022002222221221222220222222221201220211222222200020222221222012212122222222220211210222022220221222222222222200222102002220022020222222022002202222220222201222211220222220222221111202222220222222221210202221222022212022222222221220201222122222222222021222222200122222021222222221222222112101212221220022001222222221222220222222111202220212222222211110212222222112212022222202222211221222122220220222122222222210222012021222022221222222012010202221221222010222211222222222222220111101122201222222222221222221222122202022022202220222220222222220221222221222222201222112220220222022222222002021222220222122100222220220222220222222012212121200222222212200212221220212202021122202220212202222222222221222120222222211022002011221022222222222222022202222222222222222200220222120222221200211120222222222200122202221222112202122022222220201202222122220222222020222222221022022012222122221222222112202212220220022200222222220222221222221122021021210222222201210202220221202202020122222221210210222122221220222122222222202022002202221122020220222002120222220222222000222210222222120222220001201120202222222212002202221220112202221122202220220202222222222220222021222222220022102221221222121221222202211202221221022022222212220222222222220001120121222222222222112202220221202212222222212201210220222022222222222120222222201222202100222122021222222212120222221221022201202201220222021222222102220122211222222221200212220220202212120022222211211222222222222221222020222222220222022121222122220220222202200222221221222100222200221222021222222220202120210222222200020212220220202212121222222211212201222222221222222121222222220222112012222022222222222022020212221221022200202211221221121222222111001121200222222211020222220221002202202222202201212222222122220222222022222222221222202122222222121222222222002222221211122122202222221220021222222200011222202222222220012212221220210202200022222200221212222222220220222121222222201022212210221122221220222012210212222210022111202212221222220222222101201021211222222200222202222220022212100222212201212202222222221222222121222222212022022101222222222222222022121222221210022202202201221220120222221221201222201222222200200222221220221212021222212200200202222222220222222021222222202122002002221122020221222212112212220211222010202212220220121222221111102120222222222220020212222221111212202122212220210201022222222221222022222222202222002110222122021222222102101222222201122102222220220222220222221220211220211222222220002212220220222212021122222201222202122122222222222221222222222122002020221122022211222122211202221212022122212201221222122222221210212121211222222200022202222222220102002122202212212211122222222221222121222222202222012111220022022222222102211222222220122210202220222221220222221021121121210222222211021222220221112002212122212202210200222122220220222222222222222222012011222122020200222202201212220212222001222202221220222222221220210021212222222201110212222222011012202022212200222222022122221221222220222222222022122221220022022202222022011202221201222212212221220220022222221121210020200222222202211212221222221222220222202200221221222122222221222020222222212222212020221221121220222112010202221220122201222221221220222222220011121021200222222202000222222222111012122222202200201220222122221221222120222222201022112010222121121202222222021202221210222212222222221221120222222001211121220222222211202212221222121202011022222201201222022122222222222021222222220222122121220222121222222212210202221220022200202202222222020221222012021122221222222221021202222220201212010022202202201121222122222222222121222222212122102020220120022222222102010212220220222121222202220222122222220120121121220222222201021202222220211122122122222102200100122022220222222022222222212222002111222021121211222112102202220222122122202210220221221222220110221220212222222202202202222220001202110122202212212010022122222221222120222222211022012002220022120212222022110202220201122021212210202220120220220202212221202222222221222222220222002022121022222222212210222022220221222020222222212120102221220021120202222022222222221222222100202201220221122222222121102120212222222211022202221222111122222122202002210000222022020222222121222221202222022011220021221201222212102222222211022011202202212220022221222121101020210222222201221202222222101012210122222110212102121022222220222121222221202020202012220122021202222002020202220221222010222201221222021220220011020222200022222221101212221222002102202222212022201002020222121221222021222221201121002000220020220201022102011222220222222110202222201220221221221021121222201122222211011222220221221022212222222112200112012022120220222022222222222020222221221120222202222012010212220202122100222200221221220220220001111220221122222212000212221222221122122222222100200121020122222021222220222220202120202000221121122222022212010202222220222111012210222220020220220100110220210122222222011212220220010022110122202010210021012222221221222121222220220220120121221220022200022202102212222221222010102200222220220221222011012122200222222221111222220221110202000122202011202220200222120121222020222221212220200001220021020210022002112222222202022000112211210220022222220200211022202022222220020222221220110012001222222002222121201122021221222220222222221022002001221221022222222112211202222220122201002201211221120222222010210122200122222202120022222221022102120122202120222000210122222122222222222220210222211101222121020212222002200202220220122002002210222221222222222220022120212022222221201012021220102202220222222112210202101022221220222120222220200222121200221121221222122222222202221101112110212222212220221221220021110221201122222210111222020220212002021222202201211120210020221021222122222220220022112002220121221202022022221202220221202012022211202220022220220122111120201022222211222222022222002102001022202020210121212222022120222222222020201021111021221222122200222212121202222111202022022210210222121220220001120121202222222200210002222220020002112022222202210011201120222020222022222120211122111121222222120212022022020212220000102111102201222221221222221021101020202122222202201212021220111202221222222210202202022021122021222021222220211222102211220020121212122112221212221122002111202211220221220220220212120121202222222201211112121222100112102122202010220202000222021221222222222122222022101101221122022202222012212202222002012002002201201221020222222210010020210022222201102002121221211102121022202221220102111021221121222122222121200022011001221022022200222002200222220220202200022210200222221221220200211120211222222211212022220221222212010222212001202221112122120121222020222120222020102211220222022222222002201222222011002121112202211221022221222100121120222022222212022222121222201002100222212010201120111122221121222220222211212021001211220122120000122002200211222210012110222210121221220221221221010220220222222222202022221222011112110022212221220012110022121121222021222220212120221200221221120101122002202212220002022000122211210220200222222102211221201222222202120202221222202022020222202021212021120222222021222121222112212022020222221021021110022112202200220010202022112211102220210221220221202021212022222212222022022220121122210122212201211101222121021120222021222120211021201200221221220122222102011001222122012201102212221222201221221100101121202222222220120112220220110222020122212101212011200121021221222221222102202020222021221022120100222012001212220122122220022212022222112222220202022220221122222222101102121221011002202122202112210011111120222020222120222112000220221020220020222200022002202101220111011022202021002222112220220120120121221122222222102002021221120112211222222110201220010020120122222022222101120120201201220022022221222212112121222210011020002120200222100221221110122021221022222211121122121021102212102120202211210001112022020102222021222221102220022020221022121002222002212100221001101212112100222220020222222100001221200022222222112002222121000022021222222112212020011021121202222222222011210022002122220220121010022002222221222101122112012102202222110220220022222020211122222202110002120020220102010121202012221011021102120020222122222201112120101212222122020002222122020101220002101111102201101220000221221011101020201122222222100012022120212002000022202002221021122010120020222021222121111020112100220121121111022012211201222121022111002121101222201220220010222022202222222200102202122221012002220221212202220210120210121220222221222101222221010021222021000210022102121001221120220121202000222220112222222110010121202222222210102102021021222112021020212100222122001220122201222022222020102020022112222220000112022222020010220212001210012210021220111220221111221121220222222220120222121022200202221022202121201210110112220220222122222210111212001200221021110101122112201012220000111210002001112220221220222100222222221222222221100202121220011112220220202221221122200011221000222221222200222221020200221020120111122012000000222000202111002100101220020220220000100221202022222211211122121122102212101022212221211021022001122001222222222111001101011101221122211111122002001202220121122021222212021120020222221001222220200022222211120102020122002122022120222201212022022122021121222120222202220112201121222021211121022002222202222220121102100102220122202220220122211021221222222211120212221121200002110221202211220000102112221212222220222212021021112201222222210101222202010000222211200200112110021222101222222022021220201222222202121002022220212012110220202001201002001001020010222121222011012010122201221002001011222122100020220122201022022101200021120220221210022022200022022210020102220122111012102122222101211202022201020200222021222211210020011211222212101221222112211022221101110100212201021220000220221112020122221222022202200212122220121102101020222200212211101202222210222021222020021101010101221012010020122002020200221112110221202201012222000221220022021120212122122202212202222222022202022222202211212000220210210111222120222211120012112111220000202000222022020000221212200001210201210122001222220021202120210222122222122222220221101112000221202102211000001120110112222022222010111121210100220001120201222002222202222102212021201121212220221220221021210220221122212211121112122122122002121121212221212022010102210002222012222201120010022102222121100001222002212000220201122010022111000220210222220011112122220220202222120202121220212002221222212011001000021000010212222110222102222122222210221022101200022002110222221001101121022100201021221220221112112020210221202222110220122021001012201022222122221121001120012110222112222112220001012201222212222222022212211222222121011211200021100020012022222020022221210022122222121021120020002012202221202012202210212121002211222021220111010111110002222111220201122022002022221101102120200020202121102021222121010021200122002210100121221222111212002020012111212201101200021220001201111100220112001000101120121202210010201212012100022210001100022211012002012002122002010012210111012122111212021021100010110120221211211002"

