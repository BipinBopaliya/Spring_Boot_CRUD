package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testFlux(){

		Integer a1 = new Integer(111111111);
		Integer a2 = new Integer(222222222);
		Integer a3 = new Integer(333333333);

		Flux<Integer> flux = Flux.just(a1, a2, a3);


		List<Integer> ls = flux.collectList().block();
		for (Integer s: ls
			 ) {
			System.out.println(s);
		}

		Mono mono = Mono.just(a1);
		mono.map(i -> Integer.toBinaryString((Integer) i)).subscribe(data -> System.out.println(data));

		//List<Integer> abc = new ArrayList<>();
		//abc.add(1);
		//abc.add(2);
		//Flux<Integer> flux = Flux.fromIterable(abc);
		//flux.log().subscribe(data -> System.out.println(data));
 	}
}

