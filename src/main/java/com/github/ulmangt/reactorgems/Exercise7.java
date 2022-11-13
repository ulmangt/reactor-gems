package com.github.ulmangt.reactorgems;

import static reactor.core.publisher.Flux.just;
import static reactor.core.publisher.Flux.merge;
import static reactor.core.publisher.Mono.justOrEmpty;

import java.util.function.Function;

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
        public final Node<V> right;

        public Node( V value, Node<V> left, Node<V> right )
        {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node( V value )
        {
            this( value, null, null );
        }

        public V value( )
        {
            return this.value;
        }

        public Node<V> left( )
        {
            return this.left;
        }

        public Node<V> right( )
        {
            return this.right;
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
    public static <V> Publisher<V> emitPreOrder( Mono<Node<V>> root )
    {
        return root
                .expandDeep( node -> merge( justOrEmpty( node.left ), justOrEmpty( node.right ) ) )
                .map( Node::value );
    }

    /**
     * Emit the values in the tree rooted at the provided node in a in-order (sorted) traversal.
     *
     * For the example tree from {@link #emitPreOrder(Mono)}, emitInOrder should emit: 2, 5, 6, 9, 10, 13, 20
     */
    public static <V> Publisher<V> emitInOrder( Mono<Node<V>> root )
    {
        return root.flatMapMany( node ->
                merge(
                        emitInOrder( justOrEmpty( node.left ) ),
                        just( node.value ),
                        emitInOrder( justOrEmpty( node.right ) )
                )
        );
    }
}
