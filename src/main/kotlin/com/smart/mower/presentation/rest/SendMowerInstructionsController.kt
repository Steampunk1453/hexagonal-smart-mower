package com.smart.mower.presentation.rest

import com.smart.mower.application.MowerOrchestrator
import com.smart.mower.presentation.rest.request.InstructionsRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class SendMowerInstructionsController(private val mowerOrchestrator: MowerOrchestrator) {

  @PostMapping("mowers/send")
  fun send(@RequestBody instructionsRequest: InstructionsRequest): ResponseEntity<UUID> {
    val plateau = mowerOrchestrator.handleInstructions(instructionsRequest.instructions)
    return ResponseEntity(plateau.id.value, HttpStatus.CREATED)
  }

}
