package com.github.ulmangt.reactorgems;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Exercise4Test extends TestCase
{
    public Exercise4Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise4Test.class );
    }

    public void testEmitFirstValidAnimalName1( )
    {
        var animalNames = List.of(
                Mono.just( "Cheetah" ),
                Mono.just( "Sloth" ).delayElement( Duration.ofMillis( 1 ) ),
                Mono.just( "Snail" ).delayElement( Duration.ofMillis( 10 ) )
        );

        Function<String, Mono<Boolean>> animalNameChecker = ( name ) -> Mono.just( name.startsWith( "S" ) );

        StepVerifier.withVirtualTime( ( ) -> Exercise4.emitFirstValidAnimalName( animalNames, animalNameChecker ) )
                .expectSubscription( )
                .expectNoEvent( Duration.ofMillis( 1 ) )
                .expectNext( "Sloth" )
                .verifyComplete( );
    }

    public void testEmitFirstValidAnimalName2( )
    {
        var animalNames = List.of(
                Mono.just( "Speedy Snail" ).delayElement( Duration.ofMillis( 5 ) ),
                Mono.just( "Slow Cheetah" ).delayElement( Duration.ofMillis( 2 ) ),
                Mono.just( "Fast Sloth" ).delayElement( Duration.ofMillis( 1 ) )
        );

        Function<String, Mono<Boolean>> animalNameChecker = ( name ) -> Mono.just( name.startsWith( "S" ) );

        StepVerifier.withVirtualTime( ( ) -> Exercise4.emitFirstValidAnimalName( animalNames, animalNameChecker ) )
                .expectSubscription( )
                .expectNoEvent( Duration.ofMillis( 2 ) )
                .expectNext( "Slow Cheetah" )
                .verifyComplete( );
    }

    public void testEmitFirstValidAnimalName3( )
    {
         Collection<Mono<String>> animalNames = List.of(
                Mono.just( "Flash Flash Hundred Yard Dash" ),
                Mono.empty( )
        );

        Function<String, Mono<Boolean>> animalNameChecker = ( name ) -> Mono.just( name.startsWith( "S" ) );

        StepVerifier.withVirtualTime( ( ) -> Exercise4.emitFirstValidAnimalName( animalNames, animalNameChecker ) )
                .verifyComplete( );
    }

    public void testEmitFirstValidAnimalName4( )
    {
        Collection<Mono<String>> animalNames = List.of(
                Mono.error( new RuntimeException( ) )
        );

        Function<String, Mono<Boolean>> animalNameChecker = ( name ) -> Mono.just( name.startsWith( "S" ) );

        StepVerifier.withVirtualTime( ( ) -> Exercise4.emitFirstValidAnimalName( animalNames, animalNameChecker ) )
                .verifyError( RuntimeException.class );
    }
}
