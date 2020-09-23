package lab6;

import java.util.Random;

public class Consumer implements Runnable{
	private IMyList myList;
	private Random random;
	private int iterations;
	private int range;
	
	public Consumer(IMyList myList, int iterations, int range) {
		this.myList=myList;
		this.random=new Random();
		this.iterations=iterations;
		this.range=range;
	}
	
	@Override
	public void run() {
		for(int i=0; i<iterations; i++) {
			
			int tmp=random.nextInt(range);
			
			boolean removed=myList.remove((Object)tmp); 
			
			if(Main.VERBOSE) {
				if(removed) {
					System.out.println(Thread.currentThread().getName() +"\t(iteration="+(i+1)+")\tremoves:\t"+tmp);
				}else {
					System.out.println(Thread.currentThread().getName() +"\t(iteration="+(i+1)+")\t: There is no "+tmp+" on the list.");
				}
			}
		}
	}
}
