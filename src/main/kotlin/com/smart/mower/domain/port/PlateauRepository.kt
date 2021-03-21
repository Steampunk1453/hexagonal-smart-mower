package com.smart.mower.domain.port

import com.smart.mower.domain.plateau.Plateau
import java.util.UUID

interface PlateauRepository {
    fun save(plateau: Plateau)
    fun findById(id: UUID): Plateau?
}
