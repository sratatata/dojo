package net.zarski.commons.collections;


public class LinkedList implements List{
	private Element head;
	private int size; 
	
	public LinkedList() {
		size = 0;
	}
	
	public void push(Object value){
		size++;
		if(head == null){
			head = new Element();
			head.previous = null;
			head.next = null;
			head.value = value;
		}else{
			Element tmp = findLast(head);
			tmp.next = new Element();
			tmp.next.previous = tmp;
			tmp.next.next = null;
			tmp.next.value = value;
		}
	}
	
	private Element findLast(Element head){
		Element tmp = head;
		while(tmp.next != null){
			tmp = tmp.next;
		}
		return tmp;
	}
	
	public Object get(int i){
		Element tmp = head;
		int counter = 0;
		while(tmp != null && counter != i){
			tmp = tmp.next;
			counter++;
		}
		return tmp.value;
	}
	
	public Object find(Object value){
		return listSearch(head, value).value;
	}
	
	private Element listSearch(Element head, Object value){
		Element tmp = head;
		while(tmp != null && !tmp.value.equals(value)){
			tmp = tmp.next;
		}
		return tmp;
	}
	
	public int getSize(){
		return size;
	}
	
	private class Element{
		Element previous;
		Element next;
		Object value;
	}
}
