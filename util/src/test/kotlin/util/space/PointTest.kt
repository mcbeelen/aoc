package util.space

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PointTest {

    @Test
    fun `it should yield 26 neighbours`() {
        val neighbors = ORIGIN.neighbors()
        assertThat(neighbors, hasSize(equalTo(26)))

        assertTrue(neighbors.contains(Point(-1, -1, -1)))
        assertTrue(neighbors.contains(Point(1, 1, 1)))
        assertFalse(neighbors.contains(ORIGIN))
        assertFalse(neighbors.contains(Point(2, 1,1)))
    }
}


