package y2024.day14

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import util.grid.ScreenCoordinate
import util.grid.Vector

class RobotTest {

    @Test
    fun teleportingToTheLeft() {
        val robot = Robot(ScreenCoordinate(2, 2), Vector(-1, 0))
        assertThat(robot.teleport(1, 13, 11).position.left, equalTo(1))
        assertThat(robot.teleport(2, 13, 11).position.left, equalTo(0))
        assertThat(robot.teleport(3, 13, 11).position.left, equalTo(12))
    }

    @Test
    fun teleportingToTheRight() {
        val robot = Robot(ScreenCoordinate(2, 2), Vector(3, 0))
        assertThat(robot.teleport(1, 13, 11).position.left, equalTo(5))
        assertThat(robot.teleport(2, 13, 11).position.left, equalTo(8))
        assertThat(robot.teleport(3, 13, 11).position.left, equalTo(11))
        assertThat(robot.teleport(4, 13, 11).position.left, equalTo(1))
    }

    @Test
    fun teleportingDownward() {
        val robot = Robot(ScreenCoordinate(2, 2), Vector(0, 5))
        assertThat(robot.teleport(1, 13, 11).position.top, equalTo(7))
        assertThat(robot.teleport(2, 13, 11).position.top, equalTo(1))
        assertThat(robot.teleport(3, 13, 11).position.top, equalTo(6))
        assertThat(robot.teleport(4, 13, 11).position.top, equalTo(0))
    }

    @Test
    fun teleportingUpward() {
        val robot = Robot(ScreenCoordinate(2, 2), Vector(0, -6))
        assertThat(robot.teleport(1, 13, 11).position.top, equalTo(7))
        assertThat(robot.teleport(2, 13, 11).position.top, equalTo(1))
        assertThat(robot.teleport(3, 13, 11).position.top, equalTo(6))
        assertThat(robot.teleport(4, 13, 11).position.top, equalTo(0))
        assertThat(robot.teleport(5, 13, 11).position.top, equalTo(5))
    }

}