package com.smart.mower.infrastructure.configuration

import com.smart.mower.application.MowerOrchestrator
import com.smart.mower.application.service.InstructionValidatorService
import com.smart.mower.application.usecase.CreateMower
import com.smart.mower.application.usecase.CreatePlateau
import com.smart.mower.application.usecase.MoveMower
import com.smart.mower.domain.port.PlateauRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeansConfig {

    @Autowired
    lateinit var plateauRepository: PlateauRepository

    @Bean
    fun getInstructionValidatorService(): InstructionValidatorService {
        return InstructionValidatorService()
    }

    @Bean
    fun getCreatePlateau(): CreatePlateau {
        return CreatePlateau(getInstructionValidatorService())
    }

    @Bean
    fun getCreateMower(): CreateMower {
        return CreateMower(getInstructionValidatorService())
    }

    @Bean
    fun getMoveMower(): MoveMower {
        return MoveMower()
    }

    @Bean
    fun mowerOrchestrator(): MowerOrchestrator {
        return MowerOrchestrator(getCreatePlateau(), getCreateMower(),getMoveMower(),  plateauRepository)
    }

}
