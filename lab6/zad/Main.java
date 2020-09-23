package lab6;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
	private static int NUMBER_OF_THREADS=100;
	private static final int NUMBER_RANGE=10;
	private static final int THREAD_ITERATIONs=100;
	
	public static final boolean VERBOSE=false;
	public static final boolean PRINT_TO_FILE=true;
	
	public static PrintWriter printWriter;
	private static final String fileName="lab6_wyniki_5.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		
		if(PRINT_TO_FILE) printWriter=new PrintWriter(fileName);
		
		Operation operation=new Operation(NUMBER_OF_THREADS, NUMBER_RANGE,THREAD_ITERATIONs);
		
		for(int i=10; i<=NUMBER_OF_THREADS;i++) {
			operation.setNumberOfThreads(i);
			
			System.out.println("Comparing ADD method:");
			operation.compare(Action.add);
	
			System.out.println("Comparing CONTAIN method:");
			operation.compare(Action.contain);
			
			System.out.println("Comparing REMOVE method:");
			operation.compare(Action.remove);
			
			System.out.println();
		}
		
		System.out.println("Comparing done");
		if(PRINT_TO_FILE) printWriter.close();;
	}
}
