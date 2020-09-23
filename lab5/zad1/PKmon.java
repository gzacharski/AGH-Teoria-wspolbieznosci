package lab5.zad1;

import java.util.ArrayList;

public class PKmon {
	private static final int bufferSize=5;
	private static final int quantityOfProducedNumbers=15;
	private static final int numberOfConverters=24;
	
	public static void main(String[] args) throws InterruptedException {
		long start=System.currentTimeMillis();
		
		ArrayList<Thread> threads=new ArrayList<>();
		Buffer[] buffer=new Buffer[numberOfConverters+1];
		for(int i=0; i<(numberOfConverters+1);i++) {
			buffer[i]=new Buffer(bufferSize);
		}
		
		Producer producer=new Producer(buffer[0], quantityOfProducedNumbers);
		producer.setName("A");
		threads.add(producer);
		
		for(int i=0;i<numberOfConverters;i++) {
			Converter tempConverter=new Converter(buffer[i], buffer[i+1], quantityOfProducedNumbers);
			tempConverter.setName(String.valueOf((char)(i+66)));
			threads.add(tempConverter);
		}
		
		Consumer consumer=new Consumer(buffer[numberOfConverters],quantityOfProducedNumbers);
		consumer.setName("Z");
		threads.add(consumer);
		
		for(Thread thread: threads) {
			thread.start();
		}
		
		for(Thread thread: threads) {
			thread.join();
		}
		
		long time=System.currentTimeMillis()-start;
		
		System.out.println("\nCzas wykonania: "+time+"ms");
	}
}
