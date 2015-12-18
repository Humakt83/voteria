package fi.ukkosnetti.voteria.client.rest;

import com.google.gwt.core.client.JsonUtils;
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

	public static void createBallot(BallotCreate dto, final RestResultHandler<Long> handler) {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode("ballot"));
		try {
			builder.sendRequest(convertToJsonString(dto), new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {
					handler.handleResult(Long.parseLong(response.getText()));					
				}

				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					
				}
				
			});
		} catch (RequestException e) {
			
		}
	}

	private static String convertToJsonString(BallotCreate dto) {
		AutoBean<BallotCreate> ballot = AutoBeanUtils.getAutoBean(dto);
		return AutoBeanCodex.encode(ballot).getPayload();
	}
	
}
