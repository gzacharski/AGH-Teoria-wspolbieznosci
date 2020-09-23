package lab7.b;

import java.util.concurrent.Semaphore;

public class Widelec {
	private final int id;
	private Semaphore jestZajety;
	private Semaphore jestUchwycony;
	
	public Widelec(int id) {
		this.id=id;
		this.jestZajety=new Semaphore(1);
		this.jestUchwycony=new Semaphore(2);
	}
	
	public int getId() {
		return id;
	}
	
	public boolean jestZajety() {
		return (jestZajety.availablePermits()==0);
	}

	public boolean podnies() {
		boolean succes=false;
		
		synchronized(this) {
			try {
				jestUchwycony.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		synchronized(this) {
			if(jestUchwycony.availablePermits()==1 && jestZajety.availablePermits()==1) {
				try {
					jestZajety.acquire();
					succes=true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				jestUchwycony.release();
				succes=false;
			}
		}
		
		return succes;
	}
	
	public synchronized void odloz() {
		jestZajety.release();
		jestUchwycony.release();
	}
}