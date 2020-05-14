package com.larscheid.bloomfilter.hash

/**
 * Returns a hash function for a given name
 *
 * @throws UnknownHashException when no matching hash is found
 */
fun hashForName(name: String) : (String) -> Int = when (name) {
    "kotlin" -> ::kotlinDefaultHash
    else -> throw UnknownHashException("No implementation for hash $name found")
}

fun kotlinDefaultHash(word: String) : Int = word.hashCode()

class UnknownHashException(message: String) : Exception(message)