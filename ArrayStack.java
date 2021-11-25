import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
import java.lang.*;
/**
 * JUnit Tests for Chapter 15
 */
import java.util.NoSuchElementException;
@SuppressWarnings("unchecked")
public class ArrayStack<E> {
	private E[]storage;
	private final int DEFAULT_CAPACITY = 10;
	private int top, size;
	/**
	 * Reset the base data structures just in case
	 */
	@BeforeEach 
	void reset(){
		return;
	}
	
	/**
	 * Tests equals
	 */
	@Test 
	public void equals(){		
		ArrayStack<String> s1 = new ArrayStack<>();
		ArrayStack<String> s2 = new ArrayStack<>();
		s1.push("1");
		s1.push("2");
		s2.push("1");
		s2.push("2");
		assertEquals(s1,s2);
	}
	
	/**
	 * Tests push and pop
	 */
	@Test 
	public void pushPop() {
		ArrayStack<String> s = new ArrayStack<>();
		assertEquals(0, s.size());
		s.push("1");
        assertEquals(1, s.size());
        s.push("2");
        assertEquals(2, s.size());
        assertEquals("2", s.pop());
        assertEquals("1", s.pop());
        assertEquals(0, s.size());
	}
	/**
	 * Tests empty
	 */
	@Test
	public void empty() {
	ArrayStack<Integer> em = new ArrayStack<>();
	assertTrue(em.isEmpty());
	em.push(7);
    assertFalse(em.isEmpty());
    em.pop();
    assertTrue(em.isEmpty());
	}
	/**
	 * Tests peek
	 */
	@Test
    public void peektest() {
	ArrayStack<Integer> p = new ArrayStack<>();
	for (int i = 0; i < 10; i++) {
    Integer pk = (int) Math.random() * 100;
            p.push(pk);
            assertEquals(pk, p.peek());
        }
	}
	/**
	 * Tests add all
	 */
	@Test 
	public void all(){
	Integer[] x = {1,2,3,4,5,6};
	ArrayStack<Integer> a1 = new ArrayStack<>();
	ArrayStack<Integer> a2 = new ArrayStack<>();
	a1.addAll(x);
	a2.addAll(x);
	assertTrue(a1.equals(a2));
		
	}

	/**
	 * Creates array stack.
	 */
	public ArrayStack(){
		storage = (E[])new Object[DEFAULT_CAPACITY];
		size = 0;
		top = 0;
	}
	/**
	 *Checks to see if we need grow the stack
	 *Pushes value to the top of the stack.
	 */
	public void push(E value){
		if(size == storage.length){
			grow();
		}
		storage[top++] = value;
	}
	/**
	 * Private method that grows the stack by double the amount of the original.
	 */
	private void grow(){
		E[]copy = (E[])new Object[size*2];
		int temp = top;
		for(int index = 0; index < size; index++){
			copy[index] = storage[temp];
		}
		top = 0;
		storage = copy;
	}
	/**
	 * Checks to see if the stack is empty
	 * removes the value and returns it at the top of the stack.
	 * Checks to see if we need to shrink the stack.
	 */
	public E pop(){
		if(top < 1){
			throw new NoSuchElementException("Stack is empty");
		}
		E value = storage[top];
		storage[top--] = null;
		if(size < storage.length / 2 && !(size < DEFAULT_CAPACITY*2)){
			shrink();	
		}
		return value; 
	}
	/**
	 * Private method that shrinks the stack by 1.5
	 */
	private void shrink(){
		E[]copy = (E[])new Object[(int)(size*3/2)];
		int temp = top;
		
		for(int index = 0; index < size; index++){
			copy[index] = storage[temp];
		}
		top = 0; 
		storage = copy;
		
		
	}
	/**
	 * Checks to see if the stack is empty.
	 * Returns the value at the top of the stack without removing it.
	 */
	public E peek() {
		if (top == 0){
			throw new NoSuchElementException("Stack is empty");
		}
		return storage[top];
	}
	/**
	 * returns the top
	 */
	public int getTop(){
		return top;
	}
	/**
	 * returns to see if the stack is empty
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	/**
	 * returns the size
	 */
	public int size() {
		return size;
	}
	/**
	 * Checks to see if the stack is empty
	 * then shows the string view of the stack. That is why, when it is empty we return
	 * the empty brackets, to show it in string form. 
	 */
	
	public String toString(){
		if(this.isEmpty()){
			return "[]";
		}
		String s = "[";
		s += storage[top];
		for(int i = 1; i < top; i++){
			s = s + ", " + storage[(i + top)%storage.length].toString();
		}
		return s + "]";
	}
	
	/**
	 * This method checks to see if two stacks are 
	 * equal to each other
	 */
	public boolean equals(ArrayStack s2){
		if(top != s2.getTop()){
			return false;
		}
		return toString().equals(s2.toString());
	}
	/**
	 * This method adds all the elements to the top of the list
	 */
	public void addAll(E[] list){
		for(E x:list){
			push(x);
		}
	}
	
}
