package y2024.day09

import util.puzzle.AdventOfCodePuzzle
import java.util.Optional

typealias FoundBlock = Long

class Disk(val diskMap: String) {

    val blocks : MutableList<Optional<FoundBlock>> by lazy {
        parseDiskMap()
    }

    private fun parseDiskMap() : MutableList<Optional<FoundBlock>> {

        val foundBlocks : MutableList<Optional<FoundBlock>> = mutableListOf()
        var idCounter = 0L
        var fileIndicator = true
        diskMap.forEach { block ->
            for (i in 0 until block.digitToInt()) {
                if (fileIndicator) {
                    foundBlocks.add(Optional.of(idCounter))
                } else {
                    foundBlocks.add(Optional.empty())
                }
            }
            if (fileIndicator) {
                idCounter++
            }
            fileIndicator = !fileIndicator
        }
        return foundBlocks
    }

    override fun toString(): String {
        return blocks.joinToString("" ) {
            if (it.isPresent) {
                it.get().mod(10).toString()
            } else {
               "."
            }
        }
    }

    private fun findFreeBlockFrom(startingPosition: Int): Int {
        for (i in startingPosition + 1 until blocks.size) {
            if (blocks[i].isEmpty) {
                return i
            }
        }
        return Int.MIN_VALUE
    }

    private fun findFileBlockBefore(startingPosition: Int): Int {
        for (i in startingPosition.downTo(0)) {
            if (blocks[i].isPresent) {
                return i
            }
        }
        return Int.MIN_VALUE
    }

    fun defrag() {
        var indexOfFirstFreeBlock = findFreeBlockFrom(0)
        var lastIndexOfBlockWithFile = findFileBlockBefore(blocks.size - 1)
        while (indexOfFirstFreeBlock <= lastIndexOfBlockWithFile) {
            blocks[indexOfFirstFreeBlock] = blocks[lastIndexOfBlockWithFile]
            blocks[lastIndexOfBlockWithFile] = Optional.empty()
            indexOfFirstFreeBlock = findFreeBlockFrom(indexOfFirstFreeBlock)
            lastIndexOfBlockWithFile = findFileBlockBefore(lastIndexOfBlockWithFile)
        }
    }

    fun calculateChecksum(): Long = blocks
            .filter { it.isPresent }
            .map { it.get() }
            .mapIndexed { index, id -> index * id }
            .sum()

    fun defragByFiles() {
        TODO("Not yet implemented")
    }


}

class DiskFragmenter(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun getAnswerForPartOne(): String {
        val disk = Disk(input[0])
        disk.defrag()
        return disk.calculateChecksum().toString()
    }


    override fun getAnswerForPartTwo(): String {
        val disk = BlockedDisk(input[0])
        return disk.toString()
//        disk.defrag()
//        return disk.calculateChecksum().toString()
    }
}



fun main() {
    DiskFragmenter().getAnswers()
}