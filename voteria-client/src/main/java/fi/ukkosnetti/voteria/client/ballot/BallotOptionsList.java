package fi.ukkosnetti.voteria.client.ballot;

import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class BallotOptionsList extends VerticalPanel {

	private ListDataProvider<String> optionProvider = new ListDataProvider<String>();
	
	@Override
	protected void onLoad() {
		final CellList<String> cellList = new CellList<>(new TextCell());
		optionProvider.addDataDisplay(cellList);
		final TextBox optionBox = new TextBox();
		Button addOptionButton = new Button("Add Option", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String optionName = optionBox.getName();
				if (null != optionName && !optionName.trim().isEmpty()) {
					optionProvider.getList().add(optionName.trim());
					cellList.redraw();
				}
			}
			
		});
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(optionBox);
		hPanel.add(addOptionButton);
		this.add(hPanel);
		this.add(cellList);
	}
	
	public List<String> getOptions() {
		return optionProvider.getList();
	}

}
