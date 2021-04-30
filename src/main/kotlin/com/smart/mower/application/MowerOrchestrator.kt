package com.smart.mower.application

import com.smart.mower.application.usecase.CreateMower
import com.smart.mower.application.usecase.CreatePlateau
import com.smart.mower.application.usecase.MoveMower
import com.smart.mower.domain.mower.Mower
import com.smart.mower.domain.plateau.Plateau
import com.smart.mower.domain.plateau.PlateauSize
import com.smart.mower.domain.port.PlateauRepository
import java.util.UUID

private const val DELIMITER = ","
private const val GRID_POSITION = 0

class MowerOrchestrator(private val createPlateau: CreatePlateau,
                        private val createMower: CreateMower,
                        private val moveMower: MoveMower,
                        private val plateauRepository: PlateauRepository) {

    fun handleInstructions(instructions: String): Plateau {
        val instructionList = instructions.split(DELIMITER)
        val mowersNumber = instructionList.size.minus(1).div(2)
        val grid = instructionList[GRID_POSITION]
        val plateau = createPlateau(grid)
        val plateauSize = PlateauSize(plateau.width, plateau.height)

        for (i in 0 until mowersNumber) {
            val coordinatesPosition = 2 * i + 1
            val commandsPosition = 2 * i + 2
            val mower = createMower(instructionList[coordinatesPosition], instructionList[commandsPosition], plateauSize)
            plateau.addMower(mower)
        }

        plateauRepository.save(plateau)

        return plateau
    }

    fun executeInstructions(plateauId: UUID): List<Mower> {
        val mowers = mutableListOf<Mower>()
        val plateau = plateauRepository.findById(plateauId)
        val mowersDeployed = plateau?.mowers

        mowersDeployed?.forEach {
            mower -> mowers.add(moveMower(mower))
        }

        return mowers
    }

}
