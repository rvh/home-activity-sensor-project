package fr.isis.hasp.agentvisualisation.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.isis.hasp.agentvisualisation.shared.Evenement;

@RemoteServiceRelativePath("service")
public interface IService extends RemoteService {

	public ArrayList<Evenement> getEvenements(Date dateDebut, Date dateFin, ArrayList<String> categories);
	public Integer getCountEvenements(Date dateDebut, Date dateFin, ArrayList<String> categories);
	public ArrayList<Evenement> getDerniersEvenements(ArrayList<String> cat);
}
