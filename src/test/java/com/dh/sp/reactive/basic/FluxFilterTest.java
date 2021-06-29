package com.dh.sp.reactive.basic;

import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxFilterTest {

  private final List<String> names = List.of("Rafa", "Marija");

  @Test
  public void flux_filterTest(){

    //Given
    final Flux<String> flux = Flux.fromIterable(names);
    //When
    final Flux<String> actual = flux.filter(names -> names.equals("Rafa"));
    //Then
    StepVerifier.create(actual)
        .expectNext("Rafa")
        .verifyComplete();
  }
}
