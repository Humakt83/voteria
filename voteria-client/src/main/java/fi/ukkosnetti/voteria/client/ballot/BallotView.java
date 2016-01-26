package fi.ukkosnetti.voteria.client.ballot;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import fi.ukkosnetti.voteria.common.dto.BallotDTO;
import fi.ukkosnetti.voteria.common.dto.BallotOptionDTO;

public class BallotView extends VerticalPanel {

	private Label title = new Label();
	private Label closesLabel = new Label();
	private CellList<String> options;
	private ListDataProvider<String> optionProvider;
	
	@Override
	protected void onLoad() {
		this.add(title);
		this.add(closesLabel);
		options = new CellList<>(new TextCell());
		optionProvider = new ListDataProvider<>();
		optionProvider.addDataDisplay(options);
		this.add(options);
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
	
}
