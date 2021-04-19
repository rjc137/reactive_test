package com.sample.reactive.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sample.reactive.api.model.Foo;

@ExtendWith(MockitoExtension.class)
class FooServiceIntegrationTest {

	@Test
	void testGetFoos() {

		IFooService service = mock(IFooService.class);

		Foo foo1 = new Foo(1, "name_1");
		Foo foo2 = new Foo(2, "name_2");

		given(service.getFoos()).willReturn(Arrays.asList(foo1, foo2));

		Collection<Foo> fooList = service.getFoos();

		assertThat(fooList).isNotEmpty().hasSize(2).containsExactly(foo1, foo2);

	}
}
