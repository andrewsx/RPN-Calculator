public class ArrayStack<E> {
	
	private static final int MAX_CAPACITY = 1500;
	private E[] data;
	private int index = -1;
	
	public ArrayStack() {
		this(MAX_CAPACITY);
	}
	
	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	public int size() {
		return index + 1;
	}
	
	public boolean isEmpty() {
		return (index == -1);
	}
	
	public void push(E e) throws IllegalStateException {
		if (data.length == size()) {
			throw new IllegalStateException("stack is full");
		} 
		data[++index] = e;
	}
	
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		int temp = size() - 1;
		E e = data[temp];
		data[temp] = null;
		index--;
		return e;
	}
	
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return data[size() - 1];
	}
	
	
}
