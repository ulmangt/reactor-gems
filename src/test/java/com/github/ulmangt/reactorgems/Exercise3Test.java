package com.github.ulmangt.reactorgems;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Exercise3Test extends TestCase
{
    public Exercise3Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise3Test.class );
    }

    public void testEmitSlothOnly1( )
    {
        StepVerifier.create( Exercise3.emitSlothOnly( Mono.just( "Sloth" ) ) )
                .expectNext( "Sloth" )
                .verifyComplete( );
    }

    public void testEmitSlothOnly2( )
    {
        StepVerifier.create( Exercise3.emitSlothOnly( Mono.just( "Cheetah" ) ) )
                .verifyComplete( );
    }
}
