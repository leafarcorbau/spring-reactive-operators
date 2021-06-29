package com.dh.sp.reactive.basic;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTimeTest {

  @Test
  public void flux_interval(){

    //Given
    final Flux<Long> flux = Flux.interval(Duration.ofMillis(200))
        .take(3)
        .log();
    //Then
    StepVerifier.create(flux)
        .expectSubscription()
        .expectNext(0L,1L, 2L )
        .verifyComplete();

  }

}
