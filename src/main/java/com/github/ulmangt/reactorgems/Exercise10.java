package com.github.ulmangt.reactorgems;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Exercise 10 - Reactor Context
 *
 * Note, this is a rather trivial/forced usage of Context. See some of the links below for discussion of more realistic use cases.
 *
 * @see <a href="https://projectreactor.io/docs/core/release/reference/#context">Project Reactor Context Documentation</a>
 * @see <a href="https://github.com/micrometer-metrics/context-propagation">Micrometer Context-Propagation GitHub Project</a>
 * @see <a href="https://domenicosibilio.medium.com/the-good-the-bad-and-the-ugly-of-propagating-data-through-reactive-streams-62beac48789e">Reactor Context Example Blog Post</a>
 */
public class Exercise10
{
    public interface SlothSaysService
    {
        public static final String SlothNameKey = "SLOTH_NAME_KEY";

        /**
         * Append the string "[SLOTH_NAME_KEY] says: " to the beginning of each string emitted by the phrases Flux.
         *
         * SLOTH_NAME_KEY is a {@link reactor.util.context.Context} key which the caller of
         * {@link #appendName(Flux)} must set a value for.
         *
         * For example, if SLOTH_NAME_KEY == "Flash" and the phrases Publisher emits "What do you call a three humped camel?"
         * then appendName should emit "Flash says: What do you call a three humped camel?"
         */
        public Flux<String> appendName( Flux<String> phrases );
    }

    /**
     * Create an instance of the SlothSaysService which properly implements appendName
     * utilizing {@link reactor.util.context.Context} to lookup the value of the Context key {@code SlothNameKey}.
     */
    public static SlothSaysService createSlothSaysService( )
    {
        return new SlothSaysService( )
        {
            @Override
            public Flux<String> appendName( Flux<String> phrases )
            {
                return Mono.deferContextual( context -> Mono.just( context.get( SlothNameKey ) ) )
                        .flatMapMany( id -> phrases.map( phrase -> id + " says: " + phrase ) );
            }
        };
    }

    /**
     * Call {@link SlothSaysService#appendName(Flux)} passing in the provided phrases Flux and
     * setting the value of the {@link reactor.util.context.Context} key {@code SlothNameKey} to {@code slothName}.
     */
    public static Flux<String> useSlothSaysService( SlothSaysService service, Flux<String> phrases, String slothName )
    {
        return service.appendName( phrases )
                .contextWrite( context -> context.put( SlothSaysService.SlothNameKey, slothName ) );
    }
}
