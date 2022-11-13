package com.github.ulmangt.reactorgems;

import java.util.function.Function;
import java.util.function.Predicate;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;

/**
 * Exercise 4 - Relationships between operators
 *
 * Some of these exercises (as well as inspiration for the name of this project) are based on the
 * excellent discussion here: https://github.com/spring-attic/reactive-streams-commons/issues/21
 */
public class Exercise6
{
    /**
     * Implement {@code map} using {@code flatMap}.
     *
     * @param <T> The input data type of the Flux to be transformed
     * @param <R> The type to transform the input Flux into
     * @param source The source Flux to be transformed
     * @param mapper The function mapping from T to R
     *
     * Calling {@code mapUsingFlatMap( source, mapper )} should return the same sequence {@code source.map( mapper)}
     * but implemented calling {@code flatMap} instead of {@code map}.
     *
     * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#flatMap-java.util.function.Function-">flatMap</a>
     */
    public static <T,R> Flux<R> mapUsingFlatMap( Flux<T> source, Function<? super T, ? extends R> mapper )
    {
        return null;
    }

    /**
     * Implement {@code filter} using {@code flatMap}.
     *
     * Calling {@code filterUsingFlatMap( source, mapper )} should return the same sequence {@code source.filter( mapper)}
     * but implemented calling {@code flatMap} instead of {@code filter}.
     */
    public static <T> Flux<T> filterUsingFlatMap( Flux<T> source, Predicate<? super T> predicate )
    {
        return null;
    }

    /**
     * Implement {@code concatMap} using {@code flatMap}.
     *
     * Calling {@code concatMapUsingFlatMap( source, mapper )} should return the same sequence {@code source.concatMap( mapper)}
     * but implemented calling {@code flatMap} instead of {@code concatMap}.
     */
    public static <T,R> Flux<R> concatMapUsingFlatMap( Flux<T> source, Function<? super T,? extends Publisher<? extends R>> mapper )
    {
        return null;
    }

    /**
     * Implement {@code flatMap} using {@code merge} and {@code map}.
     *
     * Calling {@code flatMapUsingMerge( source, mapper )} should return the same sequence {@code source.flatMap( mapper)}
     * but implemented calling {@code merge} instead of {@code flatMap}.
     *
     * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#merge-org.reactivestreams.Publisher-">merge</a>
     */
    public static <T,R> Flux<R> flatMapUsingMerge( Flux<T> source, Function<? super T,? extends Publisher<? extends R>> mapper )
    {
        return null;
    }

    /**
     * Now do the opposite of {@link #flatMapUsingMerge(Flux, Function)}. Implement {@code merge} using {@code flatMap}.
     */
    public static <T> Flux<T> mergeUsingFlatMap( Publisher<? extends Publisher<? extends T>> source )
    {
        return null;
    }

    /**
     * Follow a similar pattern as above to implement {@code Flux.switchOnNext} using {@code Flux.switchMap} and {@code Flux.merge}.
     */
    public static <T> Flux<T> switchOnNextUsingMerge( Publisher<? extends Publisher<? extends T>> mergedPublishers )
    {
        return null;
    }
}
