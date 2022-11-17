package Tree;

/*
 * Binary Search Tree
 */
public class MyTree {

	Node head;

	// parent child 관계 ?
	public MyTree() {

	}

	public class Node {
		Node left;
		Node right;
		int value;

		public Node(int data) {
			this.value = data;
			this.left = null;
			this.right = null;
		}
	}

	// Create
	public boolean insertNode(int data) {
		// 1. Node가 하나도 없을 때
		if (head == null) {
			this.head = new Node(data);
		} else {
			// 2. Node가 이미 존재할 때
			// 노드의 탐색
			Node findNode = this.head;

			while (true) {

				if (findNode.value == data)
					return false;// 이미 존재

				// 2-2. 노드의 value > data
				if (data < findNode.value) {
					if (findNode.left != null) {
						findNode = findNode.left;
					} else {
						findNode.left = new Node(data);
						break;
					}
					// 2-1. 노드의 value < data
				} else {
					if (findNode.right != null) {
						findNode = findNode.right;
					} else {// null이면
						findNode.right = new Node(data);
						break;
					}

				}
			} // while
		} // else
		return true;
	}// insertNode

	// Search
	public Node search(int data) {
		// 1. node가 하나도 없을 때
		if (this.head == null) {
			return null;
		}
		// 2. node가 하나 이상 일 때
		else {
			Node findNode = head;
			while (findNode != null) {

				if (data == findNode.value) {
					return findNode;
				} else if (data > findNode.value) {
					findNode = findNode.right;

				} else {
					findNode = findNode.left;
				}
			} // while
			return null;
		} // else

	}// search

	// Delete **매우 복잡
	public boolean delete(int value) {
		/*
		 * 1.노드가 없는 경우 2.노드가 있는 경우 2-1. 삭제할 노드가 leaf Node인 경우 => 간단히 연결 삭제 2-2. 삭제할 노드가
		 * Child Node를 한 개 가지고 있는 경우 => parent와 child를 연결 2-3. 삭제할 노드가 Child Node를 2 개
		 * 이상 가지고 있는 경우 => 삭제할 노드의 오른쪽 자식 중, 가장 작은 값을 삭제할 노드의 parent node가 가리키도록 한다.
		 */
		boolean searched = false;

		Node currParentNode = this.head;
		Node currNode = this.head;

		// 1. Node가 하나도 없을 때
		if (head == null) {
			return false;
		} else {

			// 히든 케이스: Node가 단지 하나만 있고, 해당 node가 삭제할 node일 때
			if (this.head.value == value && this.head.left == null && this.head.right == null) {
				head = null;
				return true;
			}

			// 삭제할 노드를 탐색
			while (currNode != null) {

				if (value == currNode.value) {
					searched = true;
					break;
				} else if (value > currNode.value) {
					currParentNode = currNode;
					currNode = currNode.right;

				} else {
					currParentNode = currNode;
					currNode = currNode.left;
				}
			}
			// 삭제할 노드를 못찾았은 경우과 찾은 경우의 처리를 확인하기 위하여 searched 변수 사용
			if (searched == false) {
				return false;
			}

			/*
			 * 참고) 여기까지 currNode에는 해당 Node currParentNode에는 해당 부모 노드
			 */

			// 2-1. 삭제할 노드가 leaf인 경우
			if (currNode.left == null && currNode.right == null) {
				// currParentNdoe의 왼쪽인 경우
				if (currNode.value < currParentNode.value) {
					currParentNode.left = null;
					currNode = null;
				} else {
					currParentNode.right = null;
					currNode = null;
				}
				return true;
			}
			// 2-2-1. 삭제할 노드의 Child Node가 1개인 경우 ( 왼쪽일 경우)
			else if (currNode.left != null && currNode.right == null) {
				// 삭제할 노드가 currParentNode의 왼쪽에 있는 경우
				if (value < currParentNode.value) {
					currParentNode.left = currNode.left;
					currNode = null;
				} else { // 삭제할 노드가 currParentNode의 오른쪽에 있는 경우
					currParentNode.right = currNode.left;
					currNode = null;

				}
				return true;

			}
			// 2-2-2. 삭제할 노드의 Child Node가 1개인 경우( 오른쪽일 경우)
			else if (currNode.left == null && currNode.right != null) {
				// 삭제할 노드가 currParentNode의 왼쪽에 있는 경우
				if (value < currParentNode.value) {
					currParentNode.left = currNode.right;
					currNode = null;
				} else { // 삭제할 노드가 currParentNode의 오른쪽에 있는 경우
					currParentNode.right = currNode.right;
					currNode = null;

				}
				return true;

			}
			// 2-3. 삭제할 노드가 Child Node를 2 개 이상 가지고 있는 경우
			else {
				// 2-3-1. 삭제할 노드가 부모 노드의 왼쪽에 있을 때
				if (value < currParentNode.value) {
					Node changeNode = currNode.right; // 삭제할 노드의 오른쪽 노드를 공략.
					Node changeParentNode = currNode.right;
					// 오른쪽 노드 중 가장 작은 노드를 찾아라!
					while (changeNode.left != null) {
						changeParentNode = changeNode;
						changeNode = changeNode.left;

					}
					// 여기까지 실행되면, changeNode에는 삭제할 노드의 오른쪽 노드중에서 가장 작은 값을 가진 노드
					// 2-3-1-1. changeNode가 leaf 노드인 경우 => 왼쪽 자식 노드는 있을 수 없음.
					if (changeNode.right != null) {
						// 오른쪽 노드가 있을 때 
						changeParentNode.left = changeNode.right;// change node의 right를 change node자리에
					} else {
						changeParentNode.left = null;
					}
					//오른쪽 노드를 changNode 자리에 넣는다.
					//currentNode의 왼쪽을 changeNode로 연결
					currParentNode.left = changeNode;
					changeNode.left = currNode.left;
					changeNode.right = currNode.right;
					
					currNode =null;

					

				}

				// 2-3-2. 삭제할 노드가 부모 노드의 오른쪽에 있을 때
				else if (value > currParentNode.value) {
					Node changeNode = currNode.right; // 삭제할 노드의 오른쪽 노드를 공략.
					Node changeParentNode = currNode.right;
					// 오른쪽 노드 중 가장 작은 노드를 찾아라!
					while (changeNode.left != null) {
						changeParentNode = changeNode;
						changeNode = changeNode.left;

					}
					// 여기까지 실행되면, changeNode에는 삭제할 노드의 오른쪽 노드중에서 가장 작은 값을 가진 노드
					// 2-3-2-1. changeNode가 leaf 노드인 경우 => 왼쪽 자식 노드는 있을 수 없음.
					if (changeNode.right != null) {
						// 오른쪽 노드가 있을 때 
						changeParentNode.left = changeNode.right;// change node의 right를 change node자리에
					} else {
						changeParentNode.left = null;
					}
					//오른쪽 노드를 changNode 자리에 넣는다.
					//currentNode의 오른쪽 changeNode로 연결
					currParentNode.right = changeNode;
					changeNode.left = currNode.left;
					changeNode.right = currNode.right;
					
					currNode =null;
				}
				return true;
				

			}//else 2-3

		} // else

	}// delete

}
