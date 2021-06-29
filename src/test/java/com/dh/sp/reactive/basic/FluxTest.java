package com.dh.sp.reactive.basic;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

  @Test
  public void flux(){

    //Given
    final Flux<String> flux = Flux.just("Spring", "Springboot");

    //Then
    StepVerifier.create(flux)
        .expectNext("Spring")
        .expectNext("Springboot")
        .verifyComplete();
    ;
  }

  @Test
  public void flux_withErrorClass(){
    //Given
    final Flux<String> flux = Flux.just("Spring", "Springboot")
        .concatWith(Flux.error(new IllegalArgumentException("Flux error")));

    //Then
    StepVerifier.create(flux)
        .expectNext("Spring")
        .expectNext("Springboot")
        .expectError(IllegalArgumentException.class)
        .verify();
  }

  @Test
  public void flux_withErrorMessage(){
    //Given
    final Flux<String> flux = Flux.just("Spring", "Springboot")
        .concatWith(Flux.error(new IllegalArgumentException("Flux error")));

    //Then
    StepVerifier.create(flux)
        .expectNext("Spring")
        .expectNext("Springboot")
        .expectErrorMessage("Flux error")
        .verify();
  }

  @Test
  public void flux_elementsCount(){
    //Given
    final Flux<String> flux = Flux.just("Spring", "Springboot")
        .concatWith(Flux.error(new IllegalArgumentException("Flux Error")));

    //Then
    StepVerifier.create(flux)
        .expectNextCount(2)
        .expectErrorMessage("Flux Error")
        .verify();
  }
}
