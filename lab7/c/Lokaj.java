package lab7.c;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lokaj {
	private final Widelec[] widelec;
	private Lock lock;
	
	public Lokaj(Widelec[] widelec) {
		this.widelec=widelec;
		this.lock=new ReentrantLock();
	}
	
	public boolean czyWidelceZajete(int lewyWidelec, int prawyWidelec) {
		return widelec[lewyWidelec].jestPodniesiony() || widelec[prawyWidelec].jestPodniesiony();
	}
	
	public Lock getLock() {
		return lock;
	}
}
