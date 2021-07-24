package com.javachinna.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundMapper implements ExceptionMapper<ResourceNotFoundException> {
	@Override
	public Response toResponse(ResourceNotFoundException ex) {
		return Response.status(404).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
	}
}