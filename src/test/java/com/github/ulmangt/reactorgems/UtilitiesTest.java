package com.github.ulmangt.reactorgems;

import java.time.Duration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Flux;

public class UtilitiesTest extends TestCase
{
    public UtilitiesTest( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise6Test.class );
    }

    public void testCompare1( )
    {
        Utilities.assertEqual( true, Flux.just( 1, 2 ), Flux.just( 1, 2 ) );
        Utilities.assertEqual( false, Flux.just( 1, 2 ), Flux.empty( ) );
        Utilities.assertEqual( true, Flux.empty( ), Flux.empty( ) );
        Utilities.assertEqual( false, Flux.just( 1L, 2L ), Flux.interval( Duration.ofMillis( 1 ) ) );
    }
}
