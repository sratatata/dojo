package net.zarski.commons.collections;

public class ArrayQueue implements Queue {
	private int head;
	private int tail;
	private final Object[] array;
	
	public ArrayQueue(int size) {
		array = new Object[size+1];
		head = 0;
		tail = 0;
	}
	
	public void enqueue(Object obj) {
		if(isFull()) throw new IndexOutOfBoundsException("Queue is already full");
		
		array[tail] = obj;
		if(tail == array.length-1){
			tail = 0;
		}else{
			tail++;
		}
	}

	public Object dequeue() {
		if(isEmpty()) throw new IndexOutOfBoundsException("Queue is empty");
		
		Object t = array[head];
		array[head] = null;
		if(head == array.length-1){
			head = 0;
		}else{
			head++;
		}
		return t;
	}

	public int length() {
		return 0; //todo poprawic
	}
	
	private boolean isEmpty(){
		return head == tail;
	}
	
	private boolean isFull(){
		return head == tail +1 || (head == 0 && tail == array.length-1);
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getTail() {
		return tail;
	}

	public void setTail(int tail) {
		this.tail = tail;
	}

	public String toString(){
		String s = "[";
		for(int i=0; i<array.length; i++){
			s += " "+array[i]+" ";
		}
		s += "]";
		return String.format("Size=%s, Head=%s, Tail=%s\n%s", array.length, head, tail,s );
	}

}
