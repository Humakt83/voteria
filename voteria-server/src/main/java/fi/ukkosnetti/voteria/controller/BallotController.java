package fi.ukkosnetti.voteria.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.ukkosnetti.voteria.common.dto.BallotCreate;
import fi.ukkosnetti.voteria.common.dto.BallotCreateDTO;
import fi.ukkosnetti.voteria.common.dto.BallotDTO;
import fi.ukkosnetti.voteria.service.BallotService;

@RestController
@RequestMapping("ballot")
public class BallotController {
	
	@Autowired
	private BallotService service;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody Long create(@RequestBody BallotCreateDTO dto, HttpServletRequest request) {
		return service.create(dto, request.getRemoteAddr());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody BallotDTO get(@PathVariable("id") Long id) {
		return service.get(id);
	}
}
