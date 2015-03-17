/**
 * 
 */
package com.chathura.iterator.app;

import java.util.Iterator;

import com.chathura.iterator.menu.MenuComponent;
import com.chathura.iterator.menu.MenuItem;
import com.chathura.iterator.menu.MenuList;



/**
 * @author chathura
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MenuList cars = new MenuList("CARS","all cars are here");
		MenuList toyotacars = new MenuList("Toyota CARS","all Toyota cars are here");
		MenuList nissancars = new MenuList("Nissan CARS","all Nissan cars are here");
		MenuList toyotaHybrid = new MenuList("toyota Hybrid CARS","all toyota Hybrid cars are here");
		
		MenuItem march = new MenuItem("march", "nissan march");
		MenuItem sunny = new MenuItem("sunny", "nissan sunny");
		nissancars.add(march);
		nissancars.add(sunny);
		cars.add(nissancars);
		
		MenuItem t121 = new MenuItem("t121", "toyota t121");
		MenuItem axio = new MenuItem("axio", "toyota axio");
		toyotacars.add(t121);
		toyotacars.add(axio);
		
		
		MenuItem axiohybride = new MenuItem("axiohybride", "toyota axiohybride");
		MenuItem prius = new MenuItem("prius", "toyota prius");
		toyotaHybrid.add(axiohybride);
		toyotaHybrid.add(prius);
		toyotacars.add(toyotaHybrid);
		cars.add(toyotacars);
		
		Iterator iterator = cars.createIterator();
		while(iterator.hasNext()){
			MenuComponent menus =(MenuComponent) iterator.next();
			menus.printMenu();
		}
		
	}

}
