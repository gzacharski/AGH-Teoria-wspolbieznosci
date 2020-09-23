package lab5.zad1;

import java.util.LinkedList;

public class Buffer {
	private int size;
	private LinkedList<Integer> bufferInterior;
	
    public Buffer(int size){
    	this.size = size;
    	bufferInterior=new LinkedList<>();
    }
    
    public synchronized void put(int value) {
    	
    	while(bufferInterior.size() == size) {
    		try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}

    		
    	bufferInterior.add(value);
    	
    	notifyAll();
	}
    
    public synchronized int get() {
    	int value=0;
    	
    	while(bufferInterior.size() == 0){
		    try{
		    	wait();
		    }catch(InterruptedException e){
		    	System.out.println(e.getMessage());
		    }
		}
		
    	value=bufferInterior.removeFirst();
    	notifyAll();

    	return value;
    }
}
    
