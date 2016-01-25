package fi.ukkosnetti.voteria.client.ballot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import fi.ukkosnetti.voteria.common.dto.BallotOptionCreateDTO;

public class BallotOptionsList extends VerticalPanel {

	private ListDataProvider<String> optionProvider;
	
	private Logger logger = Logger.getLogger(BallotOptionsList.class.getName());
	
	@Override
	protected void onLoad() {
		optionProvider = new ListDataProvider<String>(new ArrayList<String>());
		final CellList<String> cellList = new CellList<>(new TextCell());
		optionProvider.addDataDisplay(cellList);
		final TextBox optionBox = new TextBox();
		Button addOptionButton = new Button("Add Option", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String optionName = optionBox.getText();
				if (null != optionName && !optionName.trim().isEmpty()) {
					logger.log(Level.INFO, "Adding an option " + optionName);				
					optionProvider.getList().add(optionName.trim());
				}
			}
			
		});
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(optionBox);
		hPanel.add(addOptionButton);
		this.add(hPanel);
		this.add(cellList);
	}
	
	public List<BallotOptionCreateDTO> getOptions() {
		List<BallotOptionCreateDTO> dtos = new ArrayList<>();
		for (String option : optionProvider.getList()) {
			dtos.add(new BallotOptionCreateDTO(option));
		}
		return dtos;
	}

}
