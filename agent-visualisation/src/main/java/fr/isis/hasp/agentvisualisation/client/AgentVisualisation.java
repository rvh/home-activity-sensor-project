package fr.isis.hasp.agentvisualisation.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;

import fr.isis.hasp.agentvisualisation.shared.Evenement;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AgentVisualisation implements EntryPoint {

	public static final int LIMIT = 1500;

	public static final String CAPTEUR_MOUVEMENT = "CapteurMouvement";
	public static final String CAPTEUR_SON = "CapteurSon";
	public static final String CHANGEMENT_PIECE = "ChangementPiece";
	public static final String CAPTEUR_LUMIERE = "CapteurLumiere";
	public static final String CAPTEUR_TEMPERATURE = "CapteurTemperature";
	public static final String CAPTEUR_COUSSIN = "CapteurCoussin";
	public static final String ENDORMISSEMENT = "Endormissement";
	public static final String REVEIL = "Reveil";
	public static final String CAPTEUR_CO2 = "NombrePersonne";

	public static final String FORMAT_DATE_IHM = "dd/MM/yyyy - HH:mm";
	public static final String FORMAT_DATE_SQL = "dd/MM/yyyy - HH:mm:ss";

	public static final String[] CATEGORIES_MESSAGES = { CAPTEUR_MOUVEMENT,
			CAPTEUR_SON, CHANGEMENT_PIECE, CAPTEUR_LUMIERE,
			CAPTEUR_TEMPERATURE, CAPTEUR_COUSSIN, ENDORMISSEMENT, REVEIL,
			CAPTEUR_CO2 };

	private VerticalPanel vpCentre;
	private boolean raffraichir = false;

	private ToggleButton afficherDerniersMessages;
	private Button chercher;

	private ArrayList<CheckBox> listCategoriesBox;
	
	private Timer timer = new Timer() {
		public void run() {
			service.getDerniersEvenements(getSelectedCategories(), new AsyncCallback<ArrayList<Evenement>>() {

				public void onFailure(Throwable arg0) {
					fail(arg0.getLocalizedMessage());
				}

				public void onSuccess(ArrayList<Evenement> liste) {
					success(liste);
				}

			});
			timer.schedule(5000);
		}
	};

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final IServiceAsync service = GWT.create(IService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		HorizontalPanel hpGlobal = new HorizontalPanel();

		VerticalPanel vpGauche = new VerticalPanel();

		// Filtres
		VerticalPanel vpFiltres = new VerticalPanel();

		DateTimeFormat formatDate = DateTimeFormat.getFormat(FORMAT_DATE_IHM);

		final DateBox dateDebut = new DateBox();
		dateDebut.setWidth("165px");
		dateDebut.setFormat(new DateBox.DefaultFormat(formatDate));

		final DateBox dateFin = new DateBox();
		dateFin.setWidth("165px");
		dateFin.setFormat(new DateBox.DefaultFormat(formatDate));

		listCategoriesBox = new ArrayList<CheckBox>();
		for (String cat : CATEGORIES_MESSAGES) {
			CheckBox checkBox = new CheckBox(cat);
			listCategoriesBox.add(checkBox);
		}

		chercher = new Button("Chercher");
		chercher.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {
				afficherDerniersMessages.setDown(false);
				afficherDerniersMessages(false);

				// Vérification des données
				if (dateDebut.getValue() != null && dateFin.getValue() != null
						&& dateDebut.getValue().after(dateFin.getValue())) {
					Window.alert("La date de début est suppérieure à la date de fin.\nVeuillez changer les filtres de recherhe.");
				} else {

					// Service comptage du nombre de résultats
					// Appel du service
					service.getCountEvenements(dateDebut.getValue(),
							dateFin.getValue(), getSelectedCategories(),
							new AsyncCallback<Integer>() {

								public void onFailure(Throwable arg0) {
									arg0.printStackTrace();
									fail(arg0.getLocalizedMessage());
								}

								public void onSuccess(Integer count) {
									if (count > LIMIT) {
										Window.alert("Les filtres de recherche retournent "
												+ count
												+ " resultats.\nVeuillez changer les filtres afin de retourner 300 résultats au plus.");
									} else {
										retourCountResults(
												dateDebut.getValue(),
												dateFin.getValue(), getSelectedCategories());
									}
								}

							});
				}
			}
		});

		afficherDerniersMessages = new ToggleButton("Derniers messages");
		afficherDerniersMessages.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {
				afficherDerniersMessages(afficherDerniersMessages.isDown());
			}
		});
		afficherDerniersMessages.setWidth("120px");

		// Assemblage filtres
		vpFiltres.setWidth("165px");

		vpFiltres.add(new HTML(
				"<div style=\"color:#ADADAD;font-size:16pt\">Filtres</div>"));
		vpFiltres.add(new HTML("Date de début :"));
		vpFiltres.add(SpacerFactory.getVSpacer(6));
		vpFiltres.add(dateDebut);

		vpFiltres.add(SpacerFactory.getVSpacer(6));
		vpFiltres.add(new HTML("Date de fin :"));
		vpFiltres.add(SpacerFactory.getVSpacer(6));
		vpFiltres.add(dateFin);

		vpFiltres.add(SpacerFactory.getVSpacer(6));
		vpFiltres.add(new HTML("&Eacute;vènements à afficher :"));
		vpFiltres.add(SpacerFactory.getVSpacer(6));

		for (CheckBox box : listCategoriesBox) {
			vpFiltres.add(box);
			vpFiltres.add(SpacerFactory.getVSpacer(2));
			box.setValue(true);
		}

		vpFiltres.add(SpacerFactory.getVSpacer(6));
		vpFiltres.add(chercher);

		vpFiltres.add(SpacerFactory.getVSpacer(6));
		vpFiltres.add(afficherDerniersMessages);

		// Resultats
		vpCentre = new VerticalPanel();

		// Assemblage ROOT
		vpGauche.add(vpFiltres);

		hpGlobal.add(vpGauche);
		hpGlobal.add(SpacerFactory.getHSpacer(20));
		hpGlobal.add(vpCentre);

		RootPanel.get("main").add(hpGlobal);
	}
	
	private ArrayList<String> getSelectedCategories(){
		final ArrayList<String> querry = new ArrayList<String>();
		for (CheckBox box : listCategoriesBox) {
			if (box.getValue())
				querry.add(box.getText());
		}
		return querry;
	}

	private void afficherDerniersMessages(boolean isDown) {
		raffraichir = isDown;

		if (raffraichir)
			timer.run();
		else
			timer.cancel();
	}

	private void retourCountResults(Date dateDebut, Date dateFin,
			ArrayList<String> listeCategories) {
		// Appel du service
		service.getEvenements(dateDebut, dateFin, listeCategories,
				new AsyncCallback<ArrayList<Evenement>>() {

					public void onFailure(Throwable arg0) {
						arg0.printStackTrace();
						fail(arg0.getLocalizedMessage());
					}

					public void onSuccess(ArrayList<Evenement> liste) {
						success(liste);
					}

				});
	}

	private void success(ArrayList<Evenement> listeCategories) {
		vpCentre.clear();

		vpCentre.add(new HTML(
				"<div style=\"color:#ADADAD;font-size:16pt\">Resultats</div>"));

		// Data provider
		ListDataProvider<Evenement> dataProvider = new ListDataProvider<Evenement>();

		// DATA GRID
		DataGrid<Evenement> dataGrid = new DataGrid<Evenement>();
		dataGrid.setHeight("550px");

		dataGrid.setEmptyTableWidget(new Label(
				"Aucun résultat ne correspond aux filtres sélectionnés."));

		// SortHandler
		ListHandler<Evenement> sortHandler = new ListHandler<Evenement>(
				dataProvider.getList());
		dataGrid.addColumnSortHandler(sortHandler);
		// Pager
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		SimplePager pager;
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(dataGrid);
		dataGrid.setPageSize(20);

		// COLUMNS
		// First name.
		Column<Evenement, String> firstNameColumn = new Column<Evenement, String>(
				new TextCell()) {
			@Override
			public String getValue(Evenement object) {
				String nom = "";
				if (object.getCategorie() != null)
					nom = object.getCategorie();
				return nom;
			}
		};
		firstNameColumn.setSortable(true);

		sortHandler.setComparator(firstNameColumn, new Comparator<Evenement>() {
			public int compare(Evenement o1, Evenement o2) {
				return o1.getCategorie().compareToIgnoreCase(o2.getCategorie());
			}
		});
		dataGrid.addColumn(firstNameColumn, "Catégorie du capteur");

		// Date.
		Column<Evenement, String> dateColumn = new Column<Evenement, String>(
				new TextCell()) {
			@Override
			public String getValue(Evenement object) {
				String date = "";
				if (object.getDateString() != null)
					date = object.getDateString();
				return date;
			}
		};
		dateColumn.setSortable(true);

		sortHandler.setComparator(dateColumn, new Comparator<Evenement>() {
			public int compare(Evenement o1, Evenement o2) {
				if (o1.getDate().before(o2.getDate()))
					return -1;
				else
					return 1;
			}
		});
		dataGrid.addColumn(dateColumn, "Date");

		// Date.
		Column<Evenement, String> numColumn = new Column<Evenement, String>(
				new TextCell()) {
			@Override
			public String getValue(Evenement object) {
				return "n°" + object.getNumero();
			}
		};
		numColumn.setSortable(true);

		sortHandler.setComparator(numColumn, new Comparator<Evenement>() {
			public int compare(Evenement o1, Evenement o2) {
				if (o1.getNumero() < o2.getNumero())
					return -1;
				else
					return 1;
			}
		});
		dataGrid.addColumn(numColumn, "Numéro du capteur");

		// Date.
		Column<Evenement, String> valColumn = new Column<Evenement, String>(
				new TextCell()) {
			@Override
			public String getValue(Evenement object) {
				return "" + object.getValeur();
			}
		};
		valColumn.setSortable(true);

		sortHandler.setComparator(valColumn, new Comparator<Evenement>() {
			public int compare(Evenement o1, Evenement o2) {
				if (o1.getValeur() < o2.getValeur())
					return -1;
				else
					return 1;
			}
		});
		dataGrid.addColumn(valColumn, "Valeur");
		// Fin

		dataProvider.addDataDisplay(dataGrid);
		dataProvider.getList().addAll(listeCategories);

		vpCentre.add(dataGrid);
		vpCentre.add(pager);

		dataGrid.setWidth("790px");
	}

	private void fail(String err) {
		vpCentre.add(new HTML(
				"<div style=\"color:#ADADAD;font-size:16pt\">Resultats</div>"));
		vpCentre.add(new HTML(
				"<div style=\"color:red;\">Une erreur a eu lieu lors de l'appel du service.<br/>"
						+ err + "</div>"));
	}
}