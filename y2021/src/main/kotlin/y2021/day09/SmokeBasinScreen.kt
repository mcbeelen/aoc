package y2021.day09

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration
import util.grid.ScreenCoordinate
import util.grid.parseToGrid
import util.terminal.ScreenOutputStream
import java.awt.Font

class SmokeBasinScreen : SmokeBasin(), ScreenOutputStream<Int> {

    private val heightMap = parseToGrid(input) { it.toString().toInt() }

    private var counter = 0

    private val screen: Screen = initScreen(input.size, input[0].length)

    fun emulateFlooding() {
        //    Thread.sleep(10000L)
        while (counter < 9) {
            heightMap.entries.forEach {
                paint(it.key, it.value)
            }
            Thread.sleep(1000L)
            screen.refresh()

            counter++
        }

        Thread.sleep(4000L)

        screen.stopScreen()
    }


    override fun paint(coordinate: ScreenCoordinate, value: Int) {
        val textCharacter = formatTextCharacter(value)
        screen.setCharacter(coordinate.left, coordinate.top, textCharacter)
    }


    private fun formatTextCharacter(value: Int): TextCharacter {

        return when {
            value <= counter -> TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.BLUE)
            value == 1 -> TextCharacter(value.toString()[0], TextColor.ANSI.WHITE, TextColor.ANSI.WHITE)
            value == 2 -> TextCharacter(value.toString()[0], TextColor.ANSI.WHITE, TextColor.Factory.fromString("#eeeeee"))
            value == 3 -> TextCharacter(value.toString()[0], TextColor.ANSI.WHITE, TextColor.Factory.fromString("#cccccc"))
            value == 4 -> TextCharacter(value.toString()[0], TextColor.ANSI.WHITE, TextColor.Factory.fromString("#aaaaaa"))
            value == 5 -> TextCharacter(value.toString()[0], TextColor.ANSI.WHITE, TextColor.Factory.fromString("#888888"))
            value == 6 -> TextCharacter(value.toString()[0], TextColor.ANSI.WHITE, TextColor.Factory.fromString("#666666"))
            value == 7 -> TextCharacter(value.toString()[0], TextColor.ANSI.WHITE, TextColor.Factory.fromString("#444444"))
            value == 8 -> TextCharacter(value.toString()[0], TextColor.ANSI.WHITE, TextColor.Factory.fromString("#222222"))
            else -> TextCharacter(value.toString()[0], TextColor.ANSI.WHITE, TextColor.ANSI.BLACK)
        }
    }
}


private fun initScreen(rows : Int, columns: Int): Screen {
    val terminalFactory = DefaultTerminalFactory()
    terminalFactory.setInitialTerminalSize(TerminalSize(columns, rows))
    val font = Font("Courier New", Font.PLAIN, 16)
    terminalFactory.setTerminalEmulatorFontConfiguration(SwingTerminalFontConfiguration.newInstance(font))
    terminalFactory.setTerminalEmulatorTitle("Flooding!!")

    val terminal: Terminal = terminalFactory.createTerminal()

    val screen: Screen = TerminalScreen(terminal)

    screen.startScreen()
    return screen

}

fun main() {
    val screen = SmokeBasinScreen()
    screen.emulateFlooding()
}