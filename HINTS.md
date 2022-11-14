# Hints
This document contains hints organized by exercise.

## Exercise 1

### emitString

1. Use [Mono.just](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#just-T-).

### emitRuntimeException

1. Use [Mono.error](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#error-java.lang.Throwable-).

## Exercise 2

### emitStringSlowly

1. Use [Mono.delayElement](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#delayElement-java.time.Duration-).

### emitStringSlowlyOrQuickly

1. Use [Mono.flatMap](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#flatMap-java.util.function.Function-) to emit a different Publisher depending on the value emitted by animalName.
2. Use [Mono.just](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#just-T-) and [Mono.delayElement](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#delayElement-java.time.Duration-) in the same way as `emitStringSlowly( )` inside [Mono.flatMap](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#flatMap-java.util.function.Function-) if the value emitted by animalName is "Sloth". Otherwise, just wrap the animal name in [Mono.just](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#just-T-).

## Exercise 3

### emitSlothOnly

1. Use [Mono.filter](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#filter-java.util.function.Predicate-).

## Exercise 4

### emitFirstValidAnimalName

1. Use [Mono.filterWhen](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#filterWhen-java.util.function.Function-) to create a new `Collection` of `Mono` which have been filtered using `animalNameChecker`.

1. Use [Mono.firstWithValue](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#firstWithValue-java.lang.Iterable-) to choose the first `animalName` publisher to return (after filtering them based on `animalNameChecker` using `Mono.filterWhen`).

## Exercise 5

### sayHello

1. Use [Flux.map](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#map-java.util.function.Function-).

## Exercise 6

### filterUsingFlatMap

1. `flatMap( )` can return `Flux.empty( )`. Utilize that and the provided predicate function to filter out values.

### flatMapUsingMerge

1. `flatMap( )` is just a `map( )` (where the mapper function returns a Publisher) followed by a `Flux.merge( )`.

2. First call `source.map( mapper )`. Is the result Publisher something `Flux.merge( )` can operate on?

### mergeUsingFlatMap

1. Normally `flatMap( )` is used to modify the values emitted by its source Publisher. But `Flux.merge( )` does't do that. So try passing `Function.identity( )` or `input->input` as the mapper argument to `flatMap( )`.

## Exercise 7

### emitPreOrder

1. Use [Flux.expandDeep](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#expandDeep-java.util.function.Function-)

### emitInOrder

1. The built in `Flux.expand( )` and `Flux.expandDeep( )` won't help here. Consider a recursive implementation instead.

## Exercise 8

### sequenceEqual

1. Use `Flux.zip` to operate on pairwise elements from each publisher.

2. Use `Flux.any` to compare pairwise elements and short-circuit when a non-match is found.

3. Using `Flux.zip` / `Flux.any` as suggested in hints 1 and 2 isn't sufficient if the two producers start with matching sequences but one produces additional values (since `Flux.zip` will stop producing Tuples once either publisher completes). You'll need an additional check that once both producers complete, they emitted the same number of elements.

4. Use `Mono.firstWithValue` to combine the two checks from the above hints and retain the short-circuiting behavior.

## Exercise 9

### findThirdItemOrSloth

1. Use [Flux.elementAt](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#elementAt-int-T-)

### emitEveryNthElement

1. Use [Flux.index](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html#index--) to get the index of each item emitted by the source Provider.

2. `Flux.index` can be combined with `Flux.filter` to remove the desired items.

3. Handling `n == 0` may require a simple special case (just return `Flux.empty( )`).

## Exercise 10

### createSlothSaysService

1. Use [Mono.deferContextual](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#deferContextual-java.util.function.Function-) to retrieve the String value associated with SlothSaysService.SlothNameKey.

### useSlothSaysService

1. Use [Mono.contextWrite](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html#contextWrite-reactor.util.context.ContextView-) to set the value of SlothSaysService.SlothNameKey.