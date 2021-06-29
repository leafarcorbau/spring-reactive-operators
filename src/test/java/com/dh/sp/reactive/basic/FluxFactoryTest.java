package com.dh.sp.reactive.basic;

import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxFactoryTest {

  private final List<String> names = List.of("Rafa", "Marija");

  @Test
  public void flux_fromIterable(){

    //Given
    final Flux<String> flux = Flux.fromIterable(names);

    //Then
    StepVerifier.create(flux)
        .expectNext("Rafa", "Marija")
        .verifyComplete();
  }

  @Test
  public void flux_fromArray(){

    //Given
    final Flux<String> flux = Flux.fromArray(new String[]{"Rafa", "Marija"});

    //Then
    StepVerifier.create(flux)
        .expectNext("Rafa", "Marija")
        .verifyComplete();
  }

  @Test
  public void flux_fromStream(){

    //Given
    final Flux<String> flux = Flux.fromStream(names.stream());

    //Then
    StepVerifier.create(flux)
        .expectNext("Rafa", "Marija")
        .verifyComplete();
  }
}
