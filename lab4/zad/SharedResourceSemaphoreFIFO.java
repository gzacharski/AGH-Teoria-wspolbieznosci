package lab4;

import java.util.concurrent.Semaphore;

public class SharedResourceSemaphoreFIFO extends SharedResource{

	private int readersCounter;
	private Semaphore resourceAccess;
	private Semaphore readersCounterAccess;
	private Semaphore serviceQueue;

	public SharedResourceSemaphoreFIFO() {
		this.readersCounter=0;
		this.readersCounterAccess=new Semaphore(1);
		this.resourceAccess=new Semaphore(1);
		this.serviceQueue=new Semaphore(1);
	}

	@Override
	protected void beforeSettingResource() throws InterruptedException {
		serviceQueue.acquire();
		
			resourceAccess.acquire();
			
		serviceQueue.release();
	}
	
	@Override
	protected void afterSettingResource() {
		resourceAccess.release();
	}

	@Override
	protected void beforeGettingResource() throws InterruptedException {
		serviceQueue.acquire();
		
			readersCounterAccess.acquire();
			
				if(readersCounter==0) {
					resourceAccess.acquire();
				}
				readersCounter++; 
				
			readersCounterAccess.release();
			
		serviceQueue.release();
	}

	@Override
	protected void afterGettingResource() throws InterruptedException {
		readersCounterAccess.acquire();
		
			readersCounter--;
			if(readersCounter==0) {
				resourceAccess.release();
			}
			
		readersCounterAccess.release();
	}
}
 