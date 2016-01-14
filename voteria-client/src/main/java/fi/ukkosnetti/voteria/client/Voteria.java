package fi.ukkosnetti.voteria.client;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import fi.ukkosnetti.voteria.client.ballot.BallotCreatePopup;
import fi.ukkosnetti.voteria.common.dto.BallotDTO;
import fi.ukkosnetti.voteria.common.rest.BallotRestService;

public class Voteria implements EntryPoint {

	private BallotRestService service = GWT.create(BallotRestService.class);
	private CellList<String> cellList;
	private Logger logger = Logger.getLogger(Voteria.class.getName());
	
	@Override
	public void onModuleLoad() {
		RootPanel panel = RootPanel.get();
		panel.add(createNewBallotButton());
		panel.add(searchBox());
		panel.add(ballotList());
	}

	private Widget ballotList() {
		cellList = new CellList<>(new TextCell());
		final ListDataProvider<String> dataProvider = new ListDataProvider<String>(Arrays.asList("Test"));
		dataProvider.addDataDisplay(cellList);
		service.all(new MethodCallback<List<BallotDTO>>() {

			@Override
			public void onFailure(Method method, Throwable exception) {
				
			}

			@Override
			public void onSuccess(Method method, List<BallotDTO> response) {
				List<String> ballots = dataProvider.getList();
				for (BallotDTO dto : response) {
					ballots.add(dto.getTitle());
				}
				dataProvider.setList(ballots);
				dataProvider.refresh();
				dataProvider.flush();
				logger.log(Level.SEVERE, "Got ballots: " + response.size());
			}
		});
		VerticalPanel panel = new VerticalPanel();
		panel.add(cellList);
		return panel;
	}

	private Button createNewBallotButton() {
		return new Button("New Ballot", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				new BallotCreatePopup().show();
			}
		});
	}
	
	private TextBox searchBox() {
		TextBox searchBox =  new TextBox();
		searchBox.setText("");
		return searchBox;
	}

}
