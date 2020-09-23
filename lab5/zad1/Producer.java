package lab5.zad1;

import java.util.Random;

public class Producer extends Thread{
	private Buffer buffer;
	private int iterations;
	
	public Producer(Buffer buffer, int iterations) {
		this.buffer=buffer;
		this.iterations=iterations;
	}
	
	public void run() {
		for(int i=0; i<iterations; i++) {
			
			Random random=new Random();
			int value=random.nextInt(1000);
			
			System.out.println("Thread " + Thread.currentThread().getName() + " produces and puts " +value +" into the buffer. Iteration " +(i+1)+ " out of "+iterations);
			buffer.put(value);
		
		}
	}
}
