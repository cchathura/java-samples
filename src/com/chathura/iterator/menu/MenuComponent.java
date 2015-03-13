package com.chathura.iterator.menu;

import java.util.Iterator;

public interface MenuComponent {
public void add(MenuComponent menu);
public MenuComponent getMenu();
public void remove (MenuComponent menu);
public Iterator createIterator();
}
