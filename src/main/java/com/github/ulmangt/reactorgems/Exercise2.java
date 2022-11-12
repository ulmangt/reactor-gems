package com.github.ulmangt.reactorgems;

import java.time.Duration;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;

/**
 * Exercise 2 - Delaying Monos
 */
public class Exercise2
{
    /**
     * Return a Publisher which emits the value from the provided slothName Mono after 10 seconds.
     */
    public static Publisher<String> emitStringSlowly( Mono<String> slothName )
    {
        return slothName.delayElement( Duration.ofSeconds( 10 ) );
    }

    /**
     * Return a Publisher which emits the value from the provided animalName Mono after 10 seconds
     * if the value is "Sloth" or immediately if anything else.
     */
    public static Publisher<String> emitStringSlowlyOrQuickly( Mono<String> animalName )
    {
        return animalName.flatMap( name -> name.equals( "Sloth" ) ?
                Mono.just( "Sloth" ).delayElement( Duration.ofSeconds( 10 ) ) :
                Mono.just( name ) );
    }

}
