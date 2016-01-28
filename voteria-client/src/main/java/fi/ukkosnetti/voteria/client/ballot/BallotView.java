package fi.ukkosnetti.voteria.client.ballot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import fi.ukkosnetti.voteria.client.ballot.result.OptionResult;
import fi.ukkosnetti.voteria.client.ballot.result.Result;
import fi.ukkosnetti.voteria.client.ballot.result.ResultCalculator;
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
	private FlowPanel votePanel = new FlowPanel();
	private Button resultsButton = new Button("Show results");
	private BallotDTO ballot;
	
	@Override
	protected void onLoad() {
		title.setStyleName("ballot-header");
		this.add(title);
		resultsButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showResults(ballot);
			}
		});
		resultsButton.setEnabled(false);
		votePanel.add(closesLabel);
		votePanel.add(resultsButton);
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
		votePanel.add(options);
		this.add(votePanel);
		this.setStyleName("col-md-7");
	}
	
	public void setBallot(BallotDTO ballot) {
		this.ballot = ballot;
		title.setText(ballot.getTitle());
		closesLabel.setText(ballot.getEnds().toString());
		List<String> options = new ArrayList<>();
		for (BallotOptionDTO option : ballot.getOptions()) {
			options.add(option.getOptionName());
		}
		optionProvider.setList(options);
		resultsButton.setEnabled(true);
	}
	
	private void castVote(String option) {
		service.vote(title.getText(), option, new MethodCallback<BallotDTO>() {

			@Override
			public void onFailure(Method method, Throwable exception) {
				logger.log(Level.SEVERE, exception.getMessage(), exception);
			}

			@Override
			public void onSuccess(Method method, BallotDTO response) {
				logger.log(Level.INFO, "Vote was cast");
				showResults(response);
			}
			
		});
	}
	
	private void showResults(BallotDTO dto) {		
		Result result = ResultCalculator.calculateResults(dto);
		FlowPanel resultPanel = new FlowPanel();
		CellList<OptionResult> resultList = new CellList<>(new ResultCell());
		resultList.setRowData(0, result.optionResults);
		resultPanel.add(resultList);
		this.add(resultPanel);		
		votePanel.setVisible(false);
	}
	
	static class ResultCell extends AbstractCell<OptionResult> {
		
		interface Templates extends SafeHtmlTemplates {
			
			@SafeHtmlTemplates.Template("<div>{0}</div><div style=\"{1}\">{2}</div>")
			SafeHtml cell(SafeHtml option, SafeStyles percentageStyle, SafeHtml votesAmount);
		}
		
		private static Templates templates = GWT.create(Templates.class);
		
		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, OptionResult value, SafeHtmlBuilder sb) {
			if (value == null) {
				return;
			}
			SafeHtml option = SafeHtmlUtils.fromTrustedString(value.optionName);
			SafeHtml voteAmount = SafeHtmlUtils.fromTrustedString(value.totalVotes.toString());
			SafeStyles percentageStyle = SafeStylesUtils.fromTrustedString("height: 10px; background-color: green; width: " + value.percentageOfVotes + "px;");
			sb.append(templates.cell(option, percentageStyle, voteAmount));
		}
		
	}
	
}
