package lab5.zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer extends Thread{
	private Buffer buffer;
	private int maxQuantityToProduce;
	private Random random;
	
	public Producer(Buffer buffer, int maxQuantityToProduce) {
		this.buffer=buffer;
		this.maxQuantityToProduce=maxQuantityToProduce;
		this.random=new Random();
	}
	
	public void run() {
		while(true) {
			List<Integer> list=new ArrayList<Integer>();
			
			int quantityToPut=1+random.nextInt(maxQuantityToProduce);
			
			for(int i=0;i<quantityToPut;i++) {
				int value=random.nextInt(1000);
				list.add(value);
			}
			
			buffer.put(list);
			
			System.out.println(Thread.currentThread().getName() + " puts " +list.toString() +" into the buffer.");
		}
	}
}
