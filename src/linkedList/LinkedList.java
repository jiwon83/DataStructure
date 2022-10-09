package linkedList;

public class LinkedList {
	// visualgo https://visualgo.net/en/list?slide=1-1

	private Node head;
	private Node tail;
	private int size = 0;

	private class Node {
		private Object data;
		private Node next;

		public Node(Object input) {
			this.data = input;
			this.next = null;
		}

		public String toString() {
			return String.valueOf(this.data);
		}

	}
	


	public void addFirst(Object input) {
		Node newNode = new Node(input);
		newNode.next = head;
		head = newNode;
		size++;
		if (head.next == null) {
			tail = head;
		}
	}

	public void addLast(Object input) {
		Node newNode = new Node(input);
		if (size == 0) {
			addFirst(input);
		} else {
			tail.next = newNode;
			tail = newNode;
			size++;
		}
	}

	// 해당 index의 노드를 return => search
	Node node(int index) {
		Node x = head;
		while (index-- != 0) {
			x = x.next;
		}
		return x;
	}

	public void add(int index, Object input) {
		if (index == 0) {
			addFirst(input);
		} else {
			Node temp1 = node(index - 1);
			Node temp2 = temp1.next;
			Node newNode = new Node(input);
			temp1.next = newNode;
			newNode.next = temp2;
//			Node n = new Node(input);
//			n.next = node(index);
//			node(index-1).next = n;
			size++;
			if (newNode.next == null) {
				tail = newNode;
			}
		}
	}

	public Object removeFirst() {
		Node temp = head;
		Object returnData = temp.data;
		head = temp.next;
		temp = null;
		size--;
		return returnData;
	}

	public Object remove(int k) {
		if (k == 0) {
			return removeFirst();
		}
		Node temp = node(k - 1);
		Node todoDeleted = temp.next;
		temp.next = temp.next.next;

		Object returnData = todoDeleted.data;
		if (todoDeleted == tail) {
			tail = temp;
		}
		todoDeleted = null;
		size--;
		return returnData;

	}

	public void removeLast() {
		// 속도 측면에서 최악이다.
		remove(size - 1);
	}

	@Override
	public String toString() {
		if (head == null) {
			return "[]";

		}
		Node temp = head;
		String str = "[";
		while (temp.next != null) {
			str += temp.data + ",";
			temp = temp.next;
		}
		str += temp.data;
		str += "]";
		return str;
		// return "head:" + head + " tail:" + tail + " size:" + size;
		// return super.toString();
	}

	public int size() {
		return size;
	}

	public Object get(int k) {
		return node(k).data;
	}

	public int indexOf(Object data) {
		Node temp = head;
		int index = 0;
		while (temp.data != data) {

			temp = temp.next;
			index++;
			if (temp == null) {
				return -1;
			}
		}
		return index;
		

	}
	public ListIterator listIterator() {
		return new ListIterator();
	}
	public class ListIterator{
		
		private Node next;	//다음 노드
		private Node lastReturned;	//마지막에 return한 값.
		private int nextIndex;	//다음 노드의 index
		
		ListIterator(){
			next = head;
			nextIndex=0;
		}
		
		
		public Object next() {
			lastReturned = next;
			next =next.next;
			nextIndex++;
			return lastReturned.data;
//			return node(index++);
		}
		public boolean hasNext() {
			return nextIndex < size();
			
		}
//		public Node previous() {
//			return node(--nextIndex);
//		}
//	

		public void add(Object input) {
			Node newNode = new Node(input);
			if(lastReturned == null) {
				head = newNode;
				newNode.next = next;
				//head.next = next;
				
			}else {
				lastReturned.next=newNode;
				newNode.next = next;
				
			}
			lastReturned= newNode;
			nextIndex++;
			size++;
			
		}

		//add()하기 전에 remove를 하면 error가 발생해야 함. 
		public void remove() {
			if(nextIndex == 0) {
				throw new IllegalStateException();
			}
			LinkedList.this.remove(nextIndex-1);
			nextIndex--;
			
		}
		
	}
	

}
