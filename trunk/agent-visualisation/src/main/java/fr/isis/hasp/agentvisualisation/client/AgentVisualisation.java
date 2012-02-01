package fr.isis.hasp.agentvisualisation.client;

import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import fr.isis.hasp.objetsmetier.Constantes;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AgentVisualisation implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		VerticalPanel vp = new VerticalPanel();

		// Filtres
		VerticalPanel vpFiltres  = new VerticalPanel();
		
		DateTimeFormat formatDate = DateTimeFormat.getFormat(Constantes.FORMAT_DATE_IHM);
		
		DateBox dateDebut = new DateBox();
		dateDebut.setFormat(new DateBox.DefaultFormat(formatDate));
		
		DateBox dateFin = new DateBox();
		dateFin.setFormat(new DateBox.DefaultFormat(formatDate));
		
		// Assemblage filtres
		vpFiltres.add(new HTML("Date de d&eacute;but :"));
		vpFiltres.add(dateDebut);
		vpFiltres.add(new HTML("Date de fin :"));
		vpFiltres.add(dateFin);
		
		vp.add(vpFiltres);

		RootPanel.get().add(vp);
	}
}
