package com.sample.reactive.api.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.sample.reactive.api.model.Foo;

@Service
public class FooServiceImpl implements IFooService {

	@Override
	public Collection<Foo> getFoos() {
		Collection<Foo> fooCollection = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			fooCollection.add(new Foo(i, "name_"+i));
		}
		return fooCollection;
	}

}
