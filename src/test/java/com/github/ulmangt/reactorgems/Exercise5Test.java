package com.github.ulmangt.reactorgems;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Exercise5Test extends TestCase
{
    public Exercise5Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise5Test.class );
    }

    public void testSayHello1( )
    {
        StepVerifier.create( Exercise5.sayHello( Flux.empty( ) ) )
                .verifyComplete( );
    }

    public void testSayHello2( )
    {
        StepVerifier.create( Exercise5.sayHello( Flux.just( "Sloth", "Cheetah" ) ) )
                .expectNext( "Hello Sloth" )
                .expectNext( "Hello Cheetah" )
                .verifyComplete( );
    }
}
