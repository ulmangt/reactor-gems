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
        return null;
    }

    /**
     * Return a Publisher which emits the value from the provided animalName Mono after 10 seconds
     * if the value is "Sloth" or immediately if anything else.
     */
    public static Publisher<String> emitStringSlowlyOrQuickly( Mono<String> animalName )
    {
        return null;
    }

}
