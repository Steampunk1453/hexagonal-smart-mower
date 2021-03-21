package com.smart.mower.presentation.rest.response

import com.smart.mower.domain.mower.Mower

data class MowerResponse(val x: Int, val y: Int, val orientation: String)

fun Mower.toResponse(): MowerResponse = MowerResponse(position.x, position.y, orientation.name[0].toString())
