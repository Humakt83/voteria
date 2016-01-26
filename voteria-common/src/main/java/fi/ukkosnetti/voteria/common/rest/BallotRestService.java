package fi.ukkosnetti.voteria.common.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import fi.ukkosnetti.voteria.common.dto.BallotCreateDTO;
import fi.ukkosnetti.voteria.common.dto.BallotDTO;

public interface BallotRestService extends RestService {

	@POST
	@Path("ballot")
	public void create(BallotCreateDTO dto, MethodCallback<Long> callback);
	
	@GET
	@Path("ballot/all")
	public void all(MethodCallback<List<BallotDTO>> callback);
	
	@GET
	@Path("ballot/bytitle/{title}")
	public void getByTitle(@PathParam("title") String title, MethodCallback<BallotDTO> callback);
}
