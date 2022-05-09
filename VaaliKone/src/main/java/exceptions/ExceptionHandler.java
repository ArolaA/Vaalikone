package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
	private String errorMessage="Some error happened try again later :^)";
	@Override
	public Response toResponse(Exception exception){

		ResponseBuilder builder = Response.ok(errorMessage);
		return builder.build();
	}
}