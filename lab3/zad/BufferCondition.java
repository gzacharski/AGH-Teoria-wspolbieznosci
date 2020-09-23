public class BufferCondition extends Buffer {
	
	public BufferCondition(int capacity) {
		this.setCapacity(capacity);
	}
	
	@Override
	public synchronized void put(int i) throws InterruptedException{
		printInfo("PUT " +i+ " - before: ", false);
		
		while(this.getLinkedList().size()==this.getCapacity()) {
			wait();
		}
		
		this.getLinkedList().add(i);
		
		printInfo("PUT " +i+ " - after: ", true);
		
		notifyAll();
	}

	public synchronized int get() throws InterruptedException{
		printInfo("GET - before: ", false);
		
		while(this.getLinkedList().size()==0) {
			wait();;
		}
		
		int value=this.getLinkedList().removeFirst();
		
		printInfo("GET - after: ", true);
		
		PKmon.table.add(value);
		notifyAll();;
		return value;
	}
	
}
