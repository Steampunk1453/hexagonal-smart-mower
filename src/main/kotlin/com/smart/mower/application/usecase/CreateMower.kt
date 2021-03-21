package com.smart.mower.application.usecase

import com.smart.mower.application.service.InstructionValidatorService
import com.smart.mower.domain.mower.Command
import com.smart.mower.domain.mower.Mower
import com.smart.mower.domain.mower.MowerException
import com.smart.mower.domain.mower.Orientation
import com.smart.mower.domain.mower.Position
import com.smart.mower.domain.plateau.PlateauSize

private const val DELIMITER = " "
private const val ORIENTATION_POSITION = 2

class CreateMower(private val validator: InstructionValidatorService) {

    private val orientations: Map<String, Orientation> =
        mapOf("N" to Orientation.NORTH, "S" to Orientation.SOUTH, "E" to Orientation.EAST, "W" to Orientation.WEST)

    private val commands: Map<Char, Command> =
        mapOf('L' to Command.LEFT, 'R' to Command.RIGHT, 'M' to Command.FORWARD)

    operator fun invoke(coordinates: String, commandValues: String, plateauSize: PlateauSize): Mower {
        return buildMower(coordinates, commandValues, plateauSize)
    }

    private fun buildMower(coordinates: String, commandValues: String, plateauSize: PlateauSize): Mower {
        validator.validatePosition(coordinates)
        validator.validateCommands(commandValues)

        val positions = coordinates.split(DELIMITER)
        val position = Position(positions[0].toInt(), positions[1].toInt())
        val orientation = orientations.getOrElse(positions[ORIENTATION_POSITION], { throw MowerException(("Not found orientation")) })
        val commands = getCommands(commandValues)

        return Mower(position, orientation, commands, plateauSize)
    }

    private fun getCommands(commandValues: String): MutableList<Command> {
        val commands = mutableListOf<Command>()
        commandValues.forEach {
            this.commands[it]?.let(commands::add)
        }
        return commands
    }

}
