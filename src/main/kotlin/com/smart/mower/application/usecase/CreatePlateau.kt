package com.smart.mower.application.usecase

import com.smart.mower.application.service.InstructionValidatorService
import com.smart.mower.domain.plateau.Plateau
import com.smart.mower.domain.plateau.PlateauId
import java.util.UUID

private const val DELIMITER = " "

class CreatePlateau(private val validator: InstructionValidatorService) {

    operator fun invoke(instructions: List<String>): Plateau {
        val plateauId = PlateauId(UUID.randomUUID())
        val (width, height) = getGridSize(instructions)
        return Plateau(plateauId, width, height)
    }

    private fun getGridSize(instructions: List<String>): Pair<Int, Int> {
        val gridValues = instructions[0]
        validator.validateGrid(gridValues)
        val grid = instructions[0].split(DELIMITER)
        val width = grid[0].toInt()
        val height = grid[1].toInt()
        return Pair(width, height)
    }

}
