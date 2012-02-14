package fr.isis.hasp.agentvisualisation.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import fr.isis.hasp.agentvisualisation.shared.Evenement;

public interface IServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see fr.isis.hasp.agentvisualisation.client.IService
     */
    void getEvenements( java.util.Date p0, java.util.Date p1, ArrayList<String> categories, AsyncCallback<java.util.ArrayList<Evenement>> callback );
    void getCountEvenements( java.util.Date p0, java.util.Date p1, ArrayList<String> categories, AsyncCallback<Integer> callback );
    void getDerniersEvenements( ArrayList<String> cat, AsyncCallback<ArrayList<Evenement>> callback );

    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static IServiceAsync instance;

        public static final IServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (IServiceAsync) GWT.create( IService.class );
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint( GWT.getModuleBaseURL() + "service" );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
