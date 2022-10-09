package compare;

import ArrayList.ArrayList;
import ArrayList.ArrayList.ListIterator;
import linkedList.LinkedList;

public class Main {

	public static void main(String[]args) {
		
		ArrayList a = new ArrayList();
		a.addLast(10);
		a.addLast(20);
		a.addLast(30);
		a.addLast(40);
		final long start = System.nanoTime();
		//a.addFirst(5);
		//a.get(2);
		final long end = System.nanoTime();
		System.out.println("소요시간 : " + (end - start) * 1e-9 + " sec");
		
		ArrayList.ListIterator ai = a.listIterator();
		while(ai.hasNext()) {
			if((int)ai.next()==20) {
				ai.add(25);
			}
		}
		
		
		////////////////////////////////////
		LinkedList l = new LinkedList();
		l.addLast(10);
		l.addLast(20);
		l.addLast(30);
		l.addLast(40);

		final long start2 = System.nanoTime();
		//l.addFirst(5);
		//l.get(2);
		final long end2 = System.nanoTime();
		System.out.println("소요시간 : " + (end2 - start2) * 1e-9 + " sec");
		
		LinkedList.ListIterator li = l.listIterator();
		while(li.hasNext()) {
			if((int)li.next()==20) {
				li.add(25);
			}
		}
	}

}
