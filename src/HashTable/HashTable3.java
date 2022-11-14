package HashTable;

/*
 * Linear Probing 기법을 통한 출돌 해결
 */
public class HashTable3 {

	public Slot[] hashTable;

	// Constructor
	public HashTable3(Integer size) {
		this.hashTable = new Slot[size];
	}

	// Slot 클래스
	private class Slot {
		String key;
		Object value;

		Slot(String key, Object value) {
			this.key = key;
			this.value = value;
		}
	}

	// String key를 가장 앞 글자를 이용해 주소값을 반환하는 Hash Function
	private int hasFunc(String key) {
		return (int) (key.charAt(0)) % this.hashTable.length;
	}

	// CREATE
	public boolean saveData(String key, Object value) {
		try {
			int address = this.hasFunc(key);
			if (this.hashTable[address] != null) {
				//만약 key가 같다면 update
				if(this.hashTable[address].key == key) {
					this.hashTable[address].value = value;
					return true;
				}else {
					//key가 같지 않다면 다른 address에 저장된 것. 이제 빈자리를 찾아 탐색. 
					int currAddress = address + 1;
					while (this.hashTable[currAddress] != null) {
						//이때도 만약 같은 key를 찾으면 update
						if(this.hashTable[address].key == key) {
							this.hashTable[address].value = value;
							return true;
						}
						currAddress++;

						if (currAddress >= this.hashTable.length) {
							return false;
						}
					}//while
					//currAddress = null인 값을 찾았다.
					this.hashTable[currAddress] = new Slot(key, value);
					return true;
					
				}//else
			} else {// null이면
				this.hashTable[address] = new Slot(key, value);
			}
			return true;
		} catch (Exception e) {

			return false;
		}

	}// saveData

	// READ
	public Object getData(String key) {
		int address = this.hasFunc(key);
		if (this.hashTable[address] != null) {
			
			if(this.hashTable[address].key == key) {
				return this.hashTable[address].value;
				
			}else {
				//하나씩 이동해 가며 key에 해당하는 값을 찾아야함. 
				// 참고: 다음 코드를 수정합니다.
                // Integer currAddress = address + 1;                 
                // 예외 케이스로, 키에 해당하는 주소가 가장 마지막 슬롯일 경우, 
                // this.hashTable[address + 1] 에 해당하는 배열은 없기 때문에, 
                // 예외 케이스에서도 동작하도록 currAddress 는 address 만 대입하고 진행합니다
				int currAddress = address;
				while(this.hashTable[currAddress]!=null) {
					if(this.hashTable[currAddress].key == key) {
						return this.hashTable[currAddress].value;
					}else {
						currAddress++;
						if(currAddress >= this.hashTable.length) {
							return null;
						}
					}//else
				}//while
				return null;//null이 아닌 동안에 찾지 못했다면 저장이 안된 것.
				
			}//else
		} else {
			return null;
		}
	}//getDate

}
