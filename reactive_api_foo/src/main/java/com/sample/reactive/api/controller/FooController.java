package com.sample.reactive.api.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.reactive.api.model.Foo;
import com.sample.reactive.api.service.IFooService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/foos")
public class FooController {

	@Autowired
	private IFooService fooService;

	@Value("${com.sample.reactive.api.delay_seconds}")
	private long delaySeconds;

	public FooController(IFooService fooService) {
		this.fooService = fooService;
	}

	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Foo> getFoos() {
		return Flux.fromIterable(fooService.getFoos()).delayElements(Duration.ofSeconds(delaySeconds));
	}

}
