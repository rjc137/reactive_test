package com.sample.reactive.api.controller;

import static org.mockito.BDDMockito.given;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
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
@ExtendWith(MockitoExtension.class)
@Import(IFooService.class)
class FooControllerIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	IFooService service;

	@Test
	void testGetFoos() {

		given(service.getFoos()).willReturn(Arrays.asList(new Foo(1, "name_1"), new Foo(2, "name_2")));

		webTestClient.get().uri("/v1/foos").exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.valueOf("text/event-stream;charset=UTF-8")).expectBodyList(Foo.class);
	}

}
