package com.smart.mower.application.usecase

import com.smart.mower.application.service.InstructionValidatorService
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

private const val DELIMITER = ","

@ExtendWith(SpringExtension::class)
internal class CreatePlateauTest {

    @InjectMockKs
    private lateinit var useCase: CreatePlateau

    @MockK
    private lateinit var service: InstructionValidatorService

    @Test
    fun `should create a new plateau and return it`() {
        val source = "5 5,1 2 N,LMLMLMLMM,3 3 E,MMRMMRMRRM"
        val instructions = source.split(DELIMITER)
        val width = 5
        val height = 5
        every { service.validateGrid(any()) } just Runs

        val result = useCase.invoke(instructions)

        assertThat(result, `is`(not(nullValue())))
        assertThat(result.plateauId, `is`(not(nullValue())))
        assertThat(result.width, `is`(width))
        assertThat(result.height, `is`(height))
    }

}
