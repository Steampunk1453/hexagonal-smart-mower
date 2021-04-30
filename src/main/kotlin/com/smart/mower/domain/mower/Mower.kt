package com.smart.mower.domain.mower

import com.smart.mower.domain.plateau.PlateauSize

class Mower(val position: Position, var orientation: Orientation, val commands: MutableList<Command>,
            val plateauSize: PlateauSize) {

    private val turnRight: Map<Orientation, Orientation> =
        mapOf(Orientation.NORTH to Orientation.EAST, Orientation.EAST to Orientation.SOUTH,
            Orientation.SOUTH to Orientation.WEST, Orientation.WEST to Orientation.NORTH)

    private val turnLeft: Map<Orientation, Orientation> =
        mapOf(Orientation.NORTH to Orientation.WEST, Orientation.WEST to Orientation.SOUTH,
            Orientation.SOUTH to Orientation.EAST, Orientation.EAST to Orientation.NORTH)

    fun startMovement() {
        while (commands.isNotEmpty()) {
            move()
        }
    }

    private fun move() {
        when (val command = commands[0]) {
            Command.FORWARD -> advance()
            Command.RIGHT,
            Command.LEFT -> orientation = changeDirection(orientation, command)
        }
        commands.removeAt(0)
    }

    private fun advance() {
        when (orientation) {
            Orientation.NORTH -> if (position.y < plateauSize.height) {
                position.y = position.y + Movement.FORWARD.step
            }
            Orientation.EAST -> if (position.x < plateauSize.width) {
                position.x = position.x + Movement.FORWARD.step
            }
            Orientation.SOUTH -> if (position.y > 0) {
                position.y = position.y - 1
            }
            Orientation.WEST -> if (position.x > 0) {
                position.x = position.x - 1
            }
        }
    }

    private fun changeDirection(direction: Orientation, side: Command): Orientation {
        return when (side) {
            Command.LEFT -> turnLeft.getOrElse(direction, { throw MowerException(("Not found direction")) })
            Command.RIGHT -> turnRight.getOrElse(direction, { throw MowerException(("Not found direction")) })
            else -> throw MowerException("Not found correct side")
        }
    }

}
