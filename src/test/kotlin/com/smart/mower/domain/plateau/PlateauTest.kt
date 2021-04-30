package com.smart.mower.domain.plateau

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.UUID

@ExtendWith(SpringExtension::class)
internal class PlateauTest {

    @Test
    fun `should create new plateau`() {
        val plateauId = PlateauId(UUID.randomUUID())
        val width = 5
        val height = 5

        val result = Plateau(plateauId, width, height)

        assertThat(result, `is`(not(nullValue())))
        assertThat(result.id, `is`(not(nullValue())))
        assertThat(result.width, `is`(width))
        assertThat(result.height, `is`(height))
    }

    @Test
    fun `should throw PlateauException when create new plateau and width is negative`() {
        val plateauId = PlateauId(UUID.randomUUID())
        val width = -1
        val height = 5

        assertThrows<PlateauException> { Plateau(plateauId, width, height) }
    }

    @Test
    fun `should throw PlateauException when create new plateau and height is negative`() {
        val plateauId = PlateauId(UUID.randomUUID())
        val width = 5
        val height = -1

        assertThrows<PlateauException> { Plateau(plateauId, width, height) }
    }

}

