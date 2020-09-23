package lab4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResourceConditionsFavorsWriter extends SharedResource {
	private int writersCounter;
	private boolean isWritten;
	private boolean isRead;
	
	private Lock lock;
	private Condition turn;
	
	public SharedResourceConditionsFavorsWriter() {
		writersCounter=0;
		isWritten=false;
		isRead=false;
		lock=new ReentrantLock();
		turn=lock.newCondition();
	}
	
	
	//Writer
	@Override
	protected void beforeSettingResource() throws Exception {
		lock.lock();
		
		writersCounter++;
		while(isRead || isWritten) turn.await();
		isWritten=true;
	
		lock.unlock();
	}
	
	@Override
	protected void afterSettingResource() throws Exception {
		lock.lock();

		isWritten=false;
		writersCounter--;
		turn.signalAll();
		lock.unlock();
	}

	
	
	
	
	
	
	
	//Reader
	@Override
	protected void beforeGettingResource() throws Exception {
		lock.lock();
		
		while(isWritten || writersCounter>0) turn.await();
		isRead=true;
		
		lock.unlock();
	}

	@Override
	protected void afterGettingResource() throws Exception {
		lock.lock();
		
		isRead=false;
		turn.signalAll();
		lock.unlock();
	}
}
