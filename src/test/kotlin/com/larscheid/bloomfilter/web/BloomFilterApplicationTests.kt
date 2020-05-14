package com.larscheid.bloomfilter.web

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class BloomFilterApplicationTests(@Autowired val restTemplate: TestRestTemplate) {

	@Test
	fun shouldReturn200WhenWordInDictionary() {
		val entity = restTemplate.getForEntity<String>("/dictionary/theOnlyWordOnTheList")
		assertEquals(entity.statusCode, HttpStatus.OK)
	}

	@Test
	fun shouldReturn404WhenWordNotInDictionary() {
		//in theory this test might fail due to a collision, but the probability is extremely low as the filter size
		//is 1MB and there is only one word in it
		val entity = restTemplate.getForEntity<String>("/dictionary/anyOtherDummyWord")
		assertEquals(entity.statusCode, HttpStatus.NOT_FOUND)
	}

}
