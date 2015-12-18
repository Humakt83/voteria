package fi.ukkosnetti.voteria.client.ballot;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class BallotPopup extends DialogBox {
	
	private TextBox title = new TextBox();
	private DateBox endDate = new DateBox();
	
	public BallotPopup() {
		super();
		init();
	}

	private void init() {
		setText("Create ballot");
		VerticalPanel container = new VerticalPanel();
		container.add(titlePanel());
		container.add(endDatePanel());
		container.add(buttonPanel());
		this.setWidget(container);		
	}

	private Widget endDatePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(new Label("Date ballot ends"));
		panel.add(endDate);
		return panel;
	}

	private Widget titlePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(new Label("Title"));
		panel.add(title);
		return panel;
	}

	private Widget buttonPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(new Button("Cancel", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				BallotPopup.this.hide();
			}
		}));
		Button submitButton = new Button("Submit");
		submitButton.setEnabled(false);
		panel.add(submitButton);
		return panel;
	}

}
