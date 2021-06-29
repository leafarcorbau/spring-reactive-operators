package com.dh.sp.reactive.basic;

import java.time.Duration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ColdAndHotPublisherTest {

  @Test
  @SneakyThrows
  public void flux_coldPublisher(){

    //Given
    final Flux<String> flux = Flux.just("A", "B","C","D","E")
        .delayElements(Duration.ofSeconds(1));

    flux.subscribe(s -> System.out.println("subscriber 1: "+s));
    Thread.sleep(2000);

    flux.subscribe(s -> System.out.println("subscriber 2: "+s));
    Thread.sleep(4000);
  }


  @Test
  @SneakyThrows
  public void flux_hotPublisher(){

    //Given
    final Flux<String> flux = Flux.just("A", "B","C","D","E")
        .delayElements(Duration.ofSeconds(1));

    ConnectableFlux<String> connectableFlux = flux.publish();
    connectableFlux.connect();

    connectableFlux.subscribe(s -> System.out.println("subscriber 1: "+s));
    Thread.sleep(3000);

//Does not emit values from the beggining
    connectableFlux.subscribe(s -> System.out.println("subscriber 2: "+s));
    Thread.sleep(6000);
  }
}
