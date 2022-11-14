package HashTable;
/*
 * Chaining 기법을 통한 충돌 개선
 */
public class HashTable2 {
	
	public Slot [] hashTable;
	
	//Constructor
	public HashTable2(Integer size) {
		this.hashTable = new Slot[size];
	}
	
	//Slot 클래스
	private class Slot{
		String key;
		Object value;
		Slot next;
		
		Slot(String key, Object value){
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}
	//String key를 가장 앞 글자를 이용해 주소값을 반환하는 Hash Function
	private int hasFunc(String key) {
		return (int)(key.charAt(0)) % this.hashTable.length;
	}
	
	//CREATE
	public boolean saveData(String key, Object value) {
		try {
			int address = this.hasFunc(key);
			
			if(this.hashTable[address]!=null) {//값이 이미 있다면 링크드리스트일 수 있음. 
				
				//링크드리스트의 head 설정
				Slot findSlot = this.hashTable[address];
				Slot prevSlot = this.hashTable[address];
				
				while( findSlot != null) {
					if(findSlot.key == key) { //이미 내키에 해당하는 데이터가 있으면. 즉 아예 key가 같다면
						findSlot.value = value; //update
						return true;
					} else { //그렇지 않다면,
						prevSlot = findSlot; 
						findSlot = findSlot.next; //fidSlot이 null이 될때까지 next
					}
					
				}
				//현재 find slot이 null 값인 맨 끝의 slot(prevSlot)에 현재 내 key, value를 저장한다. 
				prevSlot.next = new Slot(key, value);
				
				return true;
			}else {
				this.hashTable[address] = new Slot(key,value);
				return true;
			}
		}catch(Exception e) {
			
			return false;
		}

	}//saveData
	
	//READ
	public Object getData(String key) {
		//기존에는 address만으로 가져왔지만
		//이제 해당 address에 링크드리스트가 있을 수 있음. 
		int address = this.hasFunc(key);
		
		if(this.hashTable[address] != null) {
			
			//address뿐 아니라 key까지 일치하는지 봐야함. 
			
			Slot findSlot = this.hashTable[address];
			
			while(findSlot != null) {
				if(findSlot.key == key) { 
					return findSlot.value; 
					
				} else {
					findSlot = findSlot.next; //fidSlot이 null이 될때까지 next
				}
				
			}
			return null;
			
		} else {
			return null;
		}	
	}
	
	
}
