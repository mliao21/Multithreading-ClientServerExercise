// Answer for Task 1: There are some values in the counter 
// that doesn't increase or appears more than once is because
// multi-threading may run the same value at the same time if no
// locking mechanism is used to prevent from duplicate/skipping
// results.

public class SimpleThread {
	
	public static void main(String args[]) {
		Resource resource = new Resource();
		Thread t = new Thread(resource);
		Thread s = new Thread(resource);
		
		t.start();
		s.start();
	}

}
