package fi.ukkosnetti.voteria.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Voteria implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel panel = RootPanel.get();
		panel.add(createNewBallotButton());
		panel.add(searchBox());
	}

	private Button createNewBallotButton() {
		return new Button("New Ballot");
	}
	
	private TextBox searchBox() {
		TextBox searchBox =  new TextBox();
		searchBox.setText("");
		return searchBox;
	}

}
