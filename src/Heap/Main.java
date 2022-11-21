package Heap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heap heap = new Heap();

		heap.insert(10);
		heap.insert(15);
		heap.insert(8);
		heap.insert(5);
		heap.insert(4);
		heap.insert(20);
		heap.pop();

		for (int i = 0; i < heap.heapArray.size(); i++) {
			System.out.println(heap.heapArray.get(i));

		}

	}

}
