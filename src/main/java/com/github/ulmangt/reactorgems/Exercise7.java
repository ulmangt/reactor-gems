package com.github.ulmangt.reactorgems;

import static reactor.core.publisher.Flux.just;
import static reactor.core.publisher.Flux.merge;
import static reactor.core.publisher.Mono.justOrEmpty;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Exercise7
{
    /**
     * A simple binary tree node.
     */
    public static class Node<V>
    {
        public final V value;
        public final Node<V> left;
        public final Node<V>  right;
        public Node( V value, Node<V>  left, Node<V> right )
        {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        public Node( V value )
        {
            this( value, null, null );
        }
    }

    /**
     * Emit the values in the tree rooted at the provided node in a pre-order (depth first) traversal.
     *
     * Consider the following example tree:
     *
     * <pre>
     *            9
     *      5          13
     *   2     6    10    20
     * </pre>
     *
     * This can be constructed with the above Node class in the following way:
     *
     * {@code new Node( 9, new Node( 5, new Node( 2 ), new Node( 6 ) ), new Node( 13, new Node( 10 ), new Node( 20 ) ) )}
     *
     * When passed the root node, emitPreOrder should emit: 9, 5, 2, 6, 13, 10, 20
     */
    public static <V> Publisher<V> emitPreOrder( Node<V> root )
    {
        return just( root )
                .expandDeep( node -> merge( justOrEmpty( node.left ), justOrEmpty( node.right ) ) )
                .map( node -> node.value );
    }
}
