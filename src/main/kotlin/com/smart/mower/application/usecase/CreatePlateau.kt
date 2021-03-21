package com.smart.mower.application.usecase

import com.smart.mower.application.service.InstructionValidatorService
import com.smart.mower.domain.plateau.Plateau
import com.smart.mower.domain.plateau.PlateauId
import java.util.UUID

private const val DELIMITER = " "

class CreatePlateau(private val validator: InstructionValidatorService) {

    operator fun invoke(grid: String): Plateau {
        val plateauId = PlateauId(UUID.randomUUID())
        val (width, height) = getGridSize(grid)
        return Plateau(plateauId, width, height)
    }

    private fun getGridSize(gridValues: String): Pair<Int, Int> {
        validator.validateGrid(gridValues)
        val grid = gridValues.split(DELIMITER)
        val width = grid[0].toInt()
        val height = grid[1].toInt()
        return Pair(width, height)
    }

}
