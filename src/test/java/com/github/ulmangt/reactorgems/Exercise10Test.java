package com.github.ulmangt.reactorgems;

import static com.github.ulmangt.reactorgems.Exercise10.createSlothSaysService;
import static com.github.ulmangt.reactorgems.Exercise10.useSlothSaysService;

import com.github.ulmangt.reactorgems.Exercise10.SlothSaysService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Exercise10Test extends TestCase
{
    public Exercise10Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise10Test.class );
    }

    public void testUseSlothSaysService1( )
    {
        StepVerifier.create( useSlothSaysService( createSlothSaysService( ), Flux.just( "Rawr", "Oink", "Mooo" ), "Flash" ) )
                .expectAccessibleContext( )
                .contains( SlothSaysService.SlothNameKey, "Flash" )
                .then( )
                .expectNext( "Flash says: Rawr" )
                .expectNext( "Flash says: Oink" )
                .expectNext( "Flash says: Mooo" )
                .verifyComplete( );
    }

    public void testUseSlothSaysService2( )
    {
        StepVerifier.create( useSlothSaysService( createSlothSaysService( ), Flux.just( "Touch Your Head" ), "Simon" ) )
                .expectAccessibleContext( )
                .contains( SlothSaysService.SlothNameKey, "Simon" )
                .then( )
                .expectNext( "Simon says: Touch Your Head" )
                .verifyComplete( );
    }
}
