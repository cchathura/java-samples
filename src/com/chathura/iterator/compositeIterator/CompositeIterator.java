package com.chathura.iterator.compositeIterator;

import java.awt.Menu;
import java.util.Iterator;
import java.util.Stack;

import com.chathura.iterator.menu.MenuComponent;
import com.chathura.iterator.menu.MenuList;

public class CompositeIterator implements Iterator<MenuComponent>{
@SuppressWarnings("rawtypes")
Stack<Iterator> stack = new Stack<Iterator>();

public CompositeIterator(Iterator iterator){
	stack.push(iterator);
}
	@Override
	public boolean hasNext() {
		
		if(stack.isEmpty()){
			return false;
		}else{
			Iterator iterator = stack.peek();
			if(!iterator.hasNext()){
				stack.pop();
				return hasNext();
			}else{
				return true;
			}
		}

	}

	@Override
	public MenuComponent next() {
		if(hasNext()){
			Iterator iterator = stack.peek();
			MenuComponent component = (MenuComponent) iterator.next();
			if(component instanceof MenuList){
				stack.push(component.createIterator());
			}
			return component;
		}else{
			return null;
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
