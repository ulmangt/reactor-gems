package com.github.ulmangt.reactorgems;

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
        flatMapUsingMergeHelper( Flux.empty( ), value -> Flux.empty( ) );
        flatMapUsingMergeHelper( Flux.range( 0, 10 ), value -> Flux.range( 0, value ) );
    }

    protected <T,R> void flatMapUsingMergeHelper( Flux<T> source, Function<? super T,? extends Publisher<? extends R>> mapper )
    {
        Flux<R> resultActual = Exercise6.flatMapUsingMerge( source, mapper );
        Flux<R> resultExpected = source.flatMap( mapper );

        Utilities.assertEqual( true, resultActual, resultExpected );
    }

}
