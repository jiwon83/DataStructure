package doublyLinkedList;

public class DoublyLinkedList {
	// visualgo https://visualgo.net/en/list?slide=1-1

	private Node head;
	private Node tail;
	private int size = 0;

	private class Node {
		private Object data;
		private Node next;
		private Node prev;

		public Node(Object input) {
			this.data = input;
			this.next = null;
			this.prev = null;
		}

		public String toString() {
			return String.valueOf(this.data);
		}

	}

	public void addFirst(Object input) {
		Node newNode = new Node(input);
		newNode.next = head;

		if (head != null) {
			head.prev = newNode;
		}

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
			newNode.prev = tail;
			tail = newNode;
			size++;
		}
	}

	// 해당 index의 노드를 return => search
	Node node(int index) {
		if(index<=size/2) {
			Node x = head;
			for(int i=0; i<index; i++) {
				x = x.next;
			}
			return x;
		}else {
			Node x = tail;
			for(int i = size-1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
		
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
			if (temp2 != null) {
				temp2.prev = newNode;
			}
			newNode.prev = temp1;
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
		if(head != null) {
			head.prev = null;
		}
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
		
		temp.next.prev = temp;

		Object returnData = todoDeleted.data;
		if (todoDeleted == tail) {
			tail = temp;
		}
		todoDeleted = null;
		size--;
		return returnData;

	}

	public void removeLast() {
		
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

	//설명 : https://www.youtube.com/watch?v=A56lqH1U-88&list=PLuHgQVnccGMDsWOOn_P0EmAWB8DArS3Fk&index=47
	public ListIterator listIterator() {
		return new ListIterator();
	}

	public class ListIterator {

		private Node next; // 다음 노드
		private Node lastReturned; // 마지막에 return한 값.
		private int nextIndex; // 다음 노드의 index

		ListIterator() {
			next = head;
			nextIndex = 0;
		}

		public Object next() {
			lastReturned = next;
			next = next.next;
			nextIndex++;
			return lastReturned.data;
			
		}

		public boolean hasNext() {
			return nextIndex < size();

		}
		public Object previous() {
			if(next == null) {
				lastReturned = next = tail;
			}else {
				lastReturned = next.prev; 
			}
			nextIndex--;
			return lastReturned.data;
		}
		public boolean hasPrevious() {
			return nextIndex > 0;
		}


		public void add(Object input) {
			Node newNode = new Node(input);
			if (lastReturned == null) { //next()를 한 번도 호출하지 않은 상태
				head = newNode;
				newNode.next = next;
		
			} else {
				lastReturned.next = newNode;
				newNode.prev = lastReturned;
				if(next != null) {
					newNode.next = next;
					next.prev = newNode;
				}else {
					tail = newNode;
				}
				 
			}
			lastReturned = newNode;
			nextIndex++;
			size++;

		}

		// add()하기 전에 remove를 하면 error가 발생해야 함.
		public void remove() {
			if (nextIndex == 0) {
				throw new IllegalStateException();
			}
			Node n = lastReturned.next;
			Node p = lastReturned.prev;
			if(p == null) {
				head= n;
				head.prev=null;
				lastReturned=null;
				
			}else {
				p.next = next;
				lastReturned.prev =null;
				
			}
			
			if(n == null) {	//가장 마지막 노드라면
				tail =p;
				tail.next= null;
			}else {
				n.prev = p;
			}
			
			if(next==null) {
				lastReturned=tail;
			}else {
				lastReturned = next.prev;
			}
			size--;
			nextIndex--;
			

		}

	}

}
