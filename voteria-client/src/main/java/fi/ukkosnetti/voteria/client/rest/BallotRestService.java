package fi.ukkosnetti.voteria.client.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

import fi.ukkosnetti.voteria.common.dto.BallotCreate;

public class BallotRestService {
	
	private static Logger logger = Logger.getLogger(BallotRestService.class.getName());
	
	public static void createBallot(BallotCreate dto, final RestResultHandler<Long> handler) {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode("ballot"));
		try {
			builder.setHeader("Content-Type", "application/json; charset=utf-8");
			builder.setHeader("Accept", "application/json; charset=utf-8");
			builder.sendRequest(convertToJsonString(dto), new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {
					handler.handleResult(Long.parseLong(response.getText()));					
				}

				@Override
				public void onError(Request request, Throwable exception) {
					logger.log(Level.SEVERE, exception.getMessage(), exception);
				}
				
			});
		} catch (RequestException e) {
			
		}
	}

	private static String convertToJsonString(BallotCreate dto) {
		logger.log(Level.SEVERE, "Converting object to json: " + dto.toString());
		AutoBean<BallotCreate> ballot = AutoBeanUtils.getAutoBean(dto);
		String json = AutoBeanCodex.encode(ballot).getPayload();
		logger.log(Level.SEVERE, "Converted object: " + json);
		return json;
	}
	
}
