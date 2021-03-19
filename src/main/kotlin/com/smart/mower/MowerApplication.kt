package com.smart.mower

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MowerApplication

fun main(args: Array<String>) {
	runApplication<MowerApplication>(*args)
}
