package net.zarski.commons.collections;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void pushElementIncreasesSize(){
		List l = new LinkedList();
		l.push(10);
		l.push(11);
		l.push(12);
		assertEquals(3, l.getSize());
		l.push(13);
		l.push(1);
		l.push(15);
		assertEquals(6, l.getSize());
	}
	
	@Test
	public void returnsElementOfSpecifiedIndex(){
		List l = new LinkedList();
		l.push(1);
		l.push(2);
		l.push(3);
		assertEquals(3, l.get(2));
	}
	
	@Test
	public void findsByValue(){
		List l = new LinkedList();
		l.push(1);
		l.push(2);
		l.push(3);
		assertEquals(2, l.find(2));
	}
	
}
