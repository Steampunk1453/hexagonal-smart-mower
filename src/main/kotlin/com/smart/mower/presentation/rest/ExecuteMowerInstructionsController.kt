package com.smart.mower.presentation.rest

import com.smart.mower.application.MowerOrchestrator
import com.smart.mower.presentation.rest.response.MowerResponse
import com.smart.mower.presentation.rest.response.toResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ExecuteMowerInstructionsController(private val mowerOrchestrator: MowerOrchestrator) {

  @PostMapping("mowers/execute/{plateauId}")
  fun execute(@PathVariable("plateauId") plateauId: UUID): List<MowerResponse> {
    val mowers = mowerOrchestrator.executeInstructions(plateauId)
    return mowers.map { it.toResponse() }
  }

}
