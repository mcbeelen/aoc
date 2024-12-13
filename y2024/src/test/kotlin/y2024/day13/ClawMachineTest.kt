package y2024.day13

import com.natpryce.hamkrest.assertion.assertThat
import org.junit.Test
import com.natpryce.hamkrest.equalTo

class ClawMachineTest  {

    @Test
    fun testFindCheapestWayToWin() {
        val a = Button(94, 34)
        val b = Button(22, 67)
        val price = Location(8400, 5400)

        val clawMachine = ClawMachine(a, b, price)
        val cheapestWayToWin = clawMachine.findCheapestWayToWin()
        assertThat(cheapestWayToWin.isPresent, equalTo(true))
        val (timesA, timesB) = cheapestWayToWin.get()
        assertThat(timesA, equalTo(80))
        assertThat(timesB, equalTo(40))

    }
}