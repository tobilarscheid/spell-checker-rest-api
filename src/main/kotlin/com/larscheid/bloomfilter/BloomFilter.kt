package com.larscheid.bloomfilter

import kotlin.math.absoluteValue

/**
 * A naive implementation of a bloom filter.
 *
 * @property bitArray the array to use for storing the filters values
 * @property hashFunction the hash function to use when storing and checking values
 * @constructor Creates an empty bloom filter
 */
class BloomFilter(private val bitArray: Array<Boolean>, private val hashFunction: (String) -> Int) {

    /**
     * Adds a word to the filter by taking it's hash and setting the respective array bit
     */
    fun add(word: String) {
        bitArray[arrayIndex(word)] = true
    }

    /**
     * Checks the existence of a word in the filter by taking it's hash and returning the respective array bit
     */
    fun contains(word: String) = bitArray[arrayIndex(word)]

    private fun arrayIndex(word: String) = hashFunction(word).absoluteValue % bitArray.size
}