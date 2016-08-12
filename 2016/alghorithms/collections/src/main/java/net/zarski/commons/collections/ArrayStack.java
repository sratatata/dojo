package net.zarski.commons.collections;

public class ArrayStack implements Stack{
	final private Object[] array;
	private int top = -1;
	
	public ArrayStack(int size){
		array = new Object[size];
	}
	
	public void push(Object obj) {
		top++;
		array[top] = obj;
		
	}

	public Object pop() {
		try{
			return array[top];
		}finally{
			top--;
		}
	}

	public int top() {
		return top+1;
	}

}
