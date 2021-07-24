package com.javachinna.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.javachinna.exception.ResourceNotFoundException;
import com.javachinna.model.Product;
import com.javachinna.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * Root resource (exposed at "products" path)
 */
@Slf4j
@Path("products")
public class ProductResource {

	private ProductService productService;

	/**
	 * @param productService
	 */
	@Inject
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "application/json" media type.
	 * 
	 * @param consumerKey
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductList(@NotBlank(message = "Consumerkey is required") @QueryParam(value = "consumerKey") String consumerKey) {
		log.info("Consumer: {}", consumerKey);
		return productService.findAll();
	}

	@GET
	@Path("{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProduct(@PathParam(value = "productId") Long productId) {
		return productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
	}

	@POST
	public String createProduct(@Valid Product product) {
		productService.save(product);
		return "Product added";
	}

	@PUT
	@Path("{productId}")
	public String updateProduct(@PathParam(value = "productId") Long productId, @Valid Product product) {
		return productService.findById(productId).map(p -> {
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			productService.update(p);
			return "Product updated";
		}).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
	}

	@DELETE
	@Path("{productId}")
	public String deleteProduct(@PathParam(value = "productId") Long productId) {
		return productService.findById(productId).map(p -> {
			productService.deleteById(productId);
			return "Product deleted";
		}).orElseThrow(() -> new ResourceNotFoundException("productId " + productId + " not found"));
	}
}