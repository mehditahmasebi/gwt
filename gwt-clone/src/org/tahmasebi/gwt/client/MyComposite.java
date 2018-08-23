package org.tahmasebi.gwt.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class MyComposite extends Composite {
	TextBox txtbox = new TextBox();
	Label lbl = new Label("LBL");
	
	public MyComposite() {
		HorizontalPanel hpMain = new HorizontalPanel();
		hpMain.add(txtbox)		;
		hpMain.add(lbl);
		initWidget(hpMain);;
	}

}
