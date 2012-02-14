package fr.isis.hasp.agentvisualisation.client;

import com.google.gwt.user.client.ui.HTML;

public class SpacerFactory {

	public static HTML getHSpacer(int size) {
		return new HTML("<div style='width:"+size+"px;'></div>");
	}

	public static HTML getVSpacer(int size) {
		return new HTML("<div style='height:"+size+"px;'></div>");
	}

}
