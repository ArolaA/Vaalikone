package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/exampleservice")

public class Testikysely {

	@Path("/hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN) // Jos text_html, niin sitten voidaan syöttää HTML-koodia
	
	public String sayHello() {
		return "<h2>Hello, Sir!</h2>";
	}
	
}
