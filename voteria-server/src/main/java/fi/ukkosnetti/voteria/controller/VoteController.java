package fi.ukkosnetti.voteria.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.ukkosnetti.voteria.service.VoteService;

@RestController
@RequestMapping("voteria/vote")
public class VoteController {

	@Autowired
	private VoteService service;
	
	@RequestMapping(value = "/{ballot}/{option}", method = RequestMethod.PUT)
	public void voteOption(@PathVariable("ballot") String ballotTitle, @PathVariable("option") String optionName, HttpServletRequest request) {
		service.vote(ballotTitle, optionName, request.getRemoteAddr());
	}
}
