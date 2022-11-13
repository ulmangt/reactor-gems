package com.github.ulmangt.reactorgems;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Utilities
{
    public static <T> void assertEqual( boolean expected, Publisher<T> first, Publisher<T> second )
    {
        StepVerifier.create( Mono.sequenceEqual( Flux.from( first ), Flux.from( second ) ) )
                .expectNext( expected )
                .verifyComplete( );
    }
}
