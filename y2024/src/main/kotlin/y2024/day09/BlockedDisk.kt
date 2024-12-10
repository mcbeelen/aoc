package y2024.day09

import java.util.*

abstract class Block(val position: Long, val size : Int) {
    abstract fun isFree() : Boolean
}

class FileBlock(private val p: Long, private val s : Int, val fileId: Long) : Block(p, s) {
    override fun isFree() = false
    override fun toString()= fileId.mod(10).toString().repeat(size)
}

class FreeBlock(private val p: Long, private val s : Int) : Block(p, s) {
    override fun isFree() = true
    override fun toString()= ".".repeat(size)
}

class BlockedDisk(private val diskMap: String) {
    val blocks : MutableList<Block> by lazy {
        parseDiskMap()
    }

    private fun parseDiskMap() : MutableList<Block> {

        val foundBlocks : MutableList<Block> = mutableListOf()
        var idCounter = 0L
        var positionCounter = 0L
        var fileIndicator = true
        diskMap.forEach { block ->
            val size = block.digitToInt()
            if (size > 0) {
                if (fileIndicator) {
                    foundBlocks.add(FileBlock(positionCounter, size, idCounter))
                    idCounter++
                } else {
                    foundBlocks.add(FreeBlock(positionCounter, size))
                }
                positionCounter = positionCounter + size
            }
            fileIndicator = !fileIndicator
        }
        return foundBlocks
    }

    override fun toString(): String {
        return blocks.joinToString("" ) {
            it.toString()
        }
    }
}
