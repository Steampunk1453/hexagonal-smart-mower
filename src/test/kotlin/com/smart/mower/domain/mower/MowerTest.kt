package com.smart.mower.domain.mower

import com.smart.mower.domain.plateau.PlateauSize
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class MowerTest {

    @Test
    fun `should create new mower`() {
        val position = Position(1, 2)
        val orientation = Orientation.NORTH
        val commands = mutableListOf(Command.LEFT, Command.RIGHT, Command.FORWARD)
        val plateauSize = PlateauSize(5, 5)

        val result = Mower(position, orientation, commands, plateauSize)

        assertThat(result, `is`(not(nullValue())))
        assertThat(result.position.x, `is`(position.x))
        assertThat(result.position.y, `is`(position.y))
        assertThat(result.orientation, `is`(Orientation.NORTH))
        assertThat(result.commands.size, `is`(not(0)))
        assertThat(result.plateauSize.width, `is`(plateauSize.width))
        assertThat(result.plateauSize.height, `is`(plateauSize.height))
    }

    @Test
    fun `should mower can change orientation`() {
        val position = Position(5, 5)
        val orientation = Orientation.NORTH
        val commands = mutableListOf(Command.LEFT, Command.RIGHT, Command.LEFT, Command.FORWARD)
        val plateauSize = PlateauSize(10, 10)
        val mower = Mower(position, orientation, commands, plateauSize)

        mower.startMovement()

        assertThat(mower, `is`(not(nullValue())))
        assertThat(mower.orientation, `is`(Orientation.WEST))
    }

    @Test
    fun `should mower can change position and go two steps`() {
        val position = Position(5, 5)
        val newPosition = Position(5, 7)
        val orientation = Orientation.NORTH
        val commands = mutableListOf(Command.FORWARD, Command.FORWARD)
        val plateauSize = PlateauSize(10, 10)
        val mower = Mower(position, orientation, commands, plateauSize)

        mower.startMovement()

        assertThat(mower, `is`(not(nullValue())))
        assertThat(mower.position.x, `is`(newPosition.x))
        assertThat(mower.position.y, `is`(newPosition.y))
        assertThat(mower.orientation, `is`(Orientation.NORTH))
    }

    @Test
    fun `should mower not go outside the limits of plateau`() {
        val position = Position(3, 4)
        val orientation = Orientation.NORTH
        val commands = mutableListOf(Command.FORWARD, Command.FORWARD, Command.FORWARD)
        val plateauSize = PlateauSize(5, 5)
        val limitPlateau = 5
        val mower = Mower(position, orientation, commands, plateauSize)

        mower.startMovement()

        assertThat(mower, `is`(not(nullValue())))
        assertThat(mower.position.x, `is`(position.x))
        assertThat(mower.position.y, `is`(limitPlateau))
    }

    @Test
    fun `should mower can execute all commands`() {
        val position = Position(5, 5)
        val orientation = Orientation.NORTH
        val commands = mutableListOf(Command.LEFT, Command.RIGHT, Command.LEFT, Command.FORWARD, Command.RIGHT)
        val plateauSize = PlateauSize(10, 10)
        val mower = Mower(position, orientation, commands, plateauSize)

        mower.startMovement()

        assertThat(mower, `is`(not(nullValue())))
        assertThat(mower.commands.size, `is`(0))
    }

}
