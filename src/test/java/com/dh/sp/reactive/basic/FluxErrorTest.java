package com.dh.sp.reactive.basic;

import static java.lang.String.format;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxErrorTest {

  @Test
  public void flux_errorHandling(){

    //Given
    final Flux<String> flux = Flux.just("A","B")
        .concatWith(Flux.error(new IllegalArgumentException("flux error")));

    //Then
    StepVerifier.create(flux)
        .expectSubscription()
        .expectNext("A", "B")
        .expectErrorMessage("flux error")
        .verify();
  }

  @Test
  public void flux_onErrorResume(){

    //Given
    final Flux<String> flux = Flux.just("A","B")
        .concatWith(Flux.error(new IllegalArgumentException("flux error")))
        .onErrorResume(e-> {
          System.out.println(format("Error Message: %s",e.getMessage()));
          return Flux.just("C");
        });

    //Then
    StepVerifier.create(flux)
        .expectSubscription()
        .expectNext("A", "B")
        .expectNext("C")
        .verifyComplete();
  }

  @Test
  public void flux_onErrorReturn(){

    //Given
    final Flux<String> flux = Flux.just("A","B")
        .concatWith(Flux.error(new IllegalArgumentException("flux error")))
        .onErrorReturn("Default");

    //Then
    StepVerifier.create(flux)
        .expectSubscription()
        .expectNext("A", "B", "Default")
        .verifyComplete();
  }

  @Test
  public void flux_onErrorMap(){

    //Given
    final Flux<String> flux = Flux.just("A","B")
        .concatWith(Flux.error(new IllegalArgumentException("flux error")))
        .onErrorMap(e -> new IllegalStateException("state error"));

    //Then
    StepVerifier.create(flux)
        .expectSubscription()
        .expectNext("A", "B")
        .expectErrorMessage("state error")
        .verify();
  }

  @Test
  public void flux_onErrorMap_withRetry(){

    //Given
    final Flux<String> flux = Flux.just("A","B")
        .concatWith(Flux.error(new IllegalArgumentException("flux error")))
        .onErrorMap(e -> new IllegalStateException("state error"))
        .retry(2);

    //Then
    StepVerifier.create(flux)
        .expectSubscription()
        .expectNext("A", "B")
        .expectNext("A", "B")
        .expectNext("A", "B")
        .expectErrorMessage("state error")
        .verify();
  }
}
