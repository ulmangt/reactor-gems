package com.github.ulmangt.reactorgems;

import java.util.NoSuchElementException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Utilities
{
    /**
     * Emits true if the two input Flux emit the same number of values and each pair of
     * emitted values are equal.
     *
     * Will short circuit if any pair of values are not equal. Otherwise, must wait for
     * both Flux to complete.
     *
     * TODO Is there a cleaner way to accomplish this? Without the short circuiting something like
     *      {@code Arrays.equals( first.toStream( ).toArray( ), second.toStream( ).toArray( ) )} works.
     */
    public static <T> Mono<Boolean> compare( Flux<T> first, Flux<T> second )
    {
        // emits true if the two fluxes did not emit the same number of elements, nothing otherwise
        var notSameSize = Mono.zip( first.count( ), second.count( ) )
                .map( tuple -> tuple.getT1( ) != tuple.getT2( ) )
                .filter( sizeComparison -> sizeComparison );

        // emits true if any of the corresponding elements of emitted by the two fluxes are not equal, nothing otherwise
        var notSameContent = Flux.zip( first, second )
                .any( tuple -> !tuple.getT1( ).equals( tuple.getT2( ) ) )
                .filter( elementComparison -> elementComparison );

        return Mono.firstWithValue( notSameSize, notSameContent )
                .map( compareFailed -> !compareFailed )
                .onErrorResume( NoSuchElementException.class, throwable -> Mono.just( true ) );
    }

    public static <T> void assertEqual( boolean expected, Flux<T> first, Flux<T> second )
    {
        StepVerifier.create( Utilities.compare( first, second ) )
                .expectNext( expected )
                .verifyComplete( );
    }
}
