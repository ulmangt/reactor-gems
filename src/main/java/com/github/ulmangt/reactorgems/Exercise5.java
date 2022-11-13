package com.github.ulmangt.reactorgems;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;

/**
 * Exercise 4 - Transforming Fluxes
 */
public class Exercise5
{
    /**
     * Return a Publisher which appends "Hello " to each string emitted by animalNames.
     *
     * For example, "Sloth" should be transformed to "Hello Sloth".
     *
     * Instead of using {@code map}
     */
    public static Publisher<String> sayHello( Flux<String> animalNames )
    {
        return null;
    }
}
