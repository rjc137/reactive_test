package com.sample.reactive.api.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.sample.reactive.api.model.Foo;
import com.sample.reactive.api.service.IFooService;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = FooController.class)
@Import(IFooService.class)
class FooControllerTest {

	@MockBean
	private IFooService fooService;

	@Autowired
	private WebTestClient webTestClient;

	@BeforeEach
	void setUp() {
		when(fooService.getFoos()).thenReturn(Arrays.asList(new Foo(1, "name_1"), new Foo(2, "name_2")));
	}

	@Test
	void testGetFoos() {
		webTestClient.get().uri("/v1/foos").exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.valueOf("text/event-stream;charset=UTF-8")).expectBodyList(Foo.class);
	}

}
