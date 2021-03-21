package com.smart.mower.application.usecase

import com.smart.mower.domain.mower.Mower

class MoveMower {

    operator fun invoke(mower: Mower): Mower {
        mower.startMovement()
        return mower
    }

}

