package com.larscheid.bloomfilter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BloomFilterTest {
    @Test
    fun shouldAddSuccessful() {
        //given an array full of 0s and a hash function that always returns 1
        val bitArray = arrayOf(false, false)
        val filter = BloomFilter(bitArray) { _ -> 1}
        //when I add any word
        filter.add("any-word")
        //then position one in the array should be 1
        assertArrayEquals(bitArray, arrayOf(false, true))
    }

    @Test
    fun shouldRespectBoundariesWhenAdding() {
        //given an array full of 0s and a hash function that returns a hash bigger then the array size
        val bitArray = arrayOf(false, false)
        val filter = BloomFilter(bitArray) { _ -> 5} //5 is higher than array size
        //when I add any word
        filter.add("any-word")
        //then the hash should be modulated to the array size (5 % 2 = 1)
        assertArrayEquals(bitArray, arrayOf(false, true))
    }

    @Test
    fun shouldHandleCollisionsWhenAdding() {
        //given an array full of 0s and a hash function that always returns 1
        val bitArray = arrayOf(false, false)
        val filter = BloomFilter(bitArray) { _ -> 1}
        //when I add the same word multiple times
        filter.add("any-word")
        filter.add("any-word")
        filter.add("any-word")
        //then the array should correctly contain a 1 at position one and no errors should occur
        assertArrayEquals(bitArray, arrayOf(false, true))
    }

    @Test
    fun shouldReturnTrueWhenWordIsInArray() {
        //given an array containing a 1 at position one and a hash function that always returns 1
        val bitArray = arrayOf(false, true)
        val filter = BloomFilter(bitArray) { _ -> 1}
        //when I check for a word
        val result = filter.contains("any-word")
        //then the result should be true
        assertTrue(result)
    }

    @Test
    fun shouldReturnFalseWhenWordIsNotInArray() {
        //given an array containing a 1 at position one and a hash function that always returns 0
        val bitArray = arrayOf(false, true)
        val filter = BloomFilter(bitArray) { _ -> 0}
        //when I check for a word
        val result = filter.contains("any-word")
        //then the result should be false
        assertFalse(result)
    }
}