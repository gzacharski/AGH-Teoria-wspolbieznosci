package lab4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static SharedResource sharedResource;
	public static PrintWriter printWriter;
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		if(args.length==0) {
			help();
			return;
		}
		
		switch(args[0]) {
			case "S":
				switch(args[1]) {
					case "F":
						sharedResource=new SharedResourceSemaphoreFIFO();
						break;
					case "R":
						sharedResource=new SharedResourceSemaphoreFavorsReader();
						break;
					case "W":
						sharedResource=new SharedResourceSemaphoreFavorsWriter();
						break;
					default:
						help();
						return;
				}
				break;
			case "C":
				switch(args[1]) {
					case "F":
						sharedResource=new SharedResourceConditionsFIFO();
						break;
					case "R":
						sharedResource=new SharedResourceConditionsFavorsReader();
						break;
					case "W":
						sharedResource=new SharedResourceConditionsFavorsWriter();
						break;
					default:
						help();
						return;
				}
				break;
			default:
				help();
				return;
		}
		
		int writerCount=(args.length>2)?Integer.parseInt(args[2]):1;
		int readerCount=(args.length>3)?Integer.parseInt(args[3]):10;
		int writerEachRepeats=(args.length>4)?Integer.parseInt(args[4]):100;
		int readerEachRepeats=(args.length>5)?Integer.parseInt(args[5]):100;
		
		String fileName="ReaderWriterProblem_"+args[0]+"_"+args[1]+
				"_writerCount-"+writerCount+"_readerCount-"+readerCount+
				"_writerRepeats-"+writerEachRepeats+"_readerRepeats-"+readerEachRepeats+".txt";
		
		printWriter=new PrintWriter(fileName);
		
		long start=System.currentTimeMillis();
		solve(writerCount,readerCount, writerEachRepeats, readerEachRepeats);
		long stop=System.currentTimeMillis();
		
		System.out.printf("Done - time: \t%d ms\n", stop-start);
	}
	

	private static void solve(int writerCount, int readerCount, int writerEachRepeats, int readerEachRepeats) 
			throws InterruptedException {
		
		List<Thread> threads =new ArrayList<>();
		
		for(int i=0; i<writerCount; i++) {
			threads.add(new Writer(sharedResource,writerEachRepeats));
		}
		
		for(int i=0; i<readerCount; i++) {
			threads.add(new Reader(sharedResource,readerEachRepeats));
		}
		
		for(Thread thread: threads) {
			thread.start();
		}
		
		for(Thread thread: threads) {
			thread.join();
		}
	}

	private static void help() {
		System.out.println("Use:");
	}
}
