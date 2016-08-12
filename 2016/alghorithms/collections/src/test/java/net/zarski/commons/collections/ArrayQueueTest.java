package net.zarski.commons.collections;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayQueueTest {
	private static final int SIZE = 3;
	@Test
	public void emptyQueueSize(){
		Queue queue = new ArrayQueue(SIZE);
		
		assertEquals(0, queue.length());
	}
	
	@Test
	public void enqueueElement(){
		Queue queue = new ArrayQueue(SIZE);
		queue.enqueue(1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void enqueueFullQueue(){
		Queue q = new ArrayQueue(SIZE);
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
	}
	
	@Test
	public void dequeue(){
		Queue q = new ArrayQueue(SIZE);
		q.enqueue(1);
		assertEquals(1, q.dequeue());
	}
	
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void dequeueEmpty(){
		Queue q = new ArrayQueue(SIZE);
		q.enqueue(1);
		q.enqueue(2);
		q.dequeue();
		q.dequeue();
		q.dequeue(); //queue is already empty
		
	}
	
	@Test
	public void enqueueCycle(){
		Queue q = new ArrayQueue(SIZE);
		q.enqueue(1);
		q.enqueue(2);
		q.dequeue();
		q.dequeue();
		q.enqueue(5);
		q.enqueue(6);
		q.dequeue();
		q.enqueue(7);
		q.enqueue(8);
		
		assertTrue("No exceptions", true);
	}

}
