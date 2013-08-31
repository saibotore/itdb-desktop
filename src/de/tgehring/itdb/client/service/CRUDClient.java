/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.service;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import de.tgehring.itdb.client.desktop.model.Abteilung;
import de.tgehring.itdb.client.desktop.model.Benutzer;
import de.tgehring.itdb.client.desktop.model.Cpu;
import de.tgehring.itdb.client.desktop.model.Drucker;
import de.tgehring.itdb.client.desktop.model.Dvm;
import de.tgehring.itdb.client.desktop.model.Gebäude;
import de.tgehring.itdb.client.desktop.model.Gpu;
import de.tgehring.itdb.client.desktop.model.Hersteller;
import de.tgehring.itdb.client.desktop.model.HerstellerTyp;
import de.tgehring.itdb.client.desktop.model.Inventarnummer;
import de.tgehring.itdb.client.desktop.model.Lieferant;
import de.tgehring.itdb.client.desktop.model.Monitor;
import de.tgehring.itdb.client.desktop.model.Rechner;
import de.tgehring.itdb.client.desktop.model.Rechnung;
import de.tgehring.itdb.client.desktop.model.Software;
import de.tgehring.itdb.client.desktop.model.Tablet;
import de.tgehring.itdb.client.desktop.model.Todo;

// TODO: Auto-generated Javadoc
/**
 * The Class CRUDClient.
 */
public class CRUDClient {
	
	/** The instance. */
	private static CRUDClient instance = null;
	
	/** The config. */
	ClientConfig config;
	
	/** The client. */
	Client client;
	
	/** The service. */
	WebResource service;

	/**
	 * Instantiates a new cRUD client.
	 */
	private CRUDClient() {
		config = new DefaultClientConfig();
		client = Client.create(config);
		service = client.resource(getBaseURI());
	}

	/**
	 * Gets the single instance of CRUDClient.
	 *
	 * @return single instance of CRUDClient
	 */
	public static CRUDClient getInstance() {
		if (instance == null) {
			instance = new CRUDClient();
		}
		return instance;
	}
	
	/**
	 * Adds the abteilung.
	 *
	 * @param abteilung the abteilung
	 * @return the client response
	 */
	public ClientResponse addAbteilung(final Abteilung abteilung) {
		ClientResponse res = null;
		ResultSetter setter = new ResultSetter() {
		    public void setResult(ClientResponse result) {
//		      res = result;
		    }
		  };

		String path = "/crud/abteilung";
		service = client.resource(buildURI(path));
//		GetThread thread = new GetThread(service);
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(abteilung);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the abteilung.
	 *
	 * @param abteilung the abteilung
	 * @return the client response
	 */
	public ClientResponse editAbteilung(Abteilung abteilung) {
		String path = "/crud/abteilung";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(abteilung);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete abteilung.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteAbteilung(long id) {
		String path = "/crud/abteilung/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the abteilung.
	 *
	 * @param id the id
	 * @return the abteilung
	 */
	public Abteilung getAbteilung(long id) {
		String path = "/crud/abteilung/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Abteilung.class);
	}
	
	/**
	 * Gets the all abteilung.
	 *
	 * @return the all abteilung
	 */
	public List<Abteilung> getAllAbteilung() {
		String path = "/crud/abteilung";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Abteilung>>() {
				});
	}
	
	/**
	 * Adds the benutzer.
	 *
	 * @param benutzer the benutzer
	 * @return the client response
	 */
	public ClientResponse addBenutzer(Benutzer benutzer) {
		String path = "/crud/benutzer";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(benutzer);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the benutzer.
	 *
	 * @param benutzer the benutzer
	 * @return the client response
	 */
	public ClientResponse editBenutzer(Benutzer benutzer) {
		String path = "/crud/benutzer";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(benutzer);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					   .put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete benutzer.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteBenutzer(long id) {
		String path = "/crud/benutzer/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json").delete(ClientResponse.class);
	}
	
	/**
	 * Gets the benutzer.
	 *
	 * @param id the id
	 * @return the benutzer
	 */
	public Benutzer getBenutzer(long id) {
		String path = "/crud/benutzer/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Benutzer.class);
	}

	/**
	 * Gets the all benutzer.
	 *
	 * @return the all benutzer
	 */
	public List<Benutzer> getAllBenutzer() {
		String path = "/crud/benutzer";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Benutzer>>() {});
	}
	
	/**
	 * Adds the cpu.
	 *
	 * @param cpu the cpu
	 * @return the client response
	 */
	public ClientResponse addCpu(Cpu cpu) {
		String path = "/crud/cpu";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(cpu);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the cpu.
	 *
	 * @param cpu the cpu
	 * @return the client response
	 */
	public ClientResponse editCpu(Cpu cpu) {
		String path = "/crud/cpu";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(cpu);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete cpu.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteCpu(long id) {
		String path = "/crud/cpu/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the cpu.
	 *
	 * @param id the id
	 * @return the cpu
	 */
	public Cpu getCpu(long id) {
		String path = "/crud/cpu/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Cpu.class);
	}
	
	/**
	 * Gets the all cpu.
	 *
	 * @return the all cpu
	 */
	public List<Cpu> getAllCpu() {
		String path = "/crud/cpu";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Cpu>>() {});
	}
	
	/**
	 * Adds the drucker.
	 *
	 * @param drucker the drucker
	 * @return the client response
	 */
	public ClientResponse addDrucker(Drucker drucker) {
		String path = "/crud/drucker";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(drucker);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the drucker.
	 *
	 * @param drucker the drucker
	 * @return the client response
	 */
	public ClientResponse editDrucker(Drucker drucker) {
		String path = "/crud/drucker";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(drucker);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete drucker.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteDrucker(long id) {
		String path = "/crud/drucker/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the drucker.
	 *
	 * @param id the id
	 * @return the drucker
	 */
	public Drucker getDrucker(long id) {
		String path = "/crud/drucker/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Drucker.class);
	}
	
	/**
	 * Gets the all drucker.
	 *
	 * @return the all drucker
	 */
	public List<Drucker> getAllDrucker() {
		String path = "/crud/drucker";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Drucker>>() {});
	}
	
	/**
	 * Adds the dvm.
	 *
	 * @param dvm the dvm
	 * @return the client response
	 */
	public ClientResponse addDvm(Dvm dvm) {
		String path = "/crud/dvm";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(dvm);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the dvm.
	 *
	 * @param dvm the dvm
	 * @return the client response
	 */
	public ClientResponse editDvm(Dvm dvm) {
		String path = "/crud/dvm";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(dvm);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete dvm.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteDvm(long id) {
		String path = "/crud/dvm/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the dvm.
	 *
	 * @param id the id
	 * @return the dvm
	 */
	public Dvm getDvm(long id) {
		String path = "/crud/dvm/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Dvm.class);
	}
	
	/**
	 * Gets the all dvm.
	 *
	 * @return the all dvm
	 */
	public List<Dvm> getAllDvm() {
		String path = "/crud/dvm";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Dvm>>() {});
	}
	
	/**
	 * Adds the gebäude.
	 *
	 * @param gebäude the gebäude
	 * @return the client response
	 */
	public ClientResponse addGebäude(Gebäude gebäude) {
		String path = "/crud/gebaeude";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(gebäude);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the gebäude.
	 *
	 * @param gebäude the gebäude
	 * @return the client response
	 */
	public ClientResponse editGebäude(Gebäude gebäude) {
		String path = "/crud/gebaeude";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(gebäude);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete gebäude.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteGebäude(long id) {
		String path = "/crud/gebaeude/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the gebäude.
	 *
	 * @param id the id
	 * @return the gebäude
	 */
	public Gebäude getGebäude(long id) {
		String path = "/crud/gebaeude/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Gebäude.class);
	}
	
	/**
	 * Gets the all gebäude.
	 *
	 * @return the all gebäude
	 */
	public List<Gebäude> getAllGebäude() {
		String path = "/crud/gebaeude";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Gebäude>>() {});
		
	}
	
	/**
	 * Adds the gpu.
	 *
	 * @param gpu the gpu
	 * @return the client response
	 */
	public ClientResponse addGpu(Gpu gpu) {
		String path = "/crud/gpu";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(gpu);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the gpu.
	 *
	 * @param gpu the gpu
	 * @return the client response
	 */
	public ClientResponse editGpu(Gpu gpu) {
		String path = "/crud/gpu";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(gpu);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete gpu.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteGpu(long id) {
		String path = "/crud/gpu/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the gpu.
	 *
	 * @param id the id
	 * @return the gpu
	 */
	public Gpu getGpu(long id) {
		String path = "/crud/gpu/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Gpu.class);
	}
	
	/**
	 * Gets the all gpu.
	 *
	 * @return the all gpu
	 */
	public List<Gpu> getAllGpu() {
		String path = "/crud/gpu";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Gpu>>() {});
	}
	
	/**
	 * Adds the hersteller.
	 *
	 * @param hdd the hdd
	 * @return the client response
	 */
	public ClientResponse addHersteller(Hersteller hdd) {
		String path = "/crud/hersteller";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(hdd);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the hersteller.
	 *
	 * @param hersteller the hersteller
	 * @return the client response
	 */
	public ClientResponse editHersteller(Hersteller hersteller) {
		String path = "/crud/hersteller";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(hersteller);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete hersteller.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteHersteller(long id) {
		String path = "/crud/hersteller/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the hersteller.
	 *
	 * @param id the id
	 * @return the hersteller
	 */
	public Hersteller getHersteller(long id) {
		String path = "/crud/hersteller/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Hersteller.class);
	}
	
	/**
	 * Gets the all hersteller.
	 *
	 * @return the all hersteller
	 */
	public List<Hersteller> getAllHersteller() {
		String path = "/crud/hersteller";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Hersteller>>() {});
	}
	
	/**
	 * Gets the all cpu hersteller.
	 *
	 * @return the all cpu hersteller
	 */
	public List<Hersteller> getAllCpuHersteller() {
		List<Hersteller> list = getAllHersteller();
		List<Hersteller> result = new LinkedList<>();
		for(Hersteller hersteller: list) {
			if(hersteller != null) {
				if(hersteller.getTyp().equals(HerstellerTyp.Cpu)) {
					result.add(hersteller);
				}
			}
		}
		return result;
	}
	
	/**
	 * Gets the all gpu hersteller.
	 *
	 * @return the all gpu hersteller
	 */
	public List<Hersteller> getAllGpuHersteller() {
		List<Hersteller> list = getAllHersteller();
		List<Hersteller> result = new LinkedList<>();
		for(Hersteller hersteller: list) {
			if(hersteller != null) {
				if(hersteller.getTyp().equals(HerstellerTyp.Gpu)) {
					result.add(hersteller);
				}
			}
		}
		return result;

	}
	
	/**
	 * Gets the all drucker hersteller.
	 *
	 * @return the all drucker hersteller
	 */
	public List<Hersteller> getAllDruckerHersteller() {
		List<Hersteller> list = getAllHersteller();
		List<Hersteller> result = new LinkedList<>();
		for(Hersteller hersteller: list) {
			if(hersteller != null) {
				if(hersteller.getTyp().equals(HerstellerTyp.Drucker)) {
					result.add(hersteller);
				}
			}
		}
		return result;
	}
	
	/**
	 * Gets the all monitor hersteller.
	 *
	 * @return the all monitor hersteller
	 */
	public List<Hersteller> getAllMonitorHersteller() {
		List<Hersteller> list = getAllHersteller();
		List<Hersteller> result = new LinkedList<>();
		for(Hersteller hersteller: list) {
			if(hersteller != null) {
				if(hersteller.getTyp().equals(HerstellerTyp.Monitor)) {
					result.add(hersteller);
				}
			}
		}
		return result;
	}
	
	/**
	 * Gets the all rechner hersteller.
	 *
	 * @return the all rechner hersteller
	 */
	public List<Hersteller> getAllRechnerHersteller() {
		List<Hersteller> list = getAllHersteller();
		List<Hersteller> result = new LinkedList<>();
		for(Hersteller hersteller: list) {
			if(hersteller != null) {
				if(hersteller.getTyp().equals(HerstellerTyp.Rechner)) {
					result.add(hersteller);
				}
			}
		}
		return result;
	}
	
	/**
	 * Gets the all tablet hersteller.
	 *
	 * @return the all tablet hersteller
	 */
	public List<Hersteller> getAllTabletHersteller() {
		List<Hersteller> list = getAllHersteller();
		List<Hersteller> result = new LinkedList<>();
		for(Hersteller hersteller: list) {
			if(hersteller != null) {
				if(hersteller.getTyp().equals(HerstellerTyp.Tablet)) {
					result.add(hersteller);
				}
			}
		}
		return result;
	}
	
	/**
	 * Adds the lieferant.
	 *
	 * @param lieferant the lieferant
	 * @return the client response
	 */
	public ClientResponse addLieferant(Lieferant lieferant) {
		String path = "/crud/lieferant";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(lieferant);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the lieferant.
	 *
	 * @param lieferant the lieferant
	 * @return the client response
	 */
	public ClientResponse editLieferant(Lieferant lieferant) {
		String path = "/crud/lieferant";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(lieferant);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete lieferant.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteLieferant(long id) {
		String path = "/crud/lieferant/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the lieferant.
	 *
	 * @param id the id
	 * @return the lieferant
	 */
	public Lieferant getLieferant(long id) {
		String path = "/crud/lieferant/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Lieferant.class);
	}
	
	/**
	 * Gets the all lieferant.
	 *
	 * @return the all lieferant
	 */
	public List<Lieferant> getAllLieferant() {
		String path = "/crud/lieferant";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Lieferant>>() {});
	}
	
	/**
	 * Adds the monitor.
	 *
	 * @param monitor the monitor
	 * @return the client response
	 */
	public ClientResponse addMonitor(Monitor monitor) {
		String path = "/crud/monitor";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(monitor);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the monitor.
	 *
	 * @param monitor the monitor
	 * @return the client response
	 */
	public ClientResponse editMonitor(Monitor monitor) {
		String path = "/crud/monitor";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(monitor);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete monitor.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteMonitor(long id) {
		String path = "/crud/monitor/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the monitor.
	 *
	 * @param id the id
	 * @return the monitor
	 */
	public Monitor getMonitor(long id) {
		String path = "/crud/monitor/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Monitor.class);
	}
	
	/**
	 * Gets the all monitor.
	 *
	 * @return the all monitor
	 */
	public List<Monitor> getAllMonitor() {
		String path = "/crud/monitor";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Monitor>>() {});
	}
	
	/**
	 * Adds the rechner.
	 *
	 * @param rechner the rechner
	 * @return the client response
	 */
	public ClientResponse addRechner(Rechner rechner) {
		String path = "/crud/rechner";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(rechner);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the rechner.
	 *
	 * @param rechner the rechner
	 * @return the client response
	 */
	public ClientResponse editRechner(Rechner rechner) {
		String path = "/crud/rechner";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(rechner);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete rechner.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteRechner(long id) {
		String path = "/crud/rechner/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the rechner.
	 *
	 * @param id the id
	 * @return the rechner
	 */
	public Rechner getRechner(long id) {
		String path = "/crud/rechner/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Rechner.class);
	}
	
	/**
	 * Gets the all rechner.
	 *
	 * @return the all rechner
	 */
	public List<Rechner> getAllRechner() {
		String path = "/crud/rechner";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Rechner>>() {});
	}
	
	/**
	 * Adds the rechnung.
	 *
	 * @param rechnung the rechnung
	 * @return the client response
	 */
	public ClientResponse addRechnung(Rechnung rechnung) {
		String path = "/crud/rechnung";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(rechnung);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the rechnung.
	 *
	 * @param rechnung the rechnung
	 * @return the client response
	 */
	public ClientResponse editRechnung(Rechnung rechnung) {
		String path = "/crud/rechnung";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(rechnung);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete rechnung.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteRechnung(long id) {
		String path = "/crud/rechnung/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the rechnung.
	 *
	 * @param id the id
	 * @return the rechnung
	 */
	public Rechnung getRechnung(long id) {
		String path = "/crud/rechnung/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Rechnung.class);
	}
	
	/**
	 * Gets the all rechnung.
	 *
	 * @return the all rechnung
	 */
	public List<Rechnung> getAllRechnung() {
		String path = "/crud/rechnung";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Rechnung>>() {});
	}
	
	/**
	 * Adds the software.
	 *
	 * @param software the software
	 * @return the client response
	 */
	public ClientResponse addSoftware(Software software) {
		String path = "/crud/software";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(software);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the software.
	 *
	 * @param software the software
	 * @return the client response
	 */
	public ClientResponse editSoftware(Software software) {
		String path = "/crud/software";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(software);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete software.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteSoftware(long id) {
		String path = "/crud/software/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the software.
	 *
	 * @param id the id
	 * @return the software
	 */
	public Software getSoftware(long id) {
		String path = "/crud/software/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Software.class);
	}
	
	/**
	 * Gets the all software.
	 *
	 * @return the all software
	 */
	public List<Software> getAllSoftware() {
		String path = "/crud/software";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Software>>() {});
	}
	
	/**
	 * Adds the tablet.
	 *
	 * @param tablet the tablet
	 * @return the client response
	 */
	public ClientResponse addTablet(Tablet tablet) {
		String path = "/crud/tablet";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(tablet);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the tablet.
	 *
	 * @param tablet the tablet
	 * @return the client response
	 */
	public ClientResponse editTablet(Tablet tablet) {
		String path = "/crud/tablet";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(tablet);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete tablet.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteTablet(long id) {
		String path = "/crud/tablet/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	/**
	 * Gets the tablet.
	 *
	 * @param id the id
	 * @return the tablet
	 */
	public Tablet getTablet(long id) {
		String path = "/crud/tablet/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Tablet.class);
	}
	
	/**
	 * Gets the all tablet.
	 *
	 * @return the all tablet
	 */
	public List<Tablet> getAllTablet() {
		String path = "/crud/tablet";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Tablet>>() {});
	}
	
	/**
	 * Adds the todo.
	 *
	 * @param todo the todo
	 * @return the client response
	 */
	public ClientResponse addTodo(Todo todo) {
		String path = "/crud/todo";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(todo);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Edits the todo.
	 *
	 * @param todo the todo
	 * @return the client response
	 */
	public ClientResponse editTodo(Todo	todo) {
		String path = "/crud/todo";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(todo);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.put(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Delete todo.
	 *
	 * @param id the id
	 * @return the client response
	 */
	public ClientResponse deleteTodo(long id) {
		String path = "/crud/todo/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.delete(ClientResponse.class);
	}
	
	
	/**
	 * Gets the todo.
	 *
	 * @param id the id
	 * @return the todo
	 */
	public Todo getTodo(long id) {
		String path = "/crud/todo/" + id;
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Todo.class);
	}
	
	/**
	 * Gets the all todo.
	 *
	 * @return the all todo
	 */
	public List<Todo> getAllTodo() {
		String path = "/crud/todo";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(new GenericType<List<Todo>>() {});
	}

	/**
	 * Adds the inventarnummer.
	 *
	 * @param inventarnummer the inventarnummer
	 * @return the client response
	 */
	public ClientResponse addInventarnummer(Inventarnummer inventarnummer) {
		String path = "/crud/iNummer";
		service = client.resource(buildURI(path));
		ObjectMapper om = new ObjectMapper();
		String input = null;
		try {
			input = om.writeValueAsString(inventarnummer);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(input != null) {
			return service.type("application/json")
					.post(ClientResponse.class, input);
		}
		return null;
	}
	
	/**
	 * Gets the last inventarnummer.
	 *
	 * @return the last inventarnummer
	 */
	public Inventarnummer getLastInventarnummer() {
		String path = "/crud/iNummer";
		service = client.resource(buildURI(path));
		return service.type("application/json")
				.get(Inventarnummer.class);
	}

	/**
	 * Builds the uri.
	 *
	 * @param path the path
	 * @return the uri
	 */
	public URI buildURI(String path) {
		String base = "http://localhost:8080/ITDB-Server";
		return UriBuilder.fromUri(base + path).build();
	}
	
	/**
	 * Gets the base uri.
	 *
	 * @return the base uri
	 */
	public URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/ITDB-Server").build();
	}

}
