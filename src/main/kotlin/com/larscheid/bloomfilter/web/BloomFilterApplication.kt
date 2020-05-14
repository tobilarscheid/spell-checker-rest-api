package com.larscheid.bloomfilter.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BloomFilterApplication

fun main(args: Array<String>) {
	runApplication<BloomFilterApplication>(*args)
}
