package doublyLinkedList;

public class Main {
	public static void main(String[]args) {
		DoublyLinkedList numbers = new DoublyLinkedList();
		numbers.addFirst(40);
		numbers.addFirst(30);
		numbers.addFirst(20);
		numbers.addFirst(10);
		//numbers.add(2,25);
		numbers.removeLast();
		System.out.println(numbers.node(2));
		System.out.println(numbers);
//		System.out.println(numbers.size());
//		System.out.println(numbers.get(1));
//		System.out.println(numbers.indexOf(30));
//		System.out.println(numbers.indexOf(77));
		
//		DoublyLinkedList.ListIterator i = numbers.listIterator();
//		//i.add(0);
//		while(i.hasNext()) {
//			if((int)i.next() == 10) {
//				//i.add(15);
//				i.remove();
//			}
//		}
//		System.out.println(numbers);
//		System.out.println(i.previous());

		
		
		


	}
}
