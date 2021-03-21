package com.smart.mower.application.usecase

import com.smart.mower.application.service.InstructionValidatorService
import com.smart.mower.domain.mower.Orientation
import com.smart.mower.domain.plateau.PlateauSize
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

private const val DELIMITER = ","

@ExtendWith(SpringExtension::class)
internal class CreateMowerTest {

    @InjectMockKs
    private lateinit var useCase: CreateMower

    @MockK
    private lateinit var service: InstructionValidatorService

    @Test
    fun `should create a new mower and return it`() {
        val source = "5 5,1 2 N,LMLMLMLMM,3 3 E,MMRMMRMRRM"
        val instructions = source.split(DELIMITER)
        val coordinates = instructions[1]
        val commands = instructions[2]
        val plateauSize = PlateauSize(5, 5)
        val x = 1
        val y = 2
        val width = 5
        val height = 5
        every { service.validatePosition(any()) } just Runs
        every { service.validateCommands(any()) } just Runs

        val result = useCase.invoke(coordinates, commands, plateauSize)

        assertThat(result, `is`(not(nullValue())))
        assertThat(result.position.x, `is`(x))
        assertThat(result.position.y, `is`(y))
        assertThat(result.orientation, `is`(Orientation.NORTH))
        assertThat(result.commands.size, `is`(not(0)))
        assertThat(result.plateauSize.width, `is`(width))
        assertThat(result.plateauSize.height, `is`(height))
    }

}
