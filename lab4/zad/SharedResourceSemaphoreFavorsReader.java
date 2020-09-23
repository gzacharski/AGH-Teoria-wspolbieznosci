package lab4;

import java.util.concurrent.Semaphore;

public class SharedResourceSemaphoreFavorsReader extends SharedResource{
	
	private int readersCounter;
	private Semaphore resourceAccess;
	private Semaphore readersCounterAccess;

	public SharedResourceSemaphoreFavorsReader() {
		this.readersCounter=0;
		this.readersCounterAccess=new Semaphore(1);
		this.resourceAccess=new Semaphore(1);
	}

	@Override
	protected void beforeSettingResource() throws InterruptedException {
		resourceAccess.acquire();
	}
	
	@Override
	protected void afterSettingResource() {
		resourceAccess.release();
	}

	@Override
	protected void beforeGettingResource() throws InterruptedException {
		readersCounterAccess.acquire();
		
			if(readersCounter==0) resourceAccess.acquire();
				
			readersCounter++; 
			
		readersCounterAccess.release();
	}

	@Override
	protected void afterGettingResource() throws InterruptedException {
		readersCounterAccess.acquire();
		
			readersCounter--;
			
			if(readersCounter==0) resourceAccess.release();

		readersCounterAccess.release();
	}
}
