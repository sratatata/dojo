package net.zarski.commons.collections;

/**
 * Collection is not generic for simplicity
 */
public interface Stack {
	public void push(Object obj);
	
	public Object pop();

	public int top();
}
