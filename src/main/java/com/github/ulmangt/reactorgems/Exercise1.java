package com.github.ulmangt.reactorgems;

import java.time.Duration;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;

/**
 * Exercise 1 - Creating Monos
 */
public class Exercise1
{
    /**
     * Return a Publisher which emits the string "Sloth".
     */
    public static Publisher<String> emitString( )
    {
        return Mono.just( "Sloth" );
    }

    /**
     * Return a Publisher which terminates with a RuntimeException without emitting any values.
     */
    public static Publisher<String> emitRuntimeException( )
    {
        return Mono.error( new RuntimeException( ) );
    }
}
