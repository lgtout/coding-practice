package com.lagostout.elementsofprogramminginterviews.hashtables

import spock.lang.Specification
import spock.lang.Unroll

class ISBNCacheSpec extends Specification {

    @Unroll
    'puts book into cache when book not already present'() {

        when:
        def cache = new ISBNCache(3)

        def isbn = "123"
        def price = 10
        cache.put(isbn, price)

        isbn = "324"
        price = 20
        cache.put(isbn, price)

        then:
        def map = cache.map
        map.containsKey(isbn) && map.get(isbn) == price
        
    }

    @Unroll
    'does not change book price when book put into cache is already present'() {

        when:
        def cache = new ISBNCache(3)

        def isbn = "123"
        def price = 10
        cache.put(isbn, price)

        def newPrice = 20
        cache.put(isbn, newPrice)

        then:
        cache.map.get(isbn) == price

    }

    @Unroll
    'updates book to most recently used when book put into cache is already present'() {

        when:
        def cache = new ISBNCache(3)
        def isbn = "234"
        def price = 20
        cache.put(isbn, price)
        cache.put("123", 10)
        cache.put(isbn, 30)

        then:
        cache.map.keySet().toList().last() == "234"

    }

    @Unroll
    'updates book to most recently used when book is looked up in cache'() {

        when:
        def cache = new ISBNCache(3)
        def isbn = "234"
        def price = 20
        cache.put(isbn, price)
        cache.put("123", 10)
        cache.get(isbn)

        then:
        cache.map.keySet().last() == "234"

    }

    @Unroll
    'removes LRU book when book is added when cache is full'() {

        when:
        def cache = new ISBNCache(3)
        cache.put("123", 10)
        cache.put("234", 20)
        cache.put("345", 30)
        cache.get("123")
        cache.put("456", 40)

        then:
        cache.map.keySet().asList() == ["345", "123", "456"]

    }

    @Unroll
    'looks up the price of a book'() {

        when:
        def cache = new ISBNCache(3)
        def isbn = "234"
        def price = 20
        cache.put(isbn, price)
        cache.put("123", 10)

        then:
        cache.get(isbn) == price

    }

    @Unroll
    'removes book from cache'() {

        when:
        def cache = new ISBNCache(3)
        def isbn = "234"
        cache.put(isbn, 30)
        cache.put("123", 10)
        cache.remove(isbn)

        then:
        cache.get(isbn) == null
        cache.map.size() == 1

    }

}
