package fi.ukkosnetti.voteria.client.ballot;

import java.util.Date;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import fi.ukkosnetti.voteria.common.dto.BallotCreateDTO;
import fi.ukkosnetti.voteria.common.rest.BallotRestService;

public class BallotCreatePopup extends DialogBox {
	
	private BallotRestService service = GWT.create(BallotRestService.class);
	
	private TextBox title = new TextBox();
	private DateBox endDate = new DateBox();
	private Button submitButton;
	private BallotOptionsList options = new BallotOptionsList();
	
	public BallotCreatePopup() {
		super();
		init();
	}

	private void init() {
		setText("Create ballot");
		VerticalPanel container = new VerticalPanel();
		container.add(titlePanel());
		container.add(endDatePanel());
		container.add(options);
		container.add(buttonPanel());
		this.setWidget(container);		
	}

	private Widget endDatePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(new Label("Date ballot ends"));
		panel.add(endDate);
		endDate.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				submitButton.setEnabled(isBallotValid());
			}
			
		});
		return panel;
	}
	
	private boolean isBallotValid() {
		String titleText = title.getText();
		return endDate.getValue() != null && titleText != null && !titleText.trim().isEmpty();
	}

	private Widget titlePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(new Label("Title"));
		panel.add(title);
		title.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				submitButton.setEnabled(isBallotValid());
			}
			
		});
		return panel;
	}

	private Widget buttonPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(new Button("Cancel", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				BallotCreatePopup.this.hide();
			}
		}));
		submitButton = new Button("Submit", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				BallotCreateDTO dto = new BallotCreateDTO();
				dto.setEnds(endDate.getValue());
				dto.setTitle(title.getText());
				dto.setOptions(options.getOptions());
				service.create(dto, new MethodCallback<Long>() {

					@Override
					public void onFailure(Method method, Throwable exception) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Method method, Long response) {
						BallotCreatePopup.this.hide();
					}
					
				});
			}
		});
		submitButton.setEnabled(false);
		panel.add(submitButton);
		return panel;
	}

}
