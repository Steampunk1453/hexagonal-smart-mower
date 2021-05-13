package com.smart.mower.presentation.rest

import com.smart.mower.domain.plateau.PlateauId
import com.smart.mower.presentation.rest.response.MowerResponse
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
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ExecuteMowerInstructionsControllerIT(@Autowired private val restTemplate: TestRestTemplate) {

    @Test
    fun `POST should execute instructions with CREATED (201) status code result`() {
        val plateauId = PlateauId(UUID.randomUUID()).value
        val headers = HttpHeaders()
        val entity = HttpEntity(null, headers)
        val response = restTemplate.exchange(
            "/mowers/execute/$plateauId",
            HttpMethod.POST,
            entity,
            MowerResponseList::class.java
        )

        response.statusCode shouldBe HttpStatus.OK
        response.body shouldNotBe null
    }

    private class MowerResponseList : MutableList<MowerResponse> by ArrayList()

}
