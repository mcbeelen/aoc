package util.grid

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ScreenCoordinateIteratorTest {

    @Test
    fun leftToRight() {

        val iterator = ScreenCoordinateIterator(
            ScreenCoordinate(0, 9),
            ScreenCoordinate(5, 9)
        )

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(0, 9)))

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(1, 9)))

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(2, 9)))

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(3, 9)))

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(4, 9)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(5, 9)))

        assertFalse(iterator.hasNext())

    }


    @Test
    fun rightToLeft() {

        val iterator = ScreenCoordinateIterator(
            ScreenCoordinate(4, 7),
            ScreenCoordinate(2, 7)
        )

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(4, 7)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(3, 7)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(2, 7)))

        assertFalse(iterator.hasNext())

    }

    @Test
    fun toToBottom() {

        val iterator = ScreenCoordinateIterator(
            ScreenCoordinate(4, 6),
            ScreenCoordinate(4, 7)
        )

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(4, 6)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(4, 7)))

        assertFalse(iterator.hasNext())
    }


    @Test
    fun bottomUp() {

        val iterator = ScreenCoordinateIterator(
            ScreenCoordinate(4, 332),
            ScreenCoordinate(4, 329)
        )

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(4, 332)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(4, 331)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(4, 330)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(4, 329)))

        assertFalse(iterator.hasNext())
    }

    @Test
    fun diagonalLines() {

        val iterator = ScreenCoordinateIterator(
            ScreenCoordinate(4, 6),
            ScreenCoordinate(8, 10)
        )

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(4, 6)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(5, 7)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(6, 8)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(7, 9)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(8, 10)))

        assertFalse(iterator.hasNext())
    }

    @Test
    fun aroundOrigin() {

        val iterator = ScreenCoordinateIterator(
            ScreenCoordinate(-1, -2),
            ScreenCoordinate(-3, 6)
        )

        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(-1, -2)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(-2, 2)))
        assertTrue(iterator.hasNext())
        assertThat(iterator.next(), equalTo(ScreenCoordinate(-3, 6)))

        assertFalse(iterator.hasNext())
    }


}