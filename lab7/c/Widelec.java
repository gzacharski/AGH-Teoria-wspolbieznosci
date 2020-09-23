package lab7.c;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Widelec {
	private final int id;
	private boolean jestPodniesiony;
	private Lock lock;
	
	public Widelec(int id) {
		this.id=id;
		this.jestPodniesiony=false;
		this.lock=new ReentrantLock();
	}
	
	public int getId() {
		return id;
	}
	
	public boolean jestPodniesiony() {
		return jestPodniesiony;
	}

	public void podnies() {
		lock.lock();
		
		jestPodniesiony=true;

		Fil5mon.log(Thread.currentThread().getName()+" podnosi widelec nr:\t"+this.id);
	}
	
	public void odloz() {
		
		Fil5mon.log(Thread.currentThread().getName()+" odk³ada widelec nr:\t"+this.id);
		
		jestPodniesiony=false;
		
		lock.unlock();
	}
}
