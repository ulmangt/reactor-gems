package com.github.ulmangt.reactorgems;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Exercise 8 - Implementing Mono.sequenceEqual
 */
public class Exercise8
{
    /**
     * {@code Mono.sequenceEqual} takes two Publishers and compares the elements emitted by
     * the two publishers pairwise.
     *
     * Re-implement this functionality without using Mono.sequenceEqual. The implementation should
     * short circuit immediately if a non-matching pair of elements is found. For example, an approach
     * like {@code Arrays.equals( first.toStream( ).toArray( ), second.toStream( ).toArray( ) )} would
     * not meet this short-circuit requirement.
     *
     * @see https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#sequenceEqual-org.reactivestreams.Publisher-org.reactivestreams.Publisher-
     */
    public static <T> Mono<Boolean> sequenceEqual( Flux<T> first, Flux<T> second )
    {
        return null;
    }
}
