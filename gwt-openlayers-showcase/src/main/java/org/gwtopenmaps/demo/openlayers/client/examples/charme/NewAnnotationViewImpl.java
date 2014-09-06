package org.gwtopenmaps.demo.openlayers.client.examples.charme;

import java.util.ArrayList;
import java.util.List;

import org.gwtopenmaps.demo.openlayers.client.DialogBoxWithCloseButton;
import org.gwtopenmaps.openlayers.client.LonLat;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NewAnnotationViewImpl implements NewAnnotationView, ClickHandler {

	private NewAnnotationPresenter presenter;

	private TextArea taGeometry;
	private Button okButton;
	private Button cancelButton;
	private ListBox lbBegin;
	private ListBox lbEnd;
	private ListBox lbVars;
	private TextArea taAnnot;
	private TextBox tbBegin;
	private TextBox tbEnd;
	private DialogBoxWithCloseButton dialogBox;
	private ListBox lbAnnotType;
	private ListBox lbMotivation;
	private TextBox tbTags;
	private ListBox lbDateFormat;

	// constructor will eventually need geo boundaries, time boundaries, depth
	// boundaries and current variable name
	public NewAnnotationViewImpl() {
		// Prepare elements that will form part of the pop up panel

		// Users can select type of annotation
		// TODO: Check in data mode which are the annotation types
		final Label lAnnotType = new Label();
		lAnnotType.setText("Type: ");
		lbAnnotType = new ListBox();
		lbAnnotType.addItem("User Comment");
		lbAnnotType.addItem("Citation");
		lbAnnotType.addItem("Other...");

		// Users can record a motivation for their new annotation
		final Label lMotivation = new Label();
		lMotivation.setText("Motivation:");
		lbMotivation = new ListBox();
		lbMotivation.addItem("bookmarking");
		lbMotivation.addItem("classifying");
		lbMotivation.addItem("commenting");
		lbMotivation.addItem("describing");
		lbMotivation.addItem("editing");
		lbMotivation.addItem("highlighting");
		lbMotivation.addItem("identifying");
		lbMotivation.addItem("linking");
		lbMotivation.addItem("moderating");
		lbMotivation.addItem("questioning");
		lbMotivation.addItem("replying");
		lbMotivation.addItem("tagging");

		// Users can insert a series of tags
		// TODO: see how it's done in the plug-in
		final Label lTags = new Label();
		lTags.setText("Tags: ");
		tbTags = new TextBox();

		final HorizontalPanel hpAnnotType = new HorizontalPanel();
		hpAnnotType.add(lAnnotType);
		hpAnnotType.add(lbAnnotType);
		hpAnnotType.add(lMotivation);
		hpAnnotType.add(lbMotivation);
		hpAnnotType.add(lTags);
		hpAnnotType.add(tbTags);
		hpAnnotType.setCellHorizontalAlignment(lMotivation,
				HasHorizontalAlignment.ALIGN_CENTER);
		hpAnnotType.setCellHorizontalAlignment(lbMotivation,
				HasHorizontalAlignment.ALIGN_CENTER);
		hpAnnotType.setCellHorizontalAlignment(lTags,
				HasHorizontalAlignment.ALIGN_RIGHT);
		hpAnnotType.setCellHorizontalAlignment(tbTags,
				HasHorizontalAlignment.ALIGN_RIGHT);

		lbDateFormat = new ListBox();
		lbDateFormat.addItem("DD/MM/YYYY hh:mm:ss");
		lbDateFormat.addItem("MJD2000");
		lbDateFormat.addItem("YYYYMMDDThh:mm:ss");
		final Label lDateFormat = new Label();
		lDateFormat.setText("Format: ");

		lbBegin = new ListBox();
		lbBegin.addItem("0");
		lbBegin.addItem("-5");
		lbBegin.addItem("-10");
		lbBegin.addItem("-15");
		lbEnd = new ListBox();
		lbEnd.addItem("0");
		lbEnd.addItem("-5");
		lbEnd.addItem("-10");
		lbEnd.addItem("-15");
		final Label lDepth = new Label();
		lDepth.setText("Choose depth boundaries:");
		final Label lBegin = new Label();
		lBegin.setText("Begin:");
		final Label lEnd = new Label();
		lEnd.setText("End:");
		final HorizontalPanel hpDepth = new HorizontalPanel();
		hpDepth.add(lBegin);
		hpDepth.add(lbBegin);
		hpDepth.add(lEnd);
		hpDepth.add(lbEnd);
		final VerticalPanel vpDepth = new VerticalPanel();
		vpDepth.add(lDepth);
		vpDepth.add(hpDepth);

		// Users can select one or more applicable variables
		// TODO: get var list from dataset
		final Label lVars = new Label();
		lVars.setText("Which variables in the dataset does your comment apply to?");
		lbVars = new ListBox();
		lbVars.addItem("sea_ice_fraction");
		lbVars.addItem("mask");
		lbVars.addItem("analysed_sst");
		// Make enough room for all variable items (setting this value to 1
		// turns it
		// into a drop-down list)s
		// TODO: visible item count initialised to number of variables read from
		// the dataset
		lbVars.setVisibleItemCount(5);
		// An annotation can be about a different number of variables
		lbVars.setMultipleSelect(true);
		final VerticalPanel vpVars = new VerticalPanel();
		vpVars.add(lVars);
		vpVars.add(lbVars);

		taAnnot = new TextArea();
		taAnnot.setWidth("400");

		// Label containing an explanation about what the pop up is trying to
		// annotate
		final Label lIntro = new Label();
		lIntro.setText("Insert a comment about this dataset subset:");

		// Panel containing the intro label and the text area
		final VerticalPanel vpAnnot = new VerticalPanel();
		vpAnnot.add(hpAnnotType);
		vpAnnot.add(lIntro);
		vpAnnot.add(taAnnot);

		// Users can confirm or cancel the comment they've made about the subset
		okButton = new Button();
		okButton.setText("OK");

		cancelButton = new Button();
		cancelButton.setText("Cancel");

		// A flow panel will contain the OK and Cancel buttons
		final FlowPanel fpButtons = new FlowPanel();
		fpButtons.add(cancelButton);
		fpButtons.add(okButton);

		// TODO: Add calendar for time period selection based on dataset's
		// covered period
		// Leave Some text boxes by now...
		final Label lDateBegin = new Label();
		lDateBegin.setText("Begin: ");
		final Label lDateEnd = new Label();
		lDateEnd.setText("End: ");
		tbBegin = new TextBox();
		tbEnd = new TextBox();

		// A Horizontal Panel will contain these
		final HorizontalPanel hpPeriod = new HorizontalPanel();
		hpPeriod.add(lDateFormat);
		hpPeriod.add(lbDateFormat);
		hpPeriod.add(lDateBegin);
		hpPeriod.add(tbBegin);
		hpPeriod.add(lDateEnd);
		hpPeriod.add(tbEnd);

		// TODO: Add something for users to insert a set of coordinates or a
		// polygon... Maybe some text area for WKT areas?
		final Label lCoords = new Label();
		lCoords.setText("Introduce a WKT description of the geographical subset: ");
		taGeometry = new TextArea();
		taGeometry.setWidth("400");

		// A Vertical Panel will contain these
		final VerticalPanel vpGeometry = new VerticalPanel();
		vpGeometry.add(lCoords);
		vpGeometry.add(taGeometry);

		final VerticalPanel vpPopup = new VerticalPanel();
		vpPopup.setSize("600", "450");
		vpPopup.add(vpAnnot);
		vpPopup.add(vpGeometry);
		vpPopup.add(hpPeriod);
		vpPopup.add(vpDepth);
		vpPopup.add(vpVars);
		vpPopup.add(fpButtons);
		vpPopup.setCellHorizontalAlignment(fpButtons,
				HasHorizontalAlignment.ALIGN_CENTER);
		vpPopup.setCellVerticalAlignment(fpButtons,
				HasVerticalAlignment.ALIGN_BOTTOM);

		dialogBox = new DialogBoxWithCloseButton(false);
		dialogBox.setWidget(vpPopup);
		dialogBox.setText("New Annotation");
		dialogBox.center();
		

		// Button handlers
		okButton.addClickHandler(this);
		cancelButton.addClickHandler(this);
	}

	@Override
	public void setPresenter(NewAnnotationPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void initialiseView(LonLat lonlat) {
		// TODO: create hierarchy of objects (point, line, polygon), each one with a method to create the appropriate string
		taGeometry.setText("POINT(" + lonlat.lon() + " " + lonlat.lat() + ")");
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == okButton) {
			onOkButtonClicked();
		} else if (event.getSource() == cancelButton) {
			onCancelButtonClicked();
		}
	}

	private void onOkButtonClicked() {
		// Check we have the necessary inputs from user
		// mandatory: user comment
		// dates make sense (ie stop is not before start, formats, etc)
		// WKT is correct
		// depth boundaries make sense
		// at least one var chosen

		// Collect users input:

		String type = lbAnnotType.getItemText(lbAnnotType.getSelectedIndex());
		String motivation = lbMotivation.getItemText(lbMotivation
				.getSelectedIndex());
		String tags = tbTags.getText();

		String comment = taAnnot.getText();

		String wktText = taGeometry.getText();

		String timeFormat = lbDateFormat.getItemText(lbAnnotType
				.getSelectedIndex());
		String valStart = tbBegin.getText();
		String valStop = tbEnd.getText();

		String depthStart = lbBegin.getItemText(lbBegin.getSelectedIndex());
		String depthStop = lbEnd.getItemText(lbEnd.getSelectedIndex());

		List<String> variables = new ArrayList<String>();
		for (int i = 0; i < lbVars.getItemCount(); i++) {
			if (lbVars.isItemSelected(i))
				variables.add(lbVars.getItemText(i));
		}

		// TODO: validate data before sending them to presenter 
		
		// Send to CHARMe Node
		presenter
				.onOkClicked(type, motivation, tags, comment, wktText,
						timeFormat, valStart, valStop, depthStart, depthStop,
						variables);

	}

	private void onCancelButtonClicked() {
		presenter.onCancelClicked();
	}

	@Override
	public void close() {
		dialogBox.hide();
	}

}
