package com.smart.mower.application.service

import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class InstructionValidatorServiceTest {

    @InjectMockKs
    private lateinit var validator: InstructionValidatorService

    @Test
    fun `should successfully validate grid values`() {
        val grid = "5 5"

        validator.validateGrid(grid)
    }

    @Test
    fun `should throw IllegalStateException for grid values with invalid format`() {
        val grid = "5 A 5"

        assertThrows<IllegalStateException> { validator.validateGrid(grid) }
    }

    @Test
    fun `should successfully validate position values`() {
        val position = "1 2 N"

        validator.validatePosition(position)
    }


    @Test
    fun `should throw IllegalStateException for position values with invalid compass point`() {
        val position = "1 2 T"

        assertThrows<IllegalStateException> { validator.validatePosition(position) }
    }

    @Test
    fun `should successfully validate command values`() {
        val commands = "LMLMLMLMM"

        validator.validateCommands(commands)
    }

    @Test
    fun `should throw IllegalStateException for invalid command values`() {
        val commands = "LMLMLMLTT"

        assertThrows<IllegalStateException> { validator.validateCommands(commands) }
    }

}
