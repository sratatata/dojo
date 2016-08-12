package net.zarski.commons.collections;

/**
 * Collection is not generic for simplicity
 */
public interface List {

	void push(Object i);

	Object get(int id);
	
	int getSize();

	Object find(Object i);

}