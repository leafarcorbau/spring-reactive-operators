package com.dh.sp.reactive.basic;

import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTransformTest {

  private final List<String> names = List.of("Rafa", "Marija");

  @Test
  public void flux_map(){

    //Given
    final Flux<String> flux = Flux.fromIterable(names);

    //Given
    final Flux<String> actual = flux.map(name -> name.concat("-flux"));

    //Then
    StepVerifier.create(actual)
        .expectNext("Rafa-flux", "Marija-flux")
        .verifyComplete();
  }

  @Test
  public void flux_mapRepeat(){

    //Given
    final Flux<String> flux = Flux.fromIterable(names);

    //Given
    final Flux<String> actual = flux.map(name -> name.concat("-flux"))
        .repeat(1);

    //Then
    StepVerifier.create(actual)
        .expectNext("Rafa-flux", "Marija-flux","Rafa-flux", "Marija-flux")
        .verifyComplete();
  }
  @Test
  public void flux_flatMap(){

    //Given
    final Flux<String> flux = Flux.fromIterable(names);

    //When
    final Flux<String> actual = flux.flatMap(name->{
      //To use to call DB or Service
      final List<String> newValue = List.of(name, "newValue");
      return  Flux.fromIterable(newValue);
    });

    //Then
    StepVerifier.create(actual)
        .expectNext("Rafa", "newValue","Marija","newValue")
        .verifyComplete();
  }


}
