package y2019.day11_emergency_hull_painting_robot

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class EmergencyHullPaintingRobotTest {
    @Test
    fun verifyPartOne() {
        assertThat(countNumberOfPaintedPanels(), equalTo(1985))
    }
}