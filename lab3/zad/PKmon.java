import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class PKmon {
	public static PrintWriter printWriter;
	public static List<Integer> table=new ArrayList<Integer>();
	
	public static void main(String[] args) {
		LinkedList<String> list=new LinkedList<>(Arrays.asList(args));
		
		String fileName;
		
		int buforCapacity;
	
		int numberOfProducers;
		int numberOfConsumers;
		
		List<Integer> producerTimeDelay;
		List<Integer> consumerTimeDelay;
		
		Buffer bufor;
		
		try {
			fileName=list.removeFirst();
			
			String type=list.removeFirst();
			
			buforCapacity=Integer.parseInt(list.removeFirst());
			
			switch(type) {
				case "zad1":
					bufor=new BufferCondition(buforCapacity);
					break;
				case "zad2":
					bufor=new BufferSemaphore(buforCapacity);
					break;
				default:
					throw new NoSuchElementException();
			}
			
			numberOfProducers=Integer.parseInt(list.removeFirst());
			numberOfConsumers=Integer.parseInt(list.removeFirst());
			
			String setDelayTime=list.removeFirst(); 
			
			producerTimeDelay=new ArrayList<>();
			consumerTimeDelay=new ArrayList<>();
			
			switch(setDelayTime) {
			case "Y":
				for(int i=0; i<numberOfProducers; i++) {
					producerTimeDelay.add(i, Integer.parseInt(list.removeFirst()));
				}
				
				for(int i=0; i<numberOfConsumers; i++) {
					consumerTimeDelay.add(i,Integer.parseInt(list.removeFirst()));
				}
				break;
			default:
				for(int i=0; i<numberOfProducers; i++) {
					producerTimeDelay.add(i, 0);
				}
				
				for(int i=0; i<numberOfConsumers; i++) {
					consumerTimeDelay.add(i,0);
				}
			}
		
			
		}catch(NoSuchElementException e) {
			help();
			return;
		}
		
		
		try {
			solveProducerConsumerProblem(fileName,numberOfProducers, numberOfConsumers, producerTimeDelay,consumerTimeDelay,bufor);
			printInfo();
		} catch (FileNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void solveProducerConsumerProblem(String fileName, int numberOfProducers, int numberOfConsumers, 
			List<Integer> producerTimeDelay, List<Integer> consumerTimeDelay, 
			Buffer bufor) throws FileNotFoundException, InterruptedException {
		
		printWriter=new PrintWriter(fileName+".txt");
		
		Producer[] producers=new Producer[numberOfProducers];
		Consumer[] consumers=new Consumer[numberOfConsumers];
		List<Thread> threads=new ArrayList<>();
		
		if(numberOfProducers==producerTimeDelay.size()) {
			for(int i=0; i<numberOfProducers; i++) {
				producers[i]=new Producer(bufor,"Producer-"+i,producerTimeDelay.get(i));
				threads.add(producers[i]);
			}
		}else {
			throw new IllegalArgumentException("Incorrect number of producerTimeDelay argument");
		}
		
		if(numberOfConsumers==consumerTimeDelay.size()) {
			for(int i=0; i<numberOfConsumers; i++) {
				consumers[i]=new Consumer(bufor,"Consumer-"+i,consumerTimeDelay.get(i));
				threads.add(consumers[i]);
			}
		}else {
			throw new IllegalArgumentException("Incorrect number of cosumerTimeDelay argument");
		}
		
		for(Thread t: threads) {
			t.start();
		}
		
		
		for(Thread t: threads) {
			t.join();
		}
	}
	
	private static void printInfo() {
		printWriter.println(table.toString());
		printWriter.println();
		
		Collections.sort(table);
		printWriter.println(table.toString());
		printWriter.close();
		
		System.out.println("Done");	
	}
	
	private static void help() {
		System.err.println("usage: java PKmon" +
				" <fileName>" +
				" {zad1|zad2}"+
				" <buforCapacity>" +
				" <numberOfProducers>" +
				" <numberOfConsumers>"+
				" {setDelayTime?: Y|N}"+
				" <producersTimeDelay ...>"+
				" <consumersTimeDelay ...>");
	}
}	
