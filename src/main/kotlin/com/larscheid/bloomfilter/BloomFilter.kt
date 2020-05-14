package com.larscheid.bloomfilter

class BloomFilter(private val bitArray: Array<Boolean>, private val hashFunction: (String) -> Int) {
    fun add(word: String) {
        bitArray[hashFunction(word) % bitArray.size] = true
    }

    fun contains(word: String) : Boolean = bitArray[hashFunction(word) % bitArray.size]
}