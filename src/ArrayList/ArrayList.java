package ArrayList;

public class ArrayList{
	
	private int size =0;	//arraylist의 크기
	private Object[] elementData = new Object[100];	//ArrayList가 될 배열
	
	public void add(int index, Object element){
		
		for(int i =size-1; i>= index; i--){	// 뒤에서부터 넣어주어야 중간 데이터의 손실이 없다. 
			/*
			 * ex) 1 2 3 에 add(0, 0)이라고 하면
			 *  1 2 3 3 ->  1 2 2 3 -> 1 1 2 3 -> 0 1 2 3 와 같은 과정으로 add 된다.
			 */
			elementData[i+1]= elementData[i];
		}
		elementData[index]= element;
		size++;
	}
	
	public void addFirst(Object element){
		add(0,element);

	}
	
	public boolean addLast(Object element){
		elementData[size] = element;
		size++;
		return true;
	}
	

	public Object remove(int index) {
		
		Object removedItem = elementData[index];
		
		for(int i=index+1 ; i <= size-1 ; i++) { 	//move back existing data to forward one block
			elementData[i-1] = elementData[i];
		}
		//remove the last data
		elementData[size] = null;
		size--; 
		return removedItem;
	}
	
	@Override
	public String toString() {
		
		String result = "[";
		for(int i =0; i<size; i++) {
			if(i<size-1) {
				result += elementData[i] + ",";
			}else {
				result += elementData[i] + "]";
				
			}
		}
		return result;
		//return super.toString(); >> ArrayList.ArrayList@15db9742
	}

	public Object removeFirst() {
		return remove(0);
		
	}

	public Object removeLast() {
		return remove(size-1);
		
	}

	public Object get(int index) {
		
		return elementData[index];
	}

	public int size() {
		return size;
	}

	public int indexOf(Object o) {
		for(int i=0; i<size; i++) {
			if(elementData[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	public ListIterator listIterator() {
		return new ListIterator();
	}
	public class ListIterator{
		
		private int nextIndex=0;

		public Object next() {
			return elementData[nextIndex++];
		}

		public boolean hasNext() {

			return nextIndex <= size-1;
		}
		
		public Object previous() {
			return elementData[--nextIndex];
		}
		
		public boolean hasPrevious() {
			return nextIndex > 0;
		}
		
		// ** next()를 하면 preIndex가 +1 된 상태임을 유의
		public void add(Object o) {
			ArrayList.this.add(nextIndex++,o);
		}

		public void remove() {
			ArrayList.this.remove(nextIndex-1);
			nextIndex--;
		}
		
		
		
	}
	

}
