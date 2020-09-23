package lab4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResourceConditionsFavorsReader extends SharedResource {
	private int readersCounter;
	private boolean isWritten;
	
	private Lock lock;
	private Condition turn;
	
	public SharedResourceConditionsFavorsReader() {
		readersCounter=0;
		isWritten=false;
		lock=new ReentrantLock();
		turn=lock.newCondition();
	}
	
	
	//Writer
	@Override
	protected void beforeSettingResource() throws Exception {
		lock.lock();
		
		while(readersCounter>0 || isWritten) turn.await();
	
		isWritten=true;
	
		lock.unlock();
	}
	
	@Override
	protected void afterSettingResource() throws Exception {
		lock.lock();

		isWritten=false;
		turn.signalAll();
		lock.unlock();
	}

	
	
	//Reader
	@Override
	protected void beforeGettingResource() throws Exception {
		lock.lock();
		
		readersCounter++;
		while(isWritten) turn.await();
		
		lock.unlock();
	}

	@Override
	protected void afterGettingResource() throws Exception {
		lock.lock();
		
		readersCounter--;
		turn.signalAll();
		lock.unlock();
	}
}
