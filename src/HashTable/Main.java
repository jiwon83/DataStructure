package HashTable;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable h = new HashTable(10);
		h.saveData("apple", "I Like apple juice!");
		System.out.println(h.getData("apple"));
		

	}

}
