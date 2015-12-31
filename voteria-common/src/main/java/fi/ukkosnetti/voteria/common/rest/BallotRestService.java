package fi.ukkosnetti.voteria.common.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import fi.ukkosnetti.voteria.common.dto.BallotCreateDTO;

public interface BallotRestService extends RestService {

	@POST
	@Path("ballot")
	public void create(BallotCreateDTO dto, MethodCallback<Long> callback);
}
