package fr.isis.hasp.agentvisualisation.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AgentVisualisation implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  VerticalPanel vp = new VerticalPanel();
		vp.add(new HTML("Hello GWT :)"));
		
		RootPanel.get().add(vp);
  }
}
