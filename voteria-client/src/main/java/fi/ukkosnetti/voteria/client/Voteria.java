package fi.ukkosnetti.voteria.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fi.ukkosnetti.voteria.client.ballot.BallotCreatePopup;
import fi.ukkosnetti.voteria.client.ballot.BallotView;
import fi.ukkosnetti.voteria.common.dto.BallotDTO;
import fi.ukkosnetti.voteria.common.rest.BallotRestService;

public class Voteria implements EntryPoint {

	private BallotRestService service = GWT.create(BallotRestService.class);
	private CellList<String> cellList;
	private ListDataProvider<String> ballotProvider;
	private Logger logger = Logger.getLogger(Voteria.class.getName());
	private BallotView ballotView;
	
	@Override
	public void onModuleLoad() {
		ballotView = new BallotView();
		Defaults.setDateFormat(null);
		RootPanel panel = RootPanel.get();
		FlowPanel hPanel = new FlowPanel();
		hPanel.add(leftPanel());
		hPanel.add(ballotView);
		hPanel.setStyleName("row");
		panel.setStyleName("container-fluid main-container");
		panel.add(titlePanel());
		panel.add(hPanel);
	}

	private FlowPanel titlePanel() {
		FlowPanel titleContainer = new FlowPanel();
		FlowPanel titlePanel = new FlowPanel();
		Label title = new Label("Voteria");
		title.setStyleName("voteria-title");
		titlePanel.add(title);		
		titlePanel.setStyleName("col-md-3 titlepanel");
		FlowPanel voteriaMemoPanel = new FlowPanel();
		Label memoPart1 = new Label("Have a ");
		HTML image = new HTML();
		image.setStyleName("memo-img");
		Label memoPart2 = new Label("and vote");
		memoPart1.setStyleName("memo1");
		memoPart2.setStyleName("memo2");
		voteriaMemoPanel.add(memoPart1);
		voteriaMemoPanel.add(image);
		voteriaMemoPanel.add(memoPart2);
		voteriaMemoPanel.setStyleName("col-md-3 memopanel");
		titleContainer.add(titlePanel);
		titleContainer.add(voteriaMemoPanel);
		titleContainer.setStyleName("row");
		return titleContainer;
	}

	private FlowPanel leftPanel() {
		FlowPanel vPanel = new FlowPanel();
		vPanel.add(createNewBallotButton());
		vPanel.add(searchBox());
		vPanel.add(ballotList());
		vPanel.setStyleName("col-md-4");
		return vPanel;
	}

	private Widget ballotList() {
		cellList = new CellList<>(new TextCell());
		cellList.setStyleName("ballot-list");
		final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<>();
	    cellList.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        String selected = selectionModel.getSelectedObject();
	        if (selected != null) {
	        	showSelectedBallot(selected);
	        }
	      }
	    });
		ballotProvider = new ListDataProvider<>();
		ballotProvider.addDataDisplay(cellList);
		getBallots();
		return cellList;
	}

	private void getBallots() {
		service.all(new MethodCallback<List<BallotDTO>>() {

			@Override
			public void onFailure(Method method, Throwable exception) {
				logger.log(Level.SEVERE, exception.getMessage());
			}

			@Override
			public void onSuccess(Method method, List<BallotDTO> response) {
				List<String> ballots = new ArrayList<>();
				for (BallotDTO dto : response) {
					ballots.add(dto.getTitle());
				}
				ballotProvider.setList(ballots);
				ballotProvider.refresh();
				ballotProvider.flush();
				logger.log(Level.INFO, "Got ballots: " + response.size());
			}
		});
	}
	
	private void showSelectedBallot(String title) {
		service.getByTitle(title, new MethodCallback<BallotDTO>() {

			@Override
			public void onFailure(Method method, Throwable exception) {
				logger.log(Level.SEVERE, exception.getMessage());
			}

			@Override
			public void onSuccess(Method method, BallotDTO response) {				
				ballotView.setBallot(response);
			}
			
		});
	}

	private Button createNewBallotButton() {
		Button button = new Button("New Ballot", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				BallotCreatePopup popup = new BallotCreatePopup();
				popup.addCloseHandler(new CloseHandler<PopupPanel>() {

					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						getBallots();
					}
				});
				popup.show();
			}
		});
		button.setStyleName("btn btn-primary");
		return button;
	}

	private TextBox searchBox() {
		TextBox searchBox =  new TextBox();
		searchBox.setText("");
		return searchBox;
	}

}
