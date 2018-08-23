package org.tahmasebi.gwt.client;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.query.client.Function;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_clone implements EntryPoint {
	Grid gdMain = new Grid(10, 2);
	public void onModuleLoad()
	{
		gdMain.getElement().setId("gdMain");
		gdMain.insertRow(0);
		gdMain.insertRow(1);
		ListBox lstCity = new ListBox();
		lstCity.addItem("Tehran","021");
		lstCity.addItem("Shirazoo","071");
		lstCity.getElement().setId("lstCity");
		
		ListBox lstCountry = new ListBox();
		lstCountry.addItem("Iran","0098");
		lstCountry.addItem("UK","0044");
		lstCountry.getElement().setId("lstCountry");
		
		gdMain.setWidget(0, 0, lstCity);
		gdMain.setWidget(0, 1, lstCountry);
		
		TextBox txtName = new TextBox();
		txtName.getElement().setId("txtname");
		txtName.setText("Mehdi");
		gdMain.setWidget(1, 0, txtName);
		
		HorizontalPanel hpRadio = new HorizontalPanel();
		RadioButton rdMale = new RadioButton("Gender","Male");
		RadioButton rdFemale = new RadioButton("Gender","Female");
		hpRadio.add(rdMale);hpRadio.add(rdFemale);
		gdMain.setWidget(2, 1, hpRadio);
		
		HorizontalPanel hpChecked = new HorizontalPanel();
		CheckBox chkMale = new CheckBox("Male");
		CheckBox chkFemale = new CheckBox("Female");
		hpChecked.add(chkMale);hpChecked.add(chkFemale);
		gdMain.setWidget(3, 1, hpChecked);;
		
		gdMain.setWidget(4, 0, new MyComposite());

		Button btnShowPopup = new Button("Popup");
		btnShowPopup.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{				
//				Window.alert($("#lstCity").val());
//				Window.alert($("option:selected").text());
//				Window.alert($("option:selected").first().text());
//				$("option:selected").each(new Function() {
//					public void f(Element element)
//					{
//						Window.alert(element.getInnerText());
//					}
//				});
//				$("#gdMain option:selected").each(new Function() {
//					public void f()
//					{
//						Window.alert($(this).text());
//					}
//				});
				
				
//				$("#gdMain" + " input[type='radio']").each(new Function() {
//					public void f(Element element)
//					{						
//						Window.alert($(this).parent().find("input").attr("name"));
//					}
//				});
				showConfirmForm(gdMain);
			}
		});
		gdMain.setWidget(5, 0, btnShowPopup);
		RootPanel.get().add(gdMain);
	}
	int i =0;
	private void showConfirmForm(Widget widget)
	{
		i = 0;		
//		DialogBox dialogBox = new DialogBox();
		PopupPanel dialogBox = new PopupPanel();
		final String cloneWidgetId = DOM.createUniqueId();
//		HTML html = new HTML($("#gdMain").clone().html());
		Element clone = DOM.clone(widget.getElement(), true);
		HTML html = new HTML();
		html.getElement().appendChild(clone);
		html.getElement().setId(cloneWidgetId);
		
		widget.getElement();
		String widgetId = DOM.createUniqueId();
		if(widget.getElement().getId() != null && !widget.getElement().getId().isEmpty())
			widgetId = widget.getElement().getId();
		else {
			widgetId = DOM.createUniqueId();
			widget.getElement().setId(widgetId);
		}
		System.out.println("Original Widget ID : " + widgetId + " clone id : " + cloneWidgetId);
		
		// ######################## Radio Button
		// not working beacuse not attached to dom
		final Set<String> radioNames = new java.util.HashSet<>();
		// find all radio names
		$("#"+widgetId + " input[type='radio']").each(new Function() {
			public void f(Element element)
			{	
//				Window.alert($(this).parent().find("input").attr("id"));
				String attr = $(this).parent().find("input").attr("name");
				radioNames.add(attr);
//				Window.alert(attr);
//				$(this).parent().find("input").attr("name",attr+"_cloned");
			}
		});
		// find checked radios
		final Set<String> checkedRadioIds = new java.util.HashSet<>();
		$("#"+widgetId + " input[type='radio']:checked").each(new Function() {
			public void f(Element element)
			{	
				checkedRadioIds.add($(this).parent().find("input").attr("id"));
			}
		});
		
		// There is a problem TODO
//		String htmlTmp = html.getHTML();
//		if(radioNames != null)
//			for(String radioName : radioNames)
//			{
//				htmlTmp = htmlTmp.replace(radioName, radioName+"_clone");
//			}
//		html.setHTML(htmlTmp);
		NodeList<Element> nodes = html.getElement().getElementsByTagName("input");
		for(int x=0;x<nodes.getLength();x++)
		{
			if(nodes.getItem(x).getAttribute("type") != null)
			{
				if(nodes.getItem(x).getAttribute("type").equalsIgnoreCase("radio"))
				{
					nodes.getItem(x).setAttribute("name", nodes.getItem(x).getAttribute("name")+"_cloned");
				}
			}
		}
	
		// ############################ Show Dialog
		dialogBox.add(html);
		dialogBox.center();
		dialogBox.setAnimationEnabled(true);
		dialogBox.setAutoHideEnabled(true);
		
		// ############################# disable form
		$("#"+cloneWidgetId + " input").each(new Function() {
				public void f()
				{
					$(this).attr("disabled",true);
				}
			});
		
		// ############################ Combobox
		final List<String> selectedComboboxValue = new ArrayList<>();
		$("#"+widgetId + " select").each(new Function() {
			public void f(Element element)
			{
				selectedComboboxValue.add($(this).val());
			}
		});

		$("#"+cloneWidgetId).ready(new Function() {
			public void f()
			{
				
			}
		});
		$("#"+cloneWidgetId).css("background-color","khaki");
		$("#"+cloneWidgetId + " select").each(new Function() {
			public void f(Element element)
			{
				$(this).val(selectedComboboxValue.get(i++));
			}
		});
		
		// ############################ RadioButton Check in clone
		if(checkedRadioIds != null)
			for(String radioId : checkedRadioIds)
			{
				$("#"+cloneWidgetId + " #"+radioId).prop("checked",true);
			}
	}
	public void traverse(Element element)
	{
		if(element == null)
			return;
	}
}
