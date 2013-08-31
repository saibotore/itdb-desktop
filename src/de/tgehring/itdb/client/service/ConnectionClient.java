/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.service;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import de.tgehring.itdb.client.desktop.model.Benutzer;
import de.tgehring.itdb.client.desktop.utils.DBUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionClient.
 */
public class ConnectionClient {

	/** The instance. */
	private static ConnectionClient instance = null;
	
	/** The config. */
	private ClientConfig config;
	
	/** The client. */
	private Client client;
	
	/** The service. */
	private WebResource service;
	
	/** The url. */
	private String url;

	/**
	 * Instantiates a new connection client.
	 */
	private ConnectionClient() {
		url = new Options().getUrl();
		config = new DefaultClientConfig();
		client = Client.create(config);
		service = client.resource(getBaseURI());
	}

	/**
	 * Gets the single instance of ConnectionClient.
	 *
	 * @return single instance of ConnectionClient
	 */
	public static ConnectionClient getInstance() {
		if (instance == null) {
			instance = new ConnectionClient();
		}
		return instance;
	}
	
	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		new Options().setUrl(url);
		this.url = url;
	}
	
	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return new Options().getUrl();
	}

	/**
	 * Login.
	 *
	 * @param benutzername the benutzername
	 * @param passwort the passwort
	 */
	public void login(String benutzername, String passwort) {
		String md5Password = DBUtils.md5(passwort);
		Benutzer benutzer = service.path("login").path(benutzername)
				.path(md5Password).accept(MediaType.APPLICATION_JSON)
				.get(Benutzer.class);
		DesktopSession.getInstance().setBenutzer(benutzer);
	}

	/**
	 * Logoff.
	 */
	public void logoff() {
		service.path("login").path("logoff").accept(MediaType.TEXT_PLAIN)
				.get(String.class);
		DesktopSession.getInstance().setBenutzer(null);
	}

	/**
	 * Gets the base uri.
	 *
	 * @return the base uri
	 */
	public URI getBaseURI() {
		return UriBuilder.fromUri(url).build();
	}
	
}
