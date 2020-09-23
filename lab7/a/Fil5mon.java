package lab7.a;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Fil5mon {
	private static final int NUMBER_OF_FILOSOPHERS=5;
	private static final int MAX_TIME=60000;
	private static final int MAX_VAR=100;
	private static int var=0;
	private static  PrintWriter PRINT_WRITER;

	public static void main(String[] args) {
		
		try {
			PRINT_WRITER=new PrintWriter("Problem5Filozofow_a.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		List<Thread> threads=new LinkedList<Thread>();
		
		Widelec[] widelec=new Widelec[NUMBER_OF_FILOSOPHERS];
		Filozof[] filozof=new Filozof[NUMBER_OF_FILOSOPHERS];
		
		for(int i=0;i<NUMBER_OF_FILOSOPHERS;i++) {
			widelec[i]=new Widelec(i);
		}
		
		for(int i=0;i<NUMBER_OF_FILOSOPHERS;i++) {
			filozof[i]=new Filozof(widelec[i%NUMBER_OF_FILOSOPHERS], widelec[(i+1)%NUMBER_OF_FILOSOPHERS]);
			filozof[i].setName("Filozof-"+i);
			threads.add(filozof[i]);
		}
		
		long startTime=System.currentTimeMillis();
		
		for(Thread thread: threads) {
			thread.start();
		}
		
		while(Fil5mon.getVar()<MAX_VAR) {
			int currentTime=(int)(System.currentTimeMillis()-startTime);
			if(currentTime>MAX_TIME) break;
		}

		long endTime=System.currentTimeMillis()-startTime;
		
		Fil5mon.log("Koniec - czas wykonania:\t"+endTime+" ms");
		PRINT_WRITER.close();
		System.exit(0);
	}
	
	public static synchronized void increment() {
		var++;
	}
	
	public static synchronized void log(String str) {
		System.out.println(str);
		PRINT_WRITER.println(str);
	}
	
	private static synchronized int getVar() {
		return var;
	}
}