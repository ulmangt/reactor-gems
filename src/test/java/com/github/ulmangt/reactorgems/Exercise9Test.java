package com.github.ulmangt.reactorgems;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Exercise9Test extends TestCase
{
    public Exercise9Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise9Test.class );
    }

    public void testFindThirdItemOrSloth1( )
    {
        StepVerifier.create( Exercise9.findThirdItemOrSloth( Flux.just( "Bear", "Panda", "Cheetah", "Pig" ) ) )
                .expectNext( "Cheetah" )
                .verifyComplete( );
    }

    public void testFindThirdItemOrSloth2( )
    {
        StepVerifier.create( Exercise9.findThirdItemOrSloth( Flux.just( "Bear" ) ) )
                .expectNext( "Sloth" )
                .verifyComplete( );
    }

    public void testFindThirdItemOrSloth3( )
    {
        StepVerifier.create( Exercise9.findThirdItemOrSloth( Mono.just( "Bear" ) ) )
                .expectNext( "Sloth" )
                .verifyComplete( );
    }

    public void testFindThirdItemOrSloth4( )
    {
        StepVerifier.create( Exercise9.findThirdItemOrSloth( Flux.merge( Flux.just( "Bear", "Panda", "Cheetah", "Pig" ), Mono.error( new RuntimeException( ) ) ) ) )
                .expectNext( "Cheetah" )
                .verifyComplete( );
    }

    public void testFindThirdItemOrSloth5( )
    {
        StepVerifier.create( Exercise9.findThirdItemOrSloth( Mono.error( new RuntimeException( ) ) ) )
                .verifyError( RuntimeException.class );
    }

    public void testEmitEveryNthElement1( )
    {
        StepVerifier.create( Exercise9.emitEveryNthElement( Flux.range( 1, 10 ), 3 ) )
                .expectNext( 3, 6, 9 )
                .verifyComplete( );
    }

    public void testEmitEveryNthElement2( )
    {
        StepVerifier.create( Exercise9.emitEveryNthElement( Flux.range( 1, 2 ), 3 ) )
                .expectNextCount( 0 )
                .verifyComplete( );
    }

    public void testEmitEveryNthElement3( )
    {
        StepVerifier.create( Exercise9.emitEveryNthElement( Flux.range( 1, 2 ), 0 ) )
                .verifyComplete( );
    }

    public void testEmitEveryNthElement4( )
    {
        StepVerifier.create( Exercise9.emitEveryNthElement( Flux.range( 1, 3 ), 1 ) )
                .expectNext( 1, 2, 3 )
                .verifyComplete( );
    }

    public void testEmitEveryNthElement5( )
    {
        StepVerifier.create( Exercise9.emitEveryNthElement( Flux.empty( ), 1 ) )
                .expectNextCount( 0 )
                .verifyComplete( );
    }
}
