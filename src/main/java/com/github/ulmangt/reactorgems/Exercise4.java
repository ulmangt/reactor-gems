package com.github.ulmangt.reactorgems;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;

/**
 * Exercise 4 - Filtering More Monos
 */
public class Exercise4
{
    public static final Logger logger = Logger.getLogger( Exercise4.class.getName( ) );

    /**
     * Return a Publisher which emits the value of the first Publisher int the animalNames
     * Collection which emits a value for which animalNameChecker returns a Mono which emits true.
     *
     * If none of the animalNames match the animalNameChecker, complete without emitting any name.
     *
     * For example, if Publishers in animalNames emit the following values in increasing time order...
     *
     * --- Cheetah -------------------
     * ------------- Sloth -----------
     * --------------------- Snail ---
     * ( increasing time --> )
     *
     * ...and animalNameChecker emits true for animalNames starting with "s" then we should emit "Sloth".
     *
     * @param animalNames a Collection of Publishers which emit animal names
     * @param animalNameChecker a Function which takes an animal name and returns a
     *                          Publisher which emits a boolean indicating whether the
     *                          animal name is valid
     */
    public static Publisher<String> emitFirstValidAnimalName(
            Collection<Mono<String>> animalNames,
            Function<String,Mono<Boolean>> animalNameChecker )
    {
        return null;
    }
}
