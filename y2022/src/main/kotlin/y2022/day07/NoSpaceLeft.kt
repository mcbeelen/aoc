package y2022.day07

import util.puzzle.AdventOfCodePuzzle
import java.util.LinkedList


class NoSpaceLeft(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val fileSystem = FileSystem()
    private var currentDirectory = fileSystem.root

    init {
        input.forEach {
            when {
                it.startsWith("\$ ") -> processCommand(it.substring(2))
                else -> addEntryToCurrentDirectory(it)
            }
        }
    }

    override fun solvePartOne(): Int {
        return findAllDirectories()
            .filter { it.getTotalSize() <= 100000 }
            .sumOf { it.getTotalSize() }
    }

    private fun findAllDirectories(): Iterable<Directory> {
        return fileSystem.root.getAllDirectories()
    }

    private fun addEntryToCurrentDirectory(listing: String) {
        when {
            listing.startsWith("dir") -> {
                val directoryName = listing.substring(4)
                currentDirectory.addDirectory(directoryName)
            }
            else -> {
                // it is a file
                val size = listing.substringBefore(" ").toInt()
                val fileName = listing.substringAfter(" ")
                currentDirectory.addFile(File(fileName, size))
            }
        }

    }

    private fun processCommand(command: String) {
        when (command) {
            "ls" -> {} // do nothing
            "cd .." -> currentDirectory = currentDirectory.parent!!
            "cd /" -> currentDirectory = fileSystem.root
            else -> {
                val directoryName = command.substring(3)
                currentDirectory = currentDirectory.getDirectory(directoryName)
            }
        }

    }

    val diskSize = 70000000
    val diskSpaceNeeded = 30000000

    override fun solvePartTwo(): Int {
        val unusedSpace = diskSize - fileSystem.root.getTotalSize()
        val additionallyNeededDiscSpace = diskSpaceNeeded - unusedSpace

        val minOf = findAllDirectories()
            .filter { it.getTotalSize() > additionallyNeededDiscSpace }
            .minOf { it.getTotalSize() }

        return minOf
    }
}

fun main() {
    NoSpaceLeft().getAnswers()
}

class FileSystem(val root: Directory = Directory(name = "/"))

class Directory(
    val parent: Directory? = null,
    val name : String) {

    private val directories = LinkedList<Directory>()
    private val files = LinkedList<File>()

    fun addFile(file: File) {
        this.files.add(file)
    }

    fun getDirectory(directoryName: String) = directories
        .single { it.name == directoryName }

    fun addDirectory(directoryName: String) = directories.add(
        Directory(this, directoryName)
    )

    fun getTotalSize() : Int = files.sumOf { it.size } +
        directories.sumOf { it.getTotalSize() }

    fun getAllDirectories(): Iterable<Directory> {
        return directories.flatMap {
            it.getAllDirectories()
        } + this
    }

}

data class File(val name: String, val size: Int)
