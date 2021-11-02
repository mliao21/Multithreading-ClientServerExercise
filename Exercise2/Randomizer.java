import java.util.ArrayList;

/**
 * @author MELISSA LIAO
 *
 */
public class Randomizer implements Runnable{

	private int min;
	private int max;
	private int sum;
	private ArrayList<Integer> value;
	

	public Randomizer(int min, int max) {
		setMin(min);
		setMax(max);
		this.sum = 0;
		this.value = new ArrayList<Integer>();
	}
	
	public synchronized int generateRandom() {
		int rand = (int)(Math.random() * (max-min+1))+min;
		this.value.add(rand); // all generated random numbers will be appended to ArrayList
		sum += rand; //as random numbers are generated, it sums up as it goes
		return rand;
	}
	
	@Override
	public void run() {
		try {
			// calls generateRandom() function to generate and 
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
	
	public int getSum() {
		return sum;
	}

	public ArrayList<Integer> getValue() {
		return value;
	}

}
