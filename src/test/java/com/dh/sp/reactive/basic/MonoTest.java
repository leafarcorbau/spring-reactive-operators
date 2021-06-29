package com.dh.sp.reactive.basic;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

  @Test
  public void mono(){

    //Given
    final Mono<String> mono = Mono.just("Spring");

    //Then
    StepVerifier.create(mono)
        .expectNext("Spring")
        .verifyComplete();
    ;
  }

  @Test
  public void mono_withErrorMessage(){

    //Given
    final Mono<String> mono = Mono.error(new IllegalArgumentException("Mono Error"));

    //Then
    StepVerifier.create(mono)
        .expectErrorMessage("Mono Error")
        .verify();
    ;
  }

  @Test
  public void mono_withErrorClass(){

    //Given
    final Mono<String> mono = Mono.error(new IllegalArgumentException("Mono Error"));

    //Then
    StepVerifier.create(mono)
        .expectError(IllegalArgumentException.class)
        .verify();
    ;
  }
}
