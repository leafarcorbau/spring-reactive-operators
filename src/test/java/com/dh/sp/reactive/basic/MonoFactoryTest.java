package com.dh.sp.reactive.basic;

import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoFactoryTest {

  private final List<String> names = List.of("Rafa", "Marija");

  @Test
  public void mono_fromJustOrEmpty_null(){

    //Given
    final Mono<String> mono = Mono.justOrEmpty(null);

    //Then
    StepVerifier.create(mono)
        .verifyComplete();
  }

  @Test
  public void mono_fromJustOrEmpty(){

    //Given
    final Mono<List<String>> mono = Mono.justOrEmpty(names);

    //Then
    StepVerifier.create(mono)
        .expectNext(names)
        .verifyComplete();
  }

  @Test
  public void mono_usingSupplier(){

    //Given
    final Supplier<String> stringSupplier = ()-> "Rafa";
    final Mono<String> mono = Mono.fromSupplier(stringSupplier);

    //Then
    StepVerifier.create(mono)
        .expectNext("Rafa")
        .verifyComplete();
  }
}
