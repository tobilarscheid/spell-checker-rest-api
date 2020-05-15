package com.larscheid.bloomfilter.hash

import java.nio.ByteBuffer
import java.security.MessageDigest

/**
 * Returns a hash function for a given name
 *
 * @throws UnknownHashException when no matching hash is found
 */
fun hashForName(name: String) : (String) -> Int = when (name) {
    "kotlin" -> ::kotlinDefaultHash
    "md5" -> ::md5Hash
    else -> throw UnknownHashException("No implementation for hash $name found - please check your configuration")
}

fun kotlinDefaultHash(word: String) : Int = word.hashCode()

fun md5Hash(word: String) : Int {
   val hashBytes = MessageDigest.getInstance("MD5").digest(word.toByteArray())
    return ByteBuffer.wrap(hashBytes).int
}

class UnknownHashException(message: String) : Exception(message)