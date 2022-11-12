package com.github.ulmangt.reactorgems;

import java.time.Duration;
import java.util.Arrays;
import java.util.function.Function;

import org.reactivestreams.Publisher;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Exercise6Test extends TestCase
{
    public Exercise6Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise6Test.class );
    }

    public void testFlatMapUsingMerge1( )
    {
        flatMapUsingMergeHelper( Flux.just( "Sloth", "Cheetah", "Snail" ), animal -> Flux.fromArray( animal.split( "" ) ) );
    }

    public void testFlatMapUsingMerge2( )
    {
        flatMapUsingMergeHelper( Flux.empty( ), value -> Flux.empty( ) );
    }

    public void testFlatMapUsingMerge3( )
    {
        flatMapUsingMergeHelper( Flux.range( 0, 10 ), value -> Flux.range( 0, value ) );
    }

    protected <T, R> void flatMapUsingMergeHelper( Flux<T> source, Function<? super T, ? extends Publisher<? extends R>> mapper )
    {
        Flux<R> resultActual = Exercise6.flatMapUsingMerge( source, mapper );
        Flux<R> resultExpected = source.flatMap( mapper );

        Utilities.assertEqual( true, resultActual, resultExpected );
    }

    public void testMergeUsingFlatMap1( )
    {
        mergeUsingFlatMapHelper( Flux.just( Flux.just( "Sloth" ), Flux.just( "Cheetah" ), Flux.just( "Snail" ) ) );
    }

    public void testMergeUsingFlatMap2( )
    {
        mergeUsingFlatMapHelper( Flux.empty( ) );
    }

    public void testMergeUsingFlatMap3( )
    {
        mergeUsingFlatMapHelper( Flux.range( 0, 10 ).map( value -> Flux.range( 0, value ) ) );
    }

    protected <T> void mergeUsingFlatMapHelper( Publisher<? extends Publisher<? extends T>> source )
    {
        Flux<T> resultActual = Exercise6.mergeUsingFlatMap( source );
        Flux<T> resultExpected = Flux.merge( source );

        Utilities.assertEqual( true, resultActual, resultExpected );
    }

    public void testSwitchOnNextUsingMerge1( )
    {
        switchOnNextUsingMergeHelper(
                Flux.just(
                        Flux.just( "Sloth" ).delayElements( Duration.ofMillis( 1 ) ),
                        Flux.just( "Cheetah" ),
                        Flux.just( "Snail" )
                )
        );
    }

    public void testSwitchOnNextUsingMerge2( )
    {
        switchOnNextUsingMergeHelper( Flux.empty( ) );
    }

    public void testSwitchOnNextUsingMerge3( )
    {
        switchOnNextUsingMergeHelper(
                Flux.range( 0, 10 ).map(
                        value -> Flux.range( 0, value ).delayElements( Duration.ofMillis( 11 - value ) )
                )
        );
    }

    protected <T> void switchOnNextUsingMergeHelper( Publisher<? extends Publisher<? extends T>> source )
    {
        Flux<T> resultActual = Exercise6.switchOnNextUsingMerge( source );
        Flux<T> resultExpected = Flux.switchOnNext( source );

        Utilities.assertEqual( true, resultActual, resultExpected );
    }

}
