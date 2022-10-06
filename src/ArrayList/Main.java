package ArrayList;

public class Main {
	public static void main(String []args) {
		ArrayList numbers = new ArrayList();
		numbers.addLast(10);
	    numbers.addLast(20);
	    numbers.addLast(30);
	    numbers.addLast(40);

	  
	    ArrayList.ListIterator it = numbers.listIterator();
	    while(it.hasNext()) {
	    	int number = (int) it.next();
	    	if( number == 30) {
	    		it.add(35);
	    	}
	    	if( number == 40) {
	    		it.remove();
	    	}
	    	//System.out.println(it.next());
	    }
	    
	    System.out.println(numbers);

	}
}
