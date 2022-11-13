package com.github.ulmangt.reactorgems;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;

/**
 * Exercise 3 - Filtering Monos
 */
public class Exercise3
{
    /**
     * Return a Publisher which emits the value from the provided animalName Mono
     * if it is "Sloth" otherwise emits nothing.
     *
     * @param animalName the name of an animal
     */
    public static Publisher<String> emitSlothOnly( Mono<String> animalName )
    {
        return null;
    }
}
