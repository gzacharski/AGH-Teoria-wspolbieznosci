package lab6;

import java.util.ArrayList;
import java.util.List;

public class Operation {
	private int numberOfThreads;
	private int numberRange;
	private int threadIterations;
	
	private IMyList myListNodeLock;
	private IMyList myListGlobalLock;
	
	
	public Operation(int numberOfThreads, int numberRange, int threadIterations) {
		this.numberOfThreads=numberOfThreads;
		this.numberRange=numberRange;
		this.threadIterations=threadIterations;
		myListNodeLock=new MyListNodeLock();
		myListGlobalLock=new MyListGlobalLock();
	}
	
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads=numberOfThreads;
	}

	public void compare(Action action) {
		
		switch(action) {
		
			case add:
				testAdd(myListGlobalLock);
				testAdd(myListNodeLock);
				break;
				
			case contain:
				testContains(myListGlobalLock);
				testContains(myListNodeLock);
				break;
				
			case remove:
				testRemove(myListGlobalLock);
				testRemove(myListNodeLock);
				break;
				
			default:
				System.err.println("Invalid action.");
		}
	}
	
	public void printLists() {
		myListGlobalLock.print();
		myListNodeLock.print();
	}
	
	
	private void testAdd(IMyList list) {
		List<Thread> threads=new ArrayList<>();
		long startTime;
		long duration;

		for(int i=0; i<numberOfThreads; i++) {
			Thread tempThread=new Thread(new Producer(list, threadIterations, numberRange));
			tempThread.setName(Producer.class.getSimpleName()+"-"+(i+1));
			threads.add(tempThread);
		}
		
		startTime=System.currentTimeMillis();
		
		for(Thread thread: threads) {
			thread.start();
		}
		
		for(Thread thread: threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		duration=System.currentTimeMillis()-startTime;
		threads.clear();
		System.out.println(list.getClass().getSimpleName()+ ": adding time equals: " +duration + " ms.");
		if(Main.PRINT_TO_FILE) Main.printWriter.println(numberOfThreads +"\t"+list.getClass().getSimpleName()
				+ ": adding time equals: [ms]\t" +duration );
	}
	
	private void testContains(IMyList list) {
		List<Thread> threads=new ArrayList<>();
		long startTime;
		long duration;

		for(int i=0; i<numberOfThreads; i++) {
			Thread tempThread=new Thread(new Inspector(list, threadIterations, numberRange));
			tempThread.setName(Inspector.class.getSimpleName()+"-"+(i+1));
			threads.add(tempThread);
		}
		
		startTime=System.currentTimeMillis();
		
		for(Thread thread: threads) {
			thread.start();
		}
		
		for(Thread thread: threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		duration=System.currentTimeMillis()-startTime;
		threads.clear();
		System.out.println(list.getClass().getSimpleName()+ ": inspecting time equals: " +duration + " ms.");
		if(Main.PRINT_TO_FILE) Main.printWriter.println(numberOfThreads +"\t"+list.getClass().getSimpleName()
				+ ": inspecting time equals: [ms]\t" +duration );
	}
	
	private void testRemove(IMyList list) {
		List<Thread> threads=new ArrayList<>();
		long startTime;
		long duration;

		for(int i=0; i<numberOfThreads; i++) {
			Thread tempThread=new Thread(new Consumer(list, threadIterations, numberRange));
			tempThread.setName(Consumer.class.getSimpleName()+"-"+(i+1));
			threads.add(tempThread);
		}
		
		startTime=System.currentTimeMillis();
		
		for(Thread thread: threads) {
			thread.start();
		}
		
		for(Thread thread: threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		duration=System.currentTimeMillis()-startTime;
		threads.clear();
		System.out.println(list.getClass().getSimpleName()+ ": removing time equals: " +duration + " ms.");
		if(Main.PRINT_TO_FILE) Main.printWriter.println(numberOfThreads +"\t"+list.getClass().getSimpleName()
				+ ": removing time equals: [ms]\t" +duration );
	}
	
}
