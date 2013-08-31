/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Rechner represents a computer.
 */
@XmlRootElement
public class Rechner implements Entity {

	/** The id. */
	private long id;

	/** The manufacturer. */
	private Hersteller hersteller;

	/** The supplier. */
	private Lieferant lieferant;

	/** The invoice. */
	private Rechnung rechnung;

	/** The user. */
	private Benutzer benutzer;

	/** The department. */
	private Abteilung abteilung;

	/** The building. */
	private Gebäude gebäude;

	/** The cpu. */
	private Cpu cpu;

	/** The gpu. */
	private Gpu gpu;

	/** The monitor. */
	private Monitor monitor;

	/** The ram. */
	private String ram;
	
	/** The first hdd. */
	private String hdd1;
	
	/** The second hdd. */
	private String hdd2;
	
	/** The thrid hdd. */
	private String hdd3;
	
	/** The fourth hdd. */
	private String hdd4;
	
	/** The first drive. */
	private String laufwerk1;
	
	/** The second drive. */
	private String laufwerk2;

	/** The used software. */
	private List<Software> software;

	/** The inventory number. */
	private String inventarnummer;
	
	/** The device number. */
	private String gerätenummer;
	
	/** Determines wheater the computer is scrapped or not. */
	private boolean ausrangiert;
	
	/** The warranty. */
	private String garantie;
	
	/** The computer name. */
	private String computername;
	
	/** The working group. */
	private String arbeitsgruppe;
	
	/** The domain. */
	private String domäne;
	
	/** The first ip. */
	private String ip1;
	
	/** The second ip. */
	private String ip2;
	
	/** The first subnet address. */
	private String subnet1;
	
	/** The second subnet address. */
	private String subnet2;
	
	/** The gateway. */
	private String gateway;
	
	/** The primary dns. */
	private String dnsPrimary;
	
	/** The secondary dns. */
	private String dnsSecondary;
	
	/** The notes. */
	private String anmerkungen;
	
	/** The maintenance. */
	private String wartung;
	
	/** Determines wheater a maintenance is initiated. */
	private boolean initWartung;
	
	/**
	 * Class constructor.
	 */
	public Rechner() {
		
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the manufacturer.
	 *
	 * @return the manufacturer
	 */
	public Hersteller getHersteller() {
		return hersteller;
	}

	/**
	 * Sets the manufacturer.
	 *
	 * @param hersteller the new manufacturer
	 */
	public void setHersteller(Hersteller hersteller) {
		this.hersteller = hersteller;
	}

	/**
	 * Gets the supplier.
	 *
	 * @return the supplier
	 */
	public Lieferant getLieferant() {
		return lieferant;
	}

	/**
	 * Sets the supplier.
	 *
	 * @param lieferant the new supplier
	 */
	public void setLieferant(Lieferant lieferant) {
		this.lieferant = lieferant;
	}

	/**
	 * Gets the invoice.
	 *
	 * @return the invoice
	 */
	public Rechnung getRechnung() {
		return rechnung;
	}

	/**
	 * Sets the invoice.
	 *
	 * @param rechnung the new invoice
	 */
	public void setRechnung(Rechnung rechnung) {
		this.rechnung = rechnung;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Benutzer getBenutzer() {
		return benutzer;
	}

	/**
	 * Sets the user.
	 *
	 * @param benutzer the new user
	 */
	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public Abteilung getAbteilung() {
		return abteilung;
	}

	/**
	 * Sets the department.
	 *
	 * @param abteilung the new department
	 */
	public void setAbteilung(Abteilung abteilung) {
		this.abteilung = abteilung;
	}

	/**
	 * Gets the building.
	 *
	 * @return the building
	 */
	public Gebäude getGebäude() {
		return gebäude;
	}

	/**
	 * Sets the building.
	 *
	 * @param gebäude the new building
	 */
	public void setGebäude(Gebäude gebäude) {
		this.gebäude = gebäude;
	}

	/**
	 * Gets the cpu.
	 *
	 * @return the cpu
	 */
	public Cpu getCpu() {
		return cpu;
	}

	/**
	 * Sets the cpu.
	 *
	 * @param cpu the new cpu
	 */
	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	/**
	 * Gets the gpu.
	 *
	 * @return the gpu
	 */
	public Gpu getGpu() {
		return gpu;
	}

	/**
	 * Sets the gpu.
	 *
	 * @param gpu the new gpu
	 */
	public void setGpu(Gpu gpu) {
		this.gpu = gpu;
	}

	/**
	 * Gets the monitor.
	 *
	 * @return the monitor
	 */
	public Monitor getMonitor() {
		return monitor;
	}

	/**
	 * Sets the monitor.
	 *
	 * @param monitor the new monitor
	 */
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	/**
	 * Gets the ram.
	 *
	 * @return the ram
	 */
	public String getRam() {
		return ram;
	}

	/**
	 * Sets the ram.
	 *
	 * @param ram the new ram
	 */
	public void setRam(String ram) {
		this.ram = ram;
	}

	/**
	 * Gets the first hdd.
	 *
	 * @return the first hdd
	 */
	public String getHdd1() {
		return hdd1;
	}

	/**
	 * Sets the first hdd.
	 *
	 * @param hdd1 the new first hdd
	 */
	public void setHdd1(String hdd1) {
		this.hdd1 = hdd1;
	}

	/**
	 * Gets the second hdd.
	 *
	 * @return the second hdd
	 */
	public String getHdd2() {
		return hdd2;
	}

	/**
	 * Sets the second hdd.
	 *
	 * @param hdd2 the new second hdd
	 */
	public void setHdd2(String hdd2) {
		this.hdd2 = hdd2;
	}

	/**
	 * Gets the third hdd.
	 *
	 * @return the third hdd
	 */
	public String getHdd3() {
		return hdd3;
	}

	/**
	 * Sets the third hdd.
	 *
	 * @param hdd3 the new third hdd
	 */
	public void setHdd3(String hdd3) {
		this.hdd3 = hdd3;
	}

	/**
	 * Gets the fourth hdd.
	 *
	 * @return the fourth hdd
	 */
	public String getHdd4() {
		return hdd4;
	}

	/**
	 * Sets the fourth hdd.
	 *
	 * @param hdd4 the new fourth hdd
	 */
	public void setHdd4(String hdd4) {
		this.hdd4 = hdd4;
	}

	/**
	 * Gets the first drive.
	 *
	 * @return the first drive
	 */
	public String getLaufwerk1() {
		return laufwerk1;
	}

	/**
	 * Sets the first drive.
	 *
	 * @param laufwerk1 the new first drive
	 */
	public void setLaufwerk1(String laufwerk1) {
		this.laufwerk1 = laufwerk1;
	}

	/**
	 * Gets the second drive.
	 *
	 * @return the second drive
	 */
	public String getLaufwerk2() {
		return laufwerk2;
	}

	/**
	 * Sets the second drive.
	 *
	 * @param laufwerk2 the new second drive
	 */
	public void setLaufwerk2(String laufwerk2) {
		this.laufwerk2 = laufwerk2;
	}

	/**
	 * Gets the software.
	 *
	 * @return the software
	 */
	public List<Software> getSoftware() {
		return software;
	}

	/**
	 * Sets the software.
	 *
	 * @param software the new software
	 */
	public void setSoftware(List<Software> software) {
		this.software = software;
	}

	/**
	 * Gets the inventory number.
	 *
	 * @return the inventory number
	 */
	public String getInventarnummer() {
		return inventarnummer;
	}

	/**
	 * Sets the inventory number.
	 *
	 * @param inventarnummer the new inventory number
	 */
	public void setInventarnummer(String inventarnummer) {
		this.inventarnummer = inventarnummer;
	}

	/**
	 * Gets the device number.
	 *
	 * @return the device number
	 */
	public String getGerätenummer() {
		return gerätenummer;
	}

	/**
	 * Sets the device number.
	 *
	 * @param gerätenummer the new device number
	 */
	public void setGerätenummer(String gerätenummer) {
		this.gerätenummer = gerätenummer;
	}

	/**
	 * Gets the warranty.
	 *
	 * @return the warranty
	 */
	public String getGarantie() {
		return garantie;
	}

	/**
	 * Sets the warranty.
	 *
	 * @param garantie the new warranty
	 */
	public void setGarantie(String garantie) {
		this.garantie = garantie;
	}

	/**
	 * Checks if the computer is scrapped.
	 *
	 * @return true, if the computer is scrapped
	 */
	public boolean isAusrangiert() {
		return ausrangiert;
	}

	/**
	 * Sets the scrapped.
	 *
	 * @param ausrangiert the new scrapped
	 */
	public void setAusrangiert(boolean ausrangiert) {
		this.ausrangiert = ausrangiert;
	}

	/**
	 * Gets the computer name.
	 *
	 * @return the computer name
	 */
	public String getComputername() {
		return computername;
	}

	/**
	 * Sets the computer name.
	 *
	 * @param computername the new computer name
	 */
	public void setComputername(String computername) {
		this.computername = computername;
	}

	/**
	 * Gets the working group.
	 *
	 * @return the working group
	 */
	public String getArbeitsgruppe() {
		return arbeitsgruppe;
	}

	/**
	 * Sets the working group.
	 *
	 * @param arbeitsgruppe the new working group
	 */
	public void setArbeitsgruppe(String arbeitsgruppe) {
		this.arbeitsgruppe = arbeitsgruppe;
	}

	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	public String getDomäne() {
		return domäne;
	}

	/**
	 * Sets the domain.
	 *
	 * @param domäne the new domain
	 */
	public void setDomäne(String domäne) {
		this.domäne = domäne;
	}

	/**
	 * Gets the first ip.
	 *
	 * @return the first ip
	 */
	public String getIp1() {
		return ip1;
	}

	/**
	 * Sets the first ip.
	 *
	 * @param ip1 the new first ip
	 */
	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}

	/**
	 * Gets the second ip.
	 *
	 * @return the second ip
	 */
	public String getIp2() {
		return ip2;
	}

	/**
	 * Sets the second ip.
	 *
	 * @param ip2 the new second ip
	 */
	public void setIp2(String ip2) {
		this.ip2 = ip2;
	}

	/**
	 * Gets the frist subnet address.
	 *
	 * @return the frist subnet address
	 */
	public String getSubnet1() {
		return subnet1;
	}

	/**
	 * Sets the frist subnet address.
	 *
	 * @param subnet1 the new frist subnet address
	 */
	public void setSubnet1(String subnet1) {
		this.subnet1 = subnet1;
	}

	/**
	 * Gets the second subnet address.
	 *
	 * @return the second subnet address
	 */
	public String getSubnet2() {
		return subnet2;
	}

	/**
	 * Sets the second subnet address.
	 *
	 * @param subnet2 the new second subnet address
	 */
	public void setSubnet2(String subnet2) {
		this.subnet2 = subnet2;
	}

	/**
	 * Gets the gateway.
	 *
	 * @return the gateway
	 */
	public String getGateway() {
		return gateway;
	}

	/**
	 * Sets the gateway.
	 *
	 * @param gateway the new gateway
	 */
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	/**
	 * Gets the primary dns.
	 *
	 * @return the primary dns
	 */
	public String getDnsPrimary() {
		return dnsPrimary;
	}

	/**
	 * Sets the primary dns.
	 *
	 * @param dnsPrimary the new primary dns
	 */
	public void setDnsPrimary(String dnsPrimary) {
		this.dnsPrimary = dnsPrimary;
	}

	/**
	 * Gets the secondary dns.
	 *
	 * @return the secondary dns
	 */
	public String getDnsSecondary() {
		return dnsSecondary;
	}

	/**
	 * Sets the secondary dns.
	 *
	 * @param dnsSecondary the new secondary dns
	 */
	public void setDnsSecondary(String dnsSecondary) {
		this.dnsSecondary = dnsSecondary;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getAnmerkungen() {
		return anmerkungen;
	}

	/**
	 * Sets the notes.
	 *
	 * @param anmerkungen the new notes
	 */
	public void setAnmerkungen(String anmerkungen) {
		this.anmerkungen = anmerkungen;
	}

	/**
	 * Gets the maintenance.
	 *
	 * @return the maintenance
	 */
	public String getWartung() {
		return wartung;
	}

	/**
	 * Sets the maintenance.
	 *
	 * @param wartung the new maintenance
	 */
	public void setWartung(String wartung) {
		this.wartung = wartung;
	}

	/**
	 * Checks if the maintenance is initiated.
	 *
	 * @return true, if the maintenance is initiated
	 */
	public boolean isInitWartung() {
		return initWartung;
	}

	/**
	 * Sets the initiation state for maintenance.
	 *
	 * @param initWartung the new initiation state for maintenance
	 */
	public void setInitWartung(boolean initWartung) {
		this.initWartung = initWartung;
	}

	@Override
	public String toString() {
		return inventarnummer + " : " + computername;
	}
	
	
	
}
