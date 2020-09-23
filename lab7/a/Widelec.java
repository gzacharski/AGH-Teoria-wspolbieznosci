package lab7.a;

public class Widelec {
	private final int id;
	private boolean jestPodniesiony;
	
	public Widelec(int id) {
		this.id=id;
		this.jestPodniesiony=false;
	}
	
	public int getId() {
		return id;
	}

	public synchronized void podnies() {
		Fil5mon.log(Thread.currentThread().getName()+" próbuje podnieœ widelec nr:\t"+this.id);
		
		while(jestPodniesiony) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		jestPodniesiony=true;

		Fil5mon.log(Thread.currentThread().getName()+" podnosi widelec nr:\t"+this.id);
	}
	
	public synchronized void odloz() {
		
		Fil5mon.log(Thread.currentThread().getName()+" odk³ada widelec nr:\t"+this.id);
		
		jestPodniesiony=false;
		
		notifyAll();
	}
}
