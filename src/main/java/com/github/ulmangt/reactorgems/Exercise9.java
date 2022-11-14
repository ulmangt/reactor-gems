package com.github.ulmangt.reactorgems;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Exercise 9 - Element Indexes
 */
public class Exercise9
{
    /**
     * Return a Publisher which emits the third item in the provided animalNames Publisher
     * or "Sloth" if there are fewer than three items emitted.
     */
    public static Mono<String> findThirdItemOrSloth( Publisher<String> animalNames )
    {
        return null;
    }

    /**
     * Return a Publisher which emits every Nth element from the provided source.
     *
     * For example:
     *
     * If n == 0, no elements are returned.
     * If n == 1, every element from the provided source is returned.
     * If n == 2, every other element is returned.
     */
    public static <T> Publisher<T> emitEveryNthElement( Publisher<T> source, int n )
    {
        return null;
    }
}
