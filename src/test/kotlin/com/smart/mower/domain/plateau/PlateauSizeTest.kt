package com.smart.mower.domain.plateau

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class PlateauSizeTest {

    @Test
    fun `should create new plateauSize`() {
        val width = 10
        val height = 10

        val result = PlateauSize( width, height)

        MatcherAssert.assertThat(result, Matchers.`is`(Matchers.not(Matchers.nullValue())))
        MatcherAssert.assertThat(result.width, Matchers.`is`(width))
        MatcherAssert.assertThat(result.height, Matchers.`is`(height))
    }

    @Test
    fun `should throw PlateauException when create new plateauSize and width is negative`() {
        val width = -2
        val height = 10

        assertThrows<PlateauException> { PlateauSize(width, height) }
    }

    @Test
    fun `should throw PlateauException when create new plateauSize and height is negative`() {
        val width = 10
        val height = -3

        assertThrows<PlateauException> { PlateauSize(width, height) }
    }

}
