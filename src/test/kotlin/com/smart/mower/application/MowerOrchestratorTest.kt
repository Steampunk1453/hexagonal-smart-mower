package com.smart.mower.application

import com.smart.mower.application.usecase.CreateMower
import com.smart.mower.application.usecase.CreatePlateau
import com.smart.mower.application.usecase.MoveMower
import com.smart.mower.domain.mower.Command
import com.smart.mower.domain.mower.Mower
import com.smart.mower.domain.mower.Orientation
import com.smart.mower.domain.mower.Position
import com.smart.mower.domain.plateau.Plateau
import com.smart.mower.domain.plateau.PlateauId
import com.smart.mower.domain.plateau.PlateauSize
import com.smart.mower.domain.port.PlateauRepository
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
import java.util.UUID

@ExtendWith(SpringExtension::class)
internal class MowerOrchestratorTest {

    @InjectMockKs
    private lateinit var orchestrator: MowerOrchestrator

    @MockK
    private lateinit var createPlateau: CreatePlateau

    @MockK
    private lateinit var createMower: CreateMower

    @MockK
    private lateinit var moveMower: MoveMower

    @MockK
    private lateinit var plateauRepository: PlateauRepository

    @Test
    fun `should handle instructions and return plateau`() {
        val instructions = "5 5,1 2 N,LMLMLMLMM,3 3 E,MMRMMRMRRM"
        val plateau = buildPlateau()
        val mower = buildMower()

        every { createPlateau(any()) } returns plateau
        every { createMower(any(), any(), any()) } returns mower
        every { plateauRepository.save(any()) } just Runs

        val result = orchestrator.handleInstructions(instructions)

        assertThat(result, `is`(not(nullValue())))
        assertThat(result.plateauId, `is`(not(nullValue())))
        assertThat(result.width, `is`(plateau.width))
        assertThat(result.height, `is`(plateau.height))
        assertThat(result.mowers.size, `is`(not(0)))
        assertThat(result.mowers[0].position.x, `is`(mower.position.x))
        assertThat(result.mowers[0].position.y, `is`(mower.position.y))
        assertThat(result.mowers[0].orientation, `is`(Orientation.NORTH))
        assertThat(result.mowers[0].commands.size, `is`(not(0)))
        assertThat(result.mowers[0].plateauSize.width, `is`(plateau.width))
        assertThat(result.mowers[0].plateauSize.height, `is`(plateau.height))
    }

    @Test
    fun `should execute instructions and return list with mowers`() {
        val plateau = buildPlateau()
        val mower = buildMower()
        val plateauId = plateau.plateauId.id
        plateau.addMower(mower)

        every { plateauRepository.findById(any()) } returns plateau
        every { moveMower(any()) } returns mower

        val result = orchestrator.executeInstructions(plateauId)

        assertThat(result.size, `is`(not(0)))
        assertThat(result[0].position.x, `is`(mower.position.x))
        assertThat(result[0].position.y, `is`(mower.position.y))
        assertThat(result[0].orientation, `is`(Orientation.NORTH))
        assertThat(result[0].commands.size, `is`(not(0)))
        assertThat(result[0].plateauSize.width, `is`(plateau.width))
        assertThat(result[0].plateauSize.height, `is`(plateau.height))
    }

    private fun buildPlateau(): Plateau {
        val plateauId = PlateauId(UUID.randomUUID())
        val width = 5
        val height = 5
        return Plateau(plateauId, width, height)
    }

    private fun buildMower(): Mower {
        val position = Position(1, 2)
        val orientation = Orientation.NORTH
        val commands = mutableListOf(Command.LEFT, Command.RIGHT, Command.FORWARD)
        val plateauSize = PlateauSize(5, 5)
        return Mower(position, orientation, commands, plateauSize)
    }

}
