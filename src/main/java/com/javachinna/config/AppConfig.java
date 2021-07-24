package com.javachinna.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.javachinna.controller.ProductResource;
import com.javachinna.repo.ProductRepository;
import com.javachinna.service.ProductService;
import com.javachinna.service.impl.ProductServiceImpl;

public class AppConfig extends ResourceConfig {

	public AppConfig() {
		register(ProductResource.class);
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(ProductServiceImpl.class).to(ProductService.class);
				bind(ProductRepository.class).to(ProductRepository.class);
			}
		});
		// Now you can expect validation errors to be sent to the
		// client.
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}
}