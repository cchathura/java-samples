/**
 * 
 */
package com.chathura.iterator.menu;

import java.util.ArrayList;
import java.util.Iterator;

import com.chathura.iterator.compositeIterator.CompositeIterator;

/**
 * @author chathura	
 *
 */
public class MenuList implements MenuComponent {
ArrayList<MenuComponent> menulist = new ArrayList<MenuComponent>();

	private String description;
	private String name;
	public MenuList(String name,String desc){
		this.description = desc;
		this.name = name;
	}
	public void add(MenuComponent menu) {
		menulist.add(menu);
		
	}

	
	public MenuComponent getMenu() {
		// TODO Auto-generated method stub
		return null;
	}


	public void remove(MenuComponent menu) {
		menulist.remove(menu);
		
	}

	@Override
	public Iterator createIterator() {
		
		return new CompositeIterator(menulist.iterator());
	}


	@Override
	public void printMenu() {
		System.out.println("print menu list "+ this.name +" : "+this.description);
		
	}

}
