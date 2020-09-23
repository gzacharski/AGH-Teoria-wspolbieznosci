package lab5.zad2;

import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final int M=10;
	private static final int QUANTITY_OF_PRODUCERS=10;
	private static final int QUANTITY_OF_CONSUMERS=10;
	
	public static void main(String[] args) {
		
		List<Thread> threads=new ArrayList<>();
		
		Buffer buffer=new Buffer(2*M);
		
		for(int i=0; i<QUANTITY_OF_PRODUCERS; i++) {
			Producer tempProducer=new Producer(buffer,M);
			tempProducer.setName("Producer "+(i+1));
			threads.add(tempProducer);
		}
		
		for(int i=0; i<QUANTITY_OF_CONSUMERS; i++) {
			Consumer tempConsumer=new Consumer(buffer, M);
			tempConsumer.setName("Consumer "+(i+1));
			threads.add(tempConsumer);
		}
		
		for(Thread thread: threads) {
			thread.start();
		}
	}
}
