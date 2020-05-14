package com.larscheid.bloomfilter.web.api

import com.larscheid.bloomfilter.web.beans.FilterBean
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

/**
 * Spring Boot RestController hosting the spell checker API
 *
 * @property filterBean instance of [FilterBean] holding the bloom filter to check words
 * @constructor Creates the controller - called by Spring Boot
 */
@RestController
class SpellCheckerAPI(private val filterBean: FilterBean) {

    @GetMapping("/dictionary/{word}")
    fun checkSpelling(@PathVariable word: String) : String {
        if (filterBean.filter.contains(word)) {
            return "${word} is most likely in our dictionary!"
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "${word} is definitely not in our dictionary!")
        }
    }
}