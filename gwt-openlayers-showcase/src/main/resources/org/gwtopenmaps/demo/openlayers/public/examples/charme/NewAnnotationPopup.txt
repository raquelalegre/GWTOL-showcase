package org.gwtopenmaps.demo.openlayers.client.examples.charme;

import java.util.ArrayList;
import java.util.List;

import org.gwtopenmaps.demo.openlayers.client.DialogBoxWithCloseButton;
import org.gwtopenmaps.openlayers.client.LonLat;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
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

public class NewAnnotationPopup {

	// constructor will eventually need geo boundaries, time boundaries, depth
	// boundaries and current variable name
	public NewAnnotationPopup(LonLat lonlat) {
		// Prepare elements that will form part of the pop up panel

		// Users can select type of annotation
		// TODO: Check in data mode which are the annotation types
		final Label lAnnotType = new Label();
		lAnnotType.setText("Type: ");
		final ListBox lbAnnotType = new ListBox();
		lbAnnotType.addItem("User Comment");
		lbAnnotType.addItem("Citation");
		lbAnnotType.addItem("Other...");

		// Users can record a motivation for their new annotation
		final Label lMotivation = new Label();
		lMotivation.setText("Motivation:");
		final ListBox lbMotivation = new ListBox();
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
		final TextBox tbTags = new TextBox();

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

		// Users can choose date format for specifying a time subset
		// TODO: Ask PK what other formats
		// TODO: Function to translate times to xsd:dateTime and viceversa
		// TODO: Get from data start and end of period to prevent users from
		// introducing dates out of bounds
		final ListBox lbDateFormat = new ListBox();
		lbDateFormat.addItem("DD/MM/YYYY hh:mm:ss");
		lbDateFormat.addItem("MJD2000");
		lbDateFormat.addItem("YYYYMMDDThh:mm:ss");
		final Label lDateFormat = new Label();
		lDateFormat.setText("Format: ");

		// Users can select a depth subset
		// TODO: Get depth boundaries from dataset to prevent out of bounds
		// TODO: Units?
		final ListBox lbBegin = new ListBox();
		lbBegin.addItem("0");
		lbBegin.addItem("-5");
		lbBegin.addItem("-10");
		lbBegin.addItem("-15");
		final ListBox lbEnd = new ListBox();
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
		final ListBox lbVars = new ListBox();
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

		// Users can insert in the text area their free text comment about the
		// subset
		final TextArea taAnnot = new TextArea();
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
		final Button okButton = new Button();
		okButton.setText("OK");

		final Button cancelButton = new Button();
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
		final TextBox tbBegin = new TextBox();
		final TextBox tbEnd = new TextBox();

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
		final TextArea taGeometry = new TextArea();
		taGeometry.setWidth("400");
		taGeometry.setText("POINT(" + lonlat.lon() + " " + lonlat.lat() + ")");

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

		// Create new
		final DialogBoxWithCloseButton dialogBox = new DialogBoxWithCloseButton(
				false);
		dialogBox.setWidget(vpPopup);
		dialogBox.center();
		dialogBox.setText("New Annotation");

		// Button handlers
		okButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				// Check we have the necessary inputs from user
				// mandatory: user comment
				// dates make sense (ie stop is not before start, formats, etc)
				// WKT is correct
				// depth boundaries make sense
				// at least one var chosen

				// Save users input:
				String type = lbAnnotType.getItemText(lbAnnotType
						.getSelectedIndex());
				String tags = tbTags.getText();
				String motivation = lbMotivation.getItemText(lbMotivation
						.getSelectedIndex());
				String content = taAnnot.getText();
				String geometry = taGeometry.getText();
				String timeFormat = lbDateFormat.getItemText(lbAnnotType
						.getSelectedIndex());
				String validityStart = tbBegin.getText();
				String validityStop = tbEnd.getText();
				String depthStart = lbBegin.getItemText(lbBegin
						.getSelectedIndex());
				String depthStop = lbEnd.getItemText(lbEnd.getSelectedIndex());
				List<String> variables = new ArrayList<String>();
				for (int i = 0; i < lbVars.getItemCount(); i++) {
					if (lbVars.isItemSelected(i))
						variables.add(lbVars.getItemText(i));
				}

				// Create new Annotation object with user's input

				// Initialise a SubsetSelector
				SubsetSelector ss = new SubsetSelector();
				ss.setGeometry(geometry);
				ss.setTimeFormat(timeFormat);
				ss.setValidityStart(validityStart);
				ss.setValidityStop(validityStop);
				ss.setVariables(variables);
				ss.setDepthStart(depthStart);
				ss.setDepthStop(depthStop);

				// Initialise a SpecificResource object
				SpecificResource sr = new SpecificResource();
				sr.setSelector(ss);
				String source = null; // URI of the dataset
				sr.setSource(source);
				Annotation annotation = new Annotation();
				String body = null; // URI of the subset selector?
				annotation.setBody(body);
				String target = null; // URI of the subset selector
				annotation.setTarget(target);
				annotation.setType(type);
				annotation.setMotivation(motivation);
				annotation.setContent(content);
				annotation.setSpecificResource(sr);
				annotation.setSubsetSelector(ss);

				//Serialise (Java Object -> JSON) using GSON
				//Gson gson = new Gson();
				//gson.toJson(annotation);
				//Gson gson = new GsonBuilder().setPrettyPrinting().create();
				//String json = gson.toJson(annotation);
				//System.out.println(json);

				// Send to CHARMe Node
				

			}
		});
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

	}

}
