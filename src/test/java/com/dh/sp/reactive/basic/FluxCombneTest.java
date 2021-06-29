package com.dh.sp.reactive.basic;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxCombneTest {

  @Test
  public void flux_combineWithMerge(){

    //Given
    final Flux<String> flux1 = Flux.just("A","B");
    final Flux<String> flux2 = Flux.just("C","D");

    //Given
    final Flux<String> actual = Flux.merge(flux1,flux2);

    //Then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext("A", "B","C","D")
        .verifyComplete();
  }

  @Test
  public void flux_combineWithConcat(){

    //Given
    final Flux<String> flux1 = Flux.just("A","B");
    final Flux<String> flux2 = Flux.just("C","D");

    //Given
    final Flux<String> actual = Flux.concat(flux1,flux2);

    //Then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext("A", "B","C","D")
        .verifyComplete();
  }

  @Test
  public void flux_combineWithZip(){

    //Given
    final Flux<String> flux1 = Flux.just("A","B");
    final Flux<String> flux2 = Flux.just("C","D");

    //Given
    final Flux<String> actual = Flux.zip(flux1,flux2, (s1, s2) -> s1.concat("_").concat(s2));

    //Then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext("A_C","B_D")
        .verifyComplete();
  }
}
