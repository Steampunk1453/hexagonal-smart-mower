package com.smart.mower.application.usecase

import com.smart.mower.domain.mower.Command
import com.smart.mower.domain.mower.Mower
import com.smart.mower.domain.mower.Orientation
import com.smart.mower.domain.mower.Position
import com.smart.mower.domain.plateau.PlateauSize
import io.mockk.impl.annotations.InjectMockKs
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class MoveMowerTest {

    @InjectMockKs
    private lateinit var useCase: MoveMower

    @Test
    fun `should move a mower and return it`() {
        val position = Position(1, 2)
        val orientation = Orientation.NORTH
        val commands = mutableListOf(Command.LEFT, Command.RIGHT, Command.FORWARD)
        val plateauSize = PlateauSize(5, 5)
        val mower = Mower(position, orientation, commands, plateauSize)
        val x = 1
        val y = 3
        val width = 5
        val height = 5

        val result = useCase.invoke(mower)

        assertThat(result, `is`(not(nullValue())))
        assertThat(result.position.x, `is`(x))
        assertThat(result.position.y, `is`(y))
        assertThat(result.orientation, `is`(Orientation.NORTH))
        assertThat(result.plateauSize.width, `is`(width))
        assertThat(result.plateauSize.height, `is`(height))
    }

}
