// Answer for Task 3: Runnable is implemented and replaced
// from original extension of Thread class.

public class Resource implements Runnable{

	int counter;
	
	// Answer for Task 2: The issue is fixed by adding the 
	// "synchronized" keyword to lock in any process done within 
	// the function and prevent duplicate/skipping values in the 
	// result of multi-threading. Once a thread is running the function,
	// no other thread can get in to run the same value.
	public synchronized int increment() {
		return counter++;
	}
	
	@Override
	public void run() {
		for(int i = 0; i<10; i++){
			try {
				System.out.println(increment());
				
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
