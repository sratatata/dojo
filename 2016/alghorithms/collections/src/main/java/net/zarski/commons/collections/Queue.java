package net.zarski.commons.collections;

/**
 * Collection is not generic for simplicity
 */
public interface Queue {
	void enqueue(Object obj);
	Object dequeue();
	int length();
}
