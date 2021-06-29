package com.dh.sp.reactive.basic;

import java.time.Duration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

public class VirtualTimeTest {

  @Test
  @SneakyThrows
  public void flux(){

    //Given
    final Flux<Long> flux = Flux.interval(Duration.ofSeconds(1))
        .take(3);

    //Then
    StepVerifier.create(flux.log())
        .expectSubscription()
        .expectNext(0L, 1L, 2L)
        .verifyComplete();
  }

  @Test
  @SneakyThrows
  public void flux_withVirtualTime(){

    //Given
    VirtualTimeScheduler.getOrSet();
    final Flux<Long> flux = Flux.interval(Duration.ofSeconds(1))
        .take(3);

    //Then
    StepVerifier.withVirtualTime(()-> flux.log())
        .expectSubscription()
        .thenAwait(Duration.ofSeconds(3))
        .expectNext(0L, 1L, 2L)
        .verifyComplete();
  }
}
