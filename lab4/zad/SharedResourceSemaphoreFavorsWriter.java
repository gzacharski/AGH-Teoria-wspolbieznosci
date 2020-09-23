package lab4;

import java.util.concurrent.Semaphore;

public class SharedResourceSemaphoreFavorsWriter extends SharedResource{
	private int readersCounter;
	private int writerCounter;
	private Semaphore resourceAccess;
	private Semaphore tryReadResourceAccess;
	private Semaphore readersCounterAccess;
	private Semaphore writerCounterAccess;

	public SharedResourceSemaphoreFavorsWriter() {
		this.readersCounter=0;
		this.writerCounter=0;
		this.resourceAccess=new Semaphore(1);
		this.tryReadResourceAccess=new Semaphore(1);
		this.readersCounterAccess=new Semaphore(1);
		this.writerCounterAccess=new Semaphore(1);
	}

	@Override
	protected void beforeSettingResource() throws InterruptedException {
		writerCounterAccess.acquire();
		
			writerCounter++;
			if(writerCounter==1) tryReadResourceAccess.acquire();
			
		writerCounterAccess.release();
		
		resourceAccess.acquire();
	}
	
	@Override
	protected void afterSettingResource() throws InterruptedException {
		resourceAccess.release();
		
		writerCounterAccess.acquire();
		
			writerCounter--;
			if(writerCounter==0) tryReadResourceAccess.release();
			
		writerCounterAccess.release();
	}

	@Override
	protected void beforeGettingResource() throws InterruptedException {
		tryReadResourceAccess.acquire();
		
			readersCounterAccess.acquire();
				
				if(readersCounter==0) resourceAccess.acquire();
	
				readersCounter++; 
				
			readersCounterAccess.release();
			
		tryReadResourceAccess.release();
	}

	@Override
	protected void afterGettingResource() throws InterruptedException {
		readersCounterAccess.acquire();
		
			readersCounter--;
			
			if(readersCounter==0) resourceAccess.release();

		readersCounterAccess.release();
	}
}
