package fi.ukkosnetti.voteria.common.rest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

public interface VoteRestService extends RestService {
	
	@PUT
	@Path("vote/{ballot}/{option}")
	public void vote(@PathParam("ballot") String ballot, @PathParam("option") String option, MethodCallback<Void> callback);

}
