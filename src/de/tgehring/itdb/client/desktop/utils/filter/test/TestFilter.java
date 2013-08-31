/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.utils.filter.test;

import java.util.LinkedList;
import java.util.List;

import de.tgehring.itdb.client.desktop.model.Cpu;
import de.tgehring.itdb.client.desktop.model.Gpu;
import de.tgehring.itdb.client.desktop.model.Hersteller;
import de.tgehring.itdb.client.desktop.model.Rechner;
import de.tgehring.itdb.client.desktop.utils.filter.CpuFilter;
import de.tgehring.itdb.client.desktop.utils.filter.GpuFilter;
import de.tgehring.itdb.client.desktop.utils.filter.HddFilter;
import de.tgehring.itdb.client.desktop.utils.filter.InventarnummerFilter;
import de.tgehring.itdb.client.desktop.utils.filter.RamFilter;
import de.tgehring.itdb.client.desktop.utils.filter.RechnerFilter;


// TODO: Auto-generated Javadoc
/**
 * The Class TestFilter.
 */
public class TestFilter {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		List<Rechner> list = new LinkedList<Rechner>();
		Hersteller gpuHersteller = new Hersteller();
		gpuHersteller.setBezeichnung("Nvidia");
		Hersteller cpuHersteller = new Hersteller();
		cpuHersteller.setBezeichnung("Intel");
		Rechner rechner = null;
		for(int i=0; i<10; i++) {
			rechner = new Rechner();
			rechner.setInventarnummer("100" +i);
			rechner.setHdd1("128");
			rechner.setHdd2("250");
			rechner.setRam("16");
			if(i%2 == 0) {
				Gpu gpu = new Gpu();
				gpu.setBezeichnung("GeForce GTX 770");
				gpu.setHersteller(gpuHersteller);
				rechner.setRam("8");
				rechner.setHdd3("500");
				rechner.setGpu(gpu);
			}
			if(i%3 == 0) {
				Cpu cpu = new Cpu();
				cpu.setBezeichnung("Core i7");
				cpu.setHersteller(cpuHersteller);
				rechner.setRam("4");
				rechner.setHdd4("1000");
				rechner.setCpu(cpu);
			}
			list.add(rechner);
		}
		RechnerFilter filter = new RechnerFilter();
		filter.setRechnerList(list);
		filter.addFilter(new CpuFilter("Intel"));
		filter.addFilter(new GpuFilter("GeForce"));
		filter.addFilter(new HddFilter("500"));
		filter.addFilter(new InventarnummerFilter("100"));
		filter.addFilter(new RamFilter("8"));
		System.out.println(filter.applyFilter().size());
	}

}
