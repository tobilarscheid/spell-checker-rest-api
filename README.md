# Spell checker REST API
Simple spell checker REST API based on [bloom filters](https://en.wikipedia.org/wiki/Bloom_filter). Also, my attempt at [CodeKata05](http://codekata.com/kata/kata05-bloom-filters/).

# Usage
The API is under `/dictionary/{word}`

It returns:

| Return code        | Meaning           |
| ------------- |-------------|
| ![200 OK](https://img.shields.io/badge/200-OK-green) | `{word}` is most likely in the dictionary |
| ![404 Not Found](https://img.shields.io/badge/404-Not%20Found-red) | `{word}` is definitely not in the dictionary |

Examples:
```shell
> curl -i localhost:8080/dictionary/motorbike  
> HTTP/1.1 200
>
>
> curl -i localhost:8080/dictionary/imadethiswordupforyou
> HTTP/1.1 404 
``` 

# Running

With `gradlew`:

```shell
> ./gradlew bootRun
```

Or with `docker`:
```shell
> ./docker build . -t spellchecker
> ./docker run -p 8080:8080 spellchecker
```

# Customization

| Customizable value        | Default           | Customize in...  |Valid values|
| ------------- |-------------|-----|---|
| *Size* of the bloom filter in KB | 1000 | [application.properties](src/main/resources/application.properties) |1 - `Ìnt.MAX_VALUE`|
| *Hash algorithm* to use     | `md5`      |   [application.properties](src/main/resources/application.properties) |`md5`, `kotlin` - additional values can be implemented in [`Hashes.kt`](src/main/kotlin/com/larscheid/bloomfilter/hash/Hashes.kt)|
| Wordlist     | [http://codekata.com/data/wordlist.txt](http://codekata.com/data/wordlist.txt)      |   [wordlist.txt](src/main/resources/wordlist.txt) |One word per line|