package com.github.ulmangt.reactorgems;

import java.time.Duration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Exercise1Test extends TestCase
{
    public Exercise1Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise1Test.class );
    }

    public void testEmitString( )
    {
        StepVerifier.create( Exercise1.emitString( ) )
                .expectNext( "Sloth" )
                .verifyComplete( );
    }

    public void testEmitRuntimeException( )
    {
        StepVerifier.create( Exercise1.emitRuntimeException( ) )
                .expectError( RuntimeException.class )
                .verify( );
    }
}
