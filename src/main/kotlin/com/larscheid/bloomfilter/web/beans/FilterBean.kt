package com.larscheid.bloomfilter.web.beans

import com.larscheid.bloomfilter.BloomFilter
import com.larscheid.bloomfilter.hash.hashForName
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component


/**
 * Singleton BloomFilter instance
 *
 * This class initializes a BloomFilter from the wordlist in
 * the resource directory and the parameters given in the config
 *
 * @property size the size of the underlying bit array of the bloom filter in KB, see [BloomFilter] for details
 * @property algorithm the hash algorithm to use in the filter. See [hashForName] for details
 * @constructor Creates a singleton bean holding the instance
 */
@Component
class FilterBean (@Value("\${size}") size: Int, @Value("\${algorithm}") algorithm: String) {
    val filter: BloomFilter = BloomFilter(Array(size * 1000, init = { _ -> false }), hashForName(algorithm))

    init {
        ClassPathResource("wordlist.txt").inputStream.bufferedReader().use { it.readLines().forEach{ word -> filter.add(word)} }
    }
}