package HashTable;

public class HashTable {
	
	public Slot [] hashTable;
	
	//Constructor
	public HashTable(Integer size) {
		this.hashTable = new Slot[size];
	}
	
	//Slot 클래스
	private class Slot{
		Object value;
		Slot(Object value){
			this.value = value;
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
			if(this.hashTable[address]!=null) {
				this.hashTable[address].value = value;
				return true;
			}else {
				this.hashTable[address] = new Slot(value);
				return true;
			}
		}catch(Exception e) {
			
			return false;
		}

	}//saveData
	
	//READ
	public Object getData(String key) {
		int address = this.hasFunc(key);
		if(this.hashTable[address] != null) {
			return this.hashTable[address].value;
		}else {
			return null;
		}	
	}
	
	
}
