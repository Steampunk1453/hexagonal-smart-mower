package com.smart.mower.presentation.rest

import com.smart.mower.presentation.rest.request.InstructionsRequest
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class SendMowerInstructionsControllerIT(@Autowired private val restTemplate: TestRestTemplate) {

    @Test
    fun `POST should send instructions with CREATED (201) status code result`() {
        val instructionsRequest = InstructionsRequest("5 5,1 2 N,LMLMLMLMM,3 3 E,MMRMMRMRRM")
        val headers = HttpHeaders()
        val entity = HttpEntity(instructionsRequest, headers)
        val response = restTemplate.exchange(
            "/mowers/send",
            HttpMethod.POST,
            entity,
            String::class.java
        )

        response.statusCode shouldBe HttpStatus.CREATED
        response.body shouldNotBe null
    }

}
