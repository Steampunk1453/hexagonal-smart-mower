package com.smart.mower.application.service

private const val GRID_FORMAT_PATTERN = "^\\d+\\s\\d+$"
private const val POSITION_FORMAT_PATTERN = "^\\d+ \\d+ [N|E|S|W]$"
private const val COMMAND_FORMAT_PATTERN = "^([LRM]+)$"

class InstructionValidatorService {

    fun validateGrid(grid: String) {
        check(grid.matches(GRID_FORMAT_PATTERN.toRegex())) {
            "Invalid format for these grid dimensions: $grid." +
                    " Format should have correct width and height"
        }
    }

    fun validatePosition(position: String) {
        check(position.matches(POSITION_FORMAT_PATTERN.toRegex())) {
            "Invalid format for these positions: $position." +
                    " Format should have X Y and compass point (N|E|S|W)"
        }
    }

    fun validateCommands(commands: String) {
        check(commands.matches(COMMAND_FORMAT_PATTERN.toRegex())) {
            "Invalid format for these commands: $commands." +
                    " Format should have any of these letters: L|R|M"
        }
    }

}
