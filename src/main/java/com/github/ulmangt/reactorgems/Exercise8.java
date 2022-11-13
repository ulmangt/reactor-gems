package com.github.ulmangt.reactorgems;

import java.util.NoSuchElementException;
import java.util.function.BooleanSupplier;

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
        // emits true if the two fluxes did not emit the same number of elements, nothing otherwise
        var notSameSize = Mono.zip( first.count( ), second.count( ) )
                .map( tuple -> tuple.getT1( ) != tuple.getT2( ) );

        // emits true if any of the corresponding elements of emitted by the two fluxes are not equal, nothing otherwise
        var notSameContent = Flux.zip( first, second )
                .any( tuple -> !tuple.getT1( ).equals( tuple.getT2( ) ) );

        // firstWithValue allows us to short circuit the comparison if any pairwise values do not match
        // the map call flips the predicate, since we want to return false if either notSameContent or notSameSize return true
        return Mono.firstWithValue( notSameSize, notSameContent )
                .map( value -> !value );
    }
}
