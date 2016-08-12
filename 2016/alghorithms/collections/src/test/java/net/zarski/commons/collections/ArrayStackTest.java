package net.zarski.commons.collections;

import static org.junit.Assert.*;

import org.junit.Test;


public class ArrayStackTest {

	public void emptyStackTopSize(){
		Stack stack = new ArrayStack(1);
		assertEquals(0,stack.top());
	}
	
	@Test
	public void stackPushInt(){
		Stack stack = new ArrayStack(3);
		stack.push(1);
		stack.push(5);
		stack.push(2);
		assertEquals(3,stack.top());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void stackPushOverTheCapacity(){
		Stack stack = new ArrayStack(2);
		stack.push(1);
		stack.push(5);
		stack.push(2);
		assertEquals(3,stack.top());
	}
	
	@Test
	public void popElementFromStack(){
		Stack stack = new ArrayStack(3);
		stack.push(1);
		stack.push(3);
		stack.push(5);
		
		assertEquals(3, stack.top());

		assertEquals(5,stack.pop());
		assertEquals(3,stack.pop());
		assertEquals(1,stack.pop());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void popElementFromEmptyStack(){
		Stack stack = new ArrayStack(3);
		stack.pop();
	}
	
	
}
