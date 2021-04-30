package com.smart.mower.infrastructure.adapter.persistence

import com.smart.mower.domain.plateau.Plateau
import com.smart.mower.domain.port.PlateauRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class InMemoryPlateauRepository : PlateauRepository {

    private val plateaus: MutableMap<UUID, Plateau> = mutableMapOf()

    override fun save(plateau: Plateau) {
        plateaus[plateau.id.value] = plateau
    }

    override fun findById(id: UUID): Plateau? {
        return plateaus[id]
    }

}
