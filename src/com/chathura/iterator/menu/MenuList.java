/**
 * 
 */
package com.chathura.iterator.menu;

import java.util.ArrayList;

/**
 * @author chathura	
 *
 */
public class MenuList implements MenuComponent {
ArrayList<MenuComponent> menulist = new ArrayList<MenuComponent>();

	@Override
	public void add(MenuComponent menu) {
		menulist.add(menu);
		
	}

	@Override
	public MenuComponent getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(MenuComponent menu) {
		menulist.remove(menu);
		
	}

}
