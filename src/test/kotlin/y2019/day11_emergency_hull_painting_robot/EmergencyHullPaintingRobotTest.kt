package y2019.day11_emergency_hull_painting_robot

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import util.grid.Direction.DOWN
import util.grid.Direction.LEFT
import util.grid.Direction.UP
import util.grid.ORIGIN
import util.grid.at
import y2019.day11_emergency_hull_painting_robot.PanelColor.BLACK

class EmergencyHullPaintingRobotTest {
    @Test
    fun verifyPartOne() {
        assertThat(countNumberOfPaintedPanels(), equalTo(1985))
    }
}