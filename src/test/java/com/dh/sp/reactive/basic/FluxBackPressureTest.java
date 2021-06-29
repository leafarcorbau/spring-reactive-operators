package com.dh.sp.reactive.basic;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxBackPressureTest {

  @Test
  public void flux_backPressure(){

    //Given
    final Flux<Integer> flux = Flux.range(1,10);

    //Then
    StepVerifier.create(flux)
        .expectSubscription()
        .thenRequest(1)
        .expectNext(1)
        .thenRequest(1)
        .expectNext(2)
        .thenRequest(1)
        .expectNext(3)
        .thenCancel()
        .verify();
  }

  @Test
  public void flux_backPressure_withSubscriber(){

    //Given
    final Flux<Integer> flux = Flux.range(1,10);

    flux.subscribe(item -> System.out.println("ITEM: "+item),
        e-> System.out.println("Error Msg: "+ e.getMessage()),
        ()-> System.out.println("FINISH"),
        subscription -> subscription.request(3));
  }

  @Test
  public void flux_backPressure_withSubscriber_withCancel(){

    //Given
    final Flux<Integer> flux = Flux.range(1,10);

    flux.subscribe(item -> System.out.println("ITEM: "+item),
        e-> System.out.println("Error Msg: "+ e.getMessage()),
        ()-> System.out.println("FINISH"),
        subscription -> subscription.cancel());
  }

  @Test
  public void flux_backPressure_withBaseSubscriber(){

    //Given
    final Flux<Integer> flux = Flux.range(1,10);

    flux.subscribe(new BaseSubscriber<Integer>() {
      @Override
      protected void hookOnNext(Integer value) {
        request(1);
        System.out.println("ITEM: "+value);
        if(value==4){
          cancel();
          System.out.println("CANCEL");
        }
      }
    });
  }

}
