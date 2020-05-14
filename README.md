# Spell checker REST API
Simple spell checker REST API based on [bloom filters](https://en.wikipedia.org/wiki/Bloom_filter). Also, my attempt at [CodeKata05](http://codekata.com/kata/kata05-bloom-filters/).

# Usage
You can use the API under the path `/dictionary/{word}` to check the spelling of a word. The API returns `200 OK` for any word that is most likely in the dictionary and `404 NOT FOUND` for any word that is definitely not in the dictionary. Example:

```shell
> curl -i localhost:8080/dictionary/motorbike  
> HTTP/1.1 200
>
>
> curl -i localhost:8080/dictionary/imadethiswordupforyou
> HTTP/1.1 404 
``` 

# Running

With `.gradlew`:

```shell
> ./gradlew bootRun
```

Or with `docker`:
```shell
> ./docker build . -t spellchecker
> ./docker run -p 8080:8080 spellchecker
```

# Customization

Size of the bloom filter and desired hash algorithms are configured in [`src/main/resources/application.properties`](src/main/resources/application.properties),
but loaded via [spring externalized configuration](https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/boot-features-external-config.html), so override them with whatever method you prefer.

Currently, only kotlin's default hash function is available, additional hashing algorithms can be implemented in [`Hashes.kt`](src/main/kotlin/com/larscheid/bloomfilter/hash/Hashes.kt). Contributions are welcome!