package lab5.zad2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Buffer {
	private int size;
	private LinkedList<Integer> bufferInterior;
	
    public Buffer(int size){
    	this.size = size;
    	bufferInterior=new LinkedList<>();
    }
    
    public synchronized void put(List<Integer> list) {
    	
    	while((bufferInterior.size()+list.size()) > size) {
    		try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}

    	bufferInterior.addAll(list);
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(Thread.currentThread().getName() + " has been interrupted");
		}
    	
    	notifyAll();
	}
    
    public synchronized List<Integer> get(int quantityToGet) {
    	List<Integer> list=new ArrayList<Integer>();
    	
    	while(bufferInterior.size() < quantityToGet){
		    try{
		    	wait();
		    }catch(InterruptedException e){
		    	System.err.println(Thread.currentThread().getName() + " has been interrupted");
		    }
		}
		
    	for(int i=0; i<quantityToGet; i++) {
    		int temp=bufferInterior.removeFirst();
    		list.add(temp);
    	}

    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	notifyAll();

    	return list;
    }
}
    
