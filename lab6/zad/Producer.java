package lab6;

import java.util.Random;

public class Producer implements Runnable{
	private IMyList myList;
	private Random random;
	private int iterations;
	private int range;
	
	public Producer(IMyList myList, int iterations, int range) {
		this.myList=myList;
		this.random=new Random();
		this.iterations=iterations;
		this.range=range;
	}
	
	@Override
	public void run() {
		for(int i=0; i<iterations; i++) {
			int temp=random.nextInt(range);
	
			myList.add(temp);
			
			if(Main.VERBOSE) System.out.println(Thread.currentThread().getName() +"\t(iteration="+(i+1)+")\tadds:\t"+temp);
			
		}
	}
}
