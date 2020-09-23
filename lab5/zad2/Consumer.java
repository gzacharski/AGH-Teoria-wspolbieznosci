package lab5.zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Consumer extends Thread {
	private Buffer buffer;
	private int maxQuantityToConsume;
	private Random random;
	
	public Consumer(Buffer buffer, int  maxQuantityToConsume) {
		this.buffer=buffer;
		this.maxQuantityToConsume=maxQuantityToConsume;
		this.random=new Random();
	}
	
	public void run() {
		while(true) {
			int quantityToGet=1+random.nextInt(maxQuantityToConsume);
			
			List<Integer> list=new ArrayList<>();
			
			list=buffer.get(quantityToGet);
			
			System.out.println(Thread.currentThread().getName() + " gets " +list.toString()+" from the buffer.");
		}
	}
}
