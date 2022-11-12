package com.github.ulmangt.reactorgems;

import java.util.IdentityHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Exercise 4 - Relationships between operators
 *
 * See https://github.com/spring-attic/reactive-streams-commons/issues/21 for further discussion.
 */
public class Exercise6
{
    /**
     * Implement {@code map} using {@code flatMap}.
     *
     * Calling {@code mapUsingFlatMap( source, mapper )} should return the same sequence {@code source.map( mapper)}
     * but implemented calling {@code flatMap} instead of {@code map}.
     */
    public static <T,R> Flux<R> mapUsingFlatMap( Flux<T> source, Function<? super T, ? extends R> mapper )
    {
        return source.flatMap( value -> Flux.just( mapper.apply( value ) ) );
    }

    /**
     * Implement {@code filter} using {@code flatMap}.
     *
     * Calling {@code filterUsingFlatMap( source, mapper )} should return the same sequence {@code source.filter( mapper)}
     * but implemented calling {@code flatMap} instead of {@code filter}.
     */
    public static <T> Flux<T> filterUsingFlatMap( Flux<T> source, Predicate<? super T> predicate )
    {
        return source.flatMap( value -> predicate.test( value ) ? Flux.just( value ) : Flux.empty( ) );
    }

    /**
     * Implement {@code concatMap} using {@code flatMap}.
     *
     * Calling {@code concatMapUsingFlatMap( source, mapper )} should return the same sequence {@code source.concatMap( mapper)}
     * but implemented calling {@code flatMap} instead of {@code concatMap}.
     */
    public static <T,R> Flux<R> concatMapUsingFlatMap( Flux<T> source, Function<? super T,? extends Publisher<? extends R>> mapper )
    {
        return source.flatMap( mapper, 1 );
    }

    /**
     * Implement {@code flatMap} using {@code merge} and {@code map}.
     *
     * Calling {@code flatMapUsingMerge( source, mapper )} should return the same sequence {@code source.flatMap( mapper)}
     * but implemented calling {@code merge} instead of {@code flatMap}.
     *
     * @param <T> The input data type of the Flux to be transformed
     * @param <R> The type to transform the input Flux into
     * @param source The source Flux to be transformed
     * @param mapper The function mapping from T to R
     *
     * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#flatMap-java.util.function.Function-">flatMap</a>
     * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#merge-org.reactivestreams.Publisher-">merge</a>
     */
    public static <T,R> Flux<R> flatMapUsingMerge( Flux<T> source, Function<? super T,? extends Publisher<? extends R>> mapper )
    {
        return Flux.merge( source.map( mapper ) );
    }

    /**
     * Now do the opposite of {@link #flatMapUsingMerge(Flux, Function)}. Implement {@code merge} using {@code flatMap}.
     */
    public static <T> Flux<T> mergeUsingFlatMap( Publisher<? extends Publisher<? extends T>> source )
    {
        return Flux.from( source ).flatMap( Function.identity( ) );
    }

    /**
     * Follow a similar pattern as above to implement {@code Flux.switchOnNext} using {@code Flux.switchMap} and {@code Flux.merge}.
     */
    public static <T> Flux<T> switchOnNextUsingMerge( Publisher<? extends Publisher<? extends T>> mergedPublishers )
    {
        return Flux.merge( Flux.from( mergedPublishers ).switchMap( Function.identity( ) ) );
    }
}
