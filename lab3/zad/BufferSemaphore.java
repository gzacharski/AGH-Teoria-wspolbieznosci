import java.util.concurrent.Semaphore;

public class BufferSemaphore extends Buffer {
	
	private Semaphore canPut;
	private Semaphore mutex;
	private Semaphore canGet;
	
	public BufferSemaphore(int capacity) {
		this.setCapacity(capacity);
		this.canPut=new Semaphore(capacity);
		this.mutex=new Semaphore(1);
		this.canGet=new Semaphore(0);
	}

	public void put(int i) throws InterruptedException{
		
		canPut.acquire();
		mutex.acquire();
			
		printInfo("PUT " +i+ " - before: ", false);
		this.getLinkedList().add(i);
		printInfo("PUT " +i+ " - after: ", true);
		
		mutex.release();
		canGet.release();
		
	}
	
	public int get() throws InterruptedException{

		canGet.acquire();	
		mutex.acquire();
	
		printInfo("GET - before: ", false);
		int value=this.getLinkedList().removeFirst();
		PKmon.table.add(value);
		printInfo("GET - after: ", true);
			
		mutex.release();
		canPut.release();

		return value;
	}
}
