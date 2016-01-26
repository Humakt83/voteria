package fi.ukkosnetti.voteria.client.ballot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fi.ukkosnetti.voteria.common.dto.BallotDTO;
import fi.ukkosnetti.voteria.common.dto.BallotOptionDTO;
import fi.ukkosnetti.voteria.common.rest.VoteRestService;

public class BallotView extends FlowPanel {

	private Logger logger = Logger.getLogger(BallotView.class.getName());
	private VoteRestService service = GWT.create(VoteRestService.class);
	private Label title = new Label();
	private Label closesLabel = new Label();
	private CellList<String> options;
	private ListDataProvider<String> optionProvider;
	
	@Override
	protected void onLoad() {
		title.setStyleName("ballot-header");
		this.add(title);
		this.add(closesLabel);
		options = new CellList<>(new TextCell());
		final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<>();
		options.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        String selected = selectionModel.getSelectedObject();
	        if (selected != null) {
	        	castVote(selected);
	        }
	      }
	    });
		optionProvider = new ListDataProvider<>();
		optionProvider.addDataDisplay(options);
		this.add(options);
		this.setStyleName("col-md-7");
	}
	
	public void setBallot(BallotDTO ballot) {
		title.setText(ballot.getTitle());
		closesLabel.setText(ballot.getEnds().toString());
		List<String> options = new ArrayList<>();
		for (BallotOptionDTO option : ballot.getOptions()) {
			options.add(option.getOptionName());
		}
		optionProvider.setList(options);
	}
	
	private void castVote(String option) {
		service.vote(title.getText(), option, new MethodCallback<Void>() {

			@Override
			public void onFailure(Method method, Throwable exception) {
				logger.log(Level.SEVERE, exception.getMessage(), exception);
			}

			@Override
			public void onSuccess(Method method, Void response) {
				logger.log(Level.INFO, "Vote was cast");
			}
			
		});
	}
	
}
