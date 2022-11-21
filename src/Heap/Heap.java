package Heap;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 이진 트리의 head는 ArrayList index 1로 대체.
 * 
 * 자식 노드를 구하려면
 * - 왼쪽 자식 노드 = 부모 인덱스 번호*2
 * - 오른쪽 자식 노드 = 부모 인덱스 번호*2 - 1 
 * 
 * 부모 노드를 구하려면 
 *  자식 노드 / 2
 */
public class Heap {

	ArrayList<Integer> heapArray = null;

	public Heap() {
	}

	public Heap(int data) {
		heapArray = new ArrayList<Integer>();
		heapArray.add(null);// 0번 인덱스
		heapArray.add(data);
	}

	public boolean insert(int data) {

		int inserted_idx, parent_idx;

		if (this.heapArray == null) {
			this.heapArray = new ArrayList<Integer>();
			this.heapArray.add(null);// 0번 인덱스
			this.heapArray.add(data);
			return true;
		}
		// 일단 가장 아래인 depth에 넣는다.
		this.heapArray.add(data);
		// 부모 노드의 데이터 > 자식 노드의 데이터를 구현.
		inserted_idx = this.heapArray.size() - 1;

		while (this.move_up(inserted_idx)) { // .. while(parentNode < preNode) 이렇게 해도 되지 않을까
			parent_idx = inserted_idx / 2;
			Collections.swap(heapArray, inserted_idx, parent_idx);
			inserted_idx = parent_idx;//이제 inserted 값은 parent_idx의 값이 되었음.
		}
		return true;

	}

	private boolean move_up(int inserted_idx) {
		// 현재 노드와 부모 노드가 바꿔야하는 지 확인
		if (inserted_idx <= 1) { //root 노드에 있거나, 범위를 벗어났거나
			return false;
		}
		int parent_idx = inserted_idx / 2;
		if (this.heapArray.get(inserted_idx) > this.heapArray.get(parent_idx)) {
			return true;
		} else {
			return false;
		}
	}

	// 삭제 : 가장 큰 값을 return and remove
	public int pop() {

		int returned_data, popped_idx, left_child_popped_idx, right_child_popped_idx;

		if (this.heapArray == null)
			return -1;
		else {
			returned_data = this.heapArray.get(1);
			// root에 가장 마지막에 넣은 데이터를 set
			this.heapArray.set(1, this.heapArray.get(this.heapArray.size() - 1));
			this.heapArray.remove(this.heapArray.size() - 1);
			popped_idx = 1;//맨처음의 popped_idx =>  바뀐 root node

			while (this.move_down(popped_idx)) { // 자식노드가 있는데 해당 숫자보다 커서(당연히 크겠지) 바꿔야한다면 true반환 더이상 바꾸지 않아도 될 때까지 반복

				// true이면 이제 바꿔야 함.
				left_child_popped_idx = popped_idx * 2;
				right_child_popped_idx = popped_idx * 2 + 1;

				// CASE2: 왼쪽 자식 노드만 있을 때
				if (right_child_popped_idx >= this.heapArray.size()) {
					Collections.swap(heapArray, left_child_popped_idx, popped_idx);
					popped_idx = left_child_popped_idx;

				}
				// CASE 3: 왼쪽 오른쪽 자식 노드가 모두 있을 때
				else {
					if(this.heapArray.get(right_child_popped_idx) > this.heapArray.get(left_child_popped_idx)) {
						//오른쪽이 더 크다면 오른쪽 자식과 바꾼다.
						Collections.swap(heapArray, right_child_popped_idx, popped_idx);
						popped_idx = right_child_popped_idx;
					}else {
						//왼쪽이 더 크다면 왼쪽 자식과 바꾼다.
						Collections.swap(heapArray, left_child_popped_idx, popped_idx);
						popped_idx = left_child_popped_idx;
					}
				}
			} // while
		}//else
		return returned_data;
	}//pop()
	
	//바꿔야 하는 지 true false만 알려주는 method
	public boolean move_down(int popped_idx) {

		int left_child_popped_idx, right_child_popped_idx;

		left_child_popped_idx = popped_idx * 2;
		right_child_popped_idx = popped_idx * 2 + 1;

		// CASE1: 자식 노드가 하나도 없을 때 = 왼쪽 자식 노드가 없을 때
		if (left_child_popped_idx >= this.heapArray.size()) {
			return false;
		}
		// CASE2: 왼쪽 자식 노드만 있을 때
		else if (right_child_popped_idx >= this.heapArray.size()) {// 오른쪽 자식 노드가 범위를 벗어났다면
			if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) // 왼쪽 자식 노드가 더 크다면
				return true;// 바꿔줘야 한다.
			else {
				return false;
			}
		}
		// CASE 3: 왼쪽 오른쪽 자식 노드가 모두 있을 때
		else {
			if (this.heapArray.get(left_child_popped_idx) > this.heapArray.get(right_child_popped_idx)) {// 왼쪽 이 더 클 때
				if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx))
					return true; // 왼쪽 자식 노드가 popped_idx보다 크다면
				else
					return false;
			} else {
				if (this.heapArray.get(popped_idx) < this.heapArray.get(right_child_popped_idx))// 오른쪽이 더 클 때
					return true; // 오른쪽 자식 노드가 popped_idx보다 크다면
				else
					return false;
			}
		}

	}

}
