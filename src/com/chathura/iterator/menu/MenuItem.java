package com.chathura.iterator.menu;

import java.util.Iterator;

public class MenuItem implements MenuComponent{
	private String menuName;
	private String description;
	public MenuItem(String desc, String name){
		this.description = desc;
		this.menuName = name;
	}

	

	

	@Override
	public Iterator createIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printMenu() {
		System.out.println(this.menuName + " : " +this.description);
		
	}

}
