package lab7.d.b;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import lab7.b.Widelce;
import lab7.b.Widelec;

public class Fil5mon {
	private static final int NUMBER_OF_FILOSOPHERS=5;
	private static final int MAX_TIME=60000;
	private static final int MAX_VAR=10000;
	private static int var=0;
	private static  PrintWriter PRINT_WRITER;
	public static boolean stopThread=false;
	
	public static void main(String[] args) {
		try {
			PRINT_WRITER=new PrintWriter("Problem5Filozofow_b_withoutSleeping.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		for(int i=0; i<1000; i++) {
			long time=solve();
			System.out.println(i);
			PRINT_WRITER.println(time);
		}
		PRINT_WRITER.close();
	}
	
	public static long solve() {
		
		List<Thread> threads=new LinkedList<Thread>();
		
		Widelec[] widelec=new Widelec[NUMBER_OF_FILOSOPHERS];
		Widelce[] widelce=new Widelce[NUMBER_OF_FILOSOPHERS];
		Filozof[] filozof=new Filozof[NUMBER_OF_FILOSOPHERS];
		
		for(int i=0;i<NUMBER_OF_FILOSOPHERS;i++) {
			widelec[i]=new Widelec(i);
		}
		
		for(int i=0;i<NUMBER_OF_FILOSOPHERS;i++) {
			widelce[i]=new Widelce(widelec[i%NUMBER_OF_FILOSOPHERS], widelec[(i+1)%NUMBER_OF_FILOSOPHERS]);
		}
		
		
		for(int i=0;i<NUMBER_OF_FILOSOPHERS;i++) {
			filozof[i]=new Filozof(widelce[i]);
			filozof[i].setName("Filozof-"+i);
			threads.add(filozof[i]);
		}
		
		long startTime=System.nanoTime();
		
		for(Thread thread: threads) {
			thread.start();
		}
		
		while(Fil5mon.getVar()<MAX_VAR) {
			int currentTime=(int)(System.currentTimeMillis()-startTime);
			if(currentTime>MAX_TIME) break;
		}

		long endTime=System.nanoTime()-startTime;
		
		stopThread=true;
		
		for(Thread thread: threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return endTime;
	}
		
	public static synchronized void increment() {
		var++;
	}
	
	public static synchronized void log(String str) {
		//System.out.println(str);
		//PRINT_WRITER.println(str);
	}
	
	private static synchronized int getVar() {
		return var;
	}
	
	
}
