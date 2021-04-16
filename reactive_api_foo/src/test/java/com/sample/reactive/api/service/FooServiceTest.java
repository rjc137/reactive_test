package com.sample.reactive.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sample.reactive.api.model.Foo;
import com.sample.reactive.api.service.IFooService;

@ExtendWith(MockitoExtension.class)
class FooServiceTest {

	@Mock
	IFooService fooService;

	@Test
	void testGetFoos() {
		when(fooService.getFoos()).thenReturn(Arrays.asList(new Foo(1, "name_1"), new Foo(2, "name_2")));
		assertEquals(2, fooService.getFoos().size());
	}
}
