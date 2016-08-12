package net.zarski.commons.collections;

public interface Queue {
	void enqueue(Object obj);
	Object dequeue();
	int length();
}
