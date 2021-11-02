
/**
 * @author MELISSA LIAO
 *
 */
public class Randomizer implements Runnable{

	private int min;
	private int max;
	private int value;

	public Randomizer(int min, int max) {
		setMin(min);
		setMax(max);
	}
	
	public int generateRandom() {
		this.value = (int)(Math.random() * (max-min+1))+min;
		return value; // a random number will be stored in value once function is called
	}
	
	@Override
	public void run() {
		try {// calls generateRandom() function to generate and 
			 // print out a random between 1 and 100.
			System.out.println(Thread.currentThread().getName() + ": " + generateRandom());
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

}
