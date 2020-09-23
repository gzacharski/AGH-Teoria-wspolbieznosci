package lab4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResourceConditionsFIFO extends SharedResource {
	private int readerWaiting;
	private int writersWaiting;
	private boolean isWritten;
	
	private Lock lock;
	private Condition canRead;
	private Condition canWrite;
	
	public SharedResourceConditionsFIFO() {
		readerWaiting=0;
		writersWaiting=0;
		isWritten=false;
		lock=new ReentrantLock();
		canRead=lock.newCondition();
		canWrite=lock.newCondition();
	}
	
	
	
	//Writer
	@Override
	protected void beforeSettingResource() throws Exception {
		lock.lock();
		
		while(isWritten) {
			writersWaiting++;
			canWrite.await();
			writersWaiting--;
		}
		
		isWritten=true;
		
		lock.unlock();
	}
	
	@Override
	protected void afterSettingResource() throws Exception {
		lock.lock();
		
		isWritten=false;
		
		if(readerWaiting>0) {
			canRead.signalAll();
		}else if(writersWaiting>0){
			canWrite.signal();
		}

		lock.unlock();
	}

	

	
	//Reader
	@Override
	protected void beforeGettingResource() throws Exception {
		lock.lock();
		
		while(isWritten) {
			readerWaiting++;
			canRead.await();
			readerWaiting--;
		}
		
		lock.unlock();
	}

	@Override
	protected void afterGettingResource() throws Exception {
		lock.lock();

		if(!isWritten && writersWaiting>0) canWrite.signal();
		
		lock.unlock();
	}
}
