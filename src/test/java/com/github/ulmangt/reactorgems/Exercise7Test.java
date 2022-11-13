package com.github.ulmangt.reactorgems;

import static reactor.core.publisher.Mono.just;

import java.time.Duration;

import com.github.ulmangt.reactorgems.Exercise7.Node;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Exercise7Test extends TestCase
{
    public Exercise7Test( String testName )
    {
        super( testName );
    }

    public static Test suite( )
    {
        return new TestSuite( Exercise7Test.class );
    }

    // Tree Structure:
    //
    //            9
    //      5          13
    //   2     6    10    20
    //
    public static Node tree1 = new Node( 9, new Node( 5, new Node( 2 ), new Node( 6 ) ), new Node( 13, new Node( 10 ), new Node( 20 ) ) );
    public static Flux<Integer> preOrderTree1 = Flux.just( 9, 5, 2, 6, 13, 10, 20 );
    public static Flux<Integer> inOrderTree1 = Flux.just( 2, 5, 6, 9, 10, 13, 20 );

    // Tree Structure:
    //
    //             5
    //          4     6
    //       3
    //    2
    // 1
    //
    public static Node tree2 = new Node( 5, new Node( 4, new Node( 3, new Node( 2, new Node( 1 ), null ), null ), null ), new Node( 6 ) );
    public static Flux<Integer> preOrderTree2 = Flux.just( 5, 4, 3, 2, 1, 6 );
    public static Flux<Integer> inOrderTree2 = Flux.just( 1, 2, 3, 4, 5, 6 );

    // Tree Structure:
    //
    //                50
    //            40      60
    //       30
    //    20    35
    // 10           39
    //            37
    //
    public static Node tree3 =
            new Node( 50,
                    new Node( 40,
                            new Node( 30,
                                    new Node( 20, new Node( 10 ), null ),
                                    new Node( 35,
                                            null,
                                            new Node( 39, new Node( 37 ), null )
                                    )
                            ),
                            null ),
                    new Node( 60 ) );
    public static Flux<Integer> preOrderTree3 = Flux.just( 50, 40, 30, 20, 10, 35, 39, 37, 60 );
    public static Flux<Integer> inOrderTree3 = Flux.just( 10, 20, 30, 35, 37, 39, 40, 50, 60 );

    public void testPreOrder1( )
    {
        Utilities.assertEqual( Boolean.TRUE, Exercise7.emitPreOrder( just( tree1 ) ), preOrderTree1 );
    }

    public void testPreOrder2( )
    {
        Utilities.assertEqual( Boolean.TRUE, Exercise7.emitPreOrder( just( tree2 ) ), preOrderTree2 );
    }

    public void testPreOrder3( )
    {
        Utilities.assertEqual( Boolean.TRUE, Exercise7.emitPreOrder( just( tree3 ) ), preOrderTree3 );
    }

    public void testPreOrder4( )
    {
        Utilities.assertEqual( Boolean.TRUE, Exercise7.emitPreOrder( just( new Node<>( 10 ) ) ), Flux.just( 10 ) );
    }

    public void testInOrder1( )
    {
        Utilities.assertEqual( Boolean.TRUE, Exercise7.emitInOrder( just( tree1 ) ), inOrderTree1 );
    }

    public void testInOrder2( )
    {
        Utilities.assertEqual( Boolean.TRUE, Exercise7.emitInOrder( just( tree2 ) ), inOrderTree2 );
    }

    public void testInOrder3( )
    {
        Utilities.assertEqual( Boolean.TRUE, Exercise7.emitInOrder( just( tree3 ) ), inOrderTree3 );
    }

    public void testInOrder4( )
    {
        Utilities.assertEqual( Boolean.TRUE, Exercise7.emitInOrder( just( new Node<>( 10 ) ) ), Flux.just( 10 ) );
    }
}
