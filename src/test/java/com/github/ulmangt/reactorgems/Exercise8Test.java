package com.github.ulmangt.reactorgems;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Predicate;

import org.reactivestreams.Publisher;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Exercise8Test extends TestCase
{
    public Exercise8Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise8Test.class );
    }

    public void testSequenceEqual1( )
    {
        assertEquals( Boolean.TRUE, Exercise8.sequenceEqual( Flux.just( 1, 2 ), Flux.just( 1, 2 ) ).block( ) );
    }

    public void testSequenceEqual2( )
    {
        assertEquals( Boolean.FALSE, Exercise8.sequenceEqual( Flux.just( 1, 2 ), Flux.empty( ) ).block( ) );
    }

    public void testSequenceEqual3( )
    {
        assertEquals( Boolean.TRUE, Exercise8.sequenceEqual( Flux.empty( ), Flux.empty( ) ).block( ) );
    }

    public void testSequenceEqual4( )
    {
        assertEquals( Boolean.FALSE, Exercise8.sequenceEqual( Flux.just( 1L, 2L ), Flux.interval( Duration.ofMillis( 1 ) ) ).block( ) );
    }

    public void testSequenceEqual5( )
    {
        assertEquals( Boolean.FALSE, Exercise8.sequenceEqual( Flux.just( 1, 2 ), Flux.just( 1, 2, 3 ) ).block( ) );
    }
}
