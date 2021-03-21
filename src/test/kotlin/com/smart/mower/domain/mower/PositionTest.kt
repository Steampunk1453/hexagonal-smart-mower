package com.smart.mower.domain.mower

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class PositionTest {

    @Test
    fun `should create new position`() {
        val x = 1
        val y = 2

        val result = Position(x, y)

        assertThat(result, `is`(not(nullValue())))
        assertThat(result.x, `is`(x))
        assertThat(result.y, `is`(y))
    }

    @Test
    fun `should throw MowerException when create new position and X is negative`() {
        val x = -1
        val y = 3

        assertThrows<MowerException> { Position(x, y) }
    }

    @Test
    fun `should throw MowerException when create new position and Y is negative`() {
        val x = 4
        val y = -5

        assertThrows<MowerException> { Position(x, y) }
    }

}
