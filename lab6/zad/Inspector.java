package lab6;

import java.util.Random;

public class Inspector implements Runnable{
	private IMyList myList;
	private Random random;
	private int iterations;
	private int range;
	
	public Inspector(IMyList myList, int iterations, int range) {
		this.myList=myList;
		this.random=new Random();
		this.iterations=iterations;
		this.range=range;
	}
	
	@Override
	public void run() {
		for(int i=0; i<iterations; i++) {
			int temp=random.nextInt(range);
			
			boolean contains=myList.contains((Object)temp);
			
			if(Main.VERBOSE) System.out.println(Thread.currentThread().getName() +"\t(iteration="+(i+1)+")\tDo the list contain :\t"+temp +"?\t"+contains);
		}
		
	}

}
