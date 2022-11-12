package com.github.ulmangt.reactorgems;

import java.time.Duration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Exercise2Test extends TestCase
{
    public Exercise2Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise2Test.class );
    }

    public void testEmitStringSlowly( )
    {
        StepVerifier.withVirtualTime( ( )->Exercise2.emitStringSlowly( Mono.just( "Sloth" ) ) )
                .expectSubscription( )
                .expectNoEvent( Duration.ofSeconds( 10 ) )
                .expectNext( "Sloth" )
                .expectComplete( )
                .verify( );
    }

    public void testEmitStringSlowlyOrQuickly1( )
    {
        StepVerifier.withVirtualTime( ( )->Exercise2.emitStringSlowlyOrQuickly( Mono.just( "Sloth" ) ) )
                .expectSubscription( )
                .expectNoEvent( Duration.ofSeconds( 10 ) )
                .expectNext( "Sloth" )
                .expectComplete( )
                .verify( );
    }

    public void testEmitStringSlowlyOrQuickly2( )
    {
        StepVerifier.withVirtualTime( ( )->Exercise2.emitStringSlowlyOrQuickly( Mono.just( "Cheetah" ) ) )
                .expectNext( "Cheetah" )
                .expectComplete( )
                //TODO is there a more explicit way to verify immediate completion?
                .verify( Duration.ofNanos( 1 ) );
    }
}
