package lab7.a;

import java.util.Random;

public class Filozof extends Thread{
	private int _licznik = 0;
	private final Widelec lewyWidelec;
	private final Widelec prawyWidelec;
	private Random random;

	public Filozof(Widelec lewyWidelec, Widelec prawyWidelec) {
		this.lewyWidelec=lewyWidelec;
		this.prawyWidelec=prawyWidelec;
		this.random=new Random();
	}
	
	public void run() {
		while (true) {
			
		  // jedzenie
		  ++_licznik;
		  if (_licznik % 10 == 0) {
			  
			  lewyWidelec.podnies();
			  prawyWidelec.podnies();
			  
			  try {
				  Thread.sleep((long)random.nextInt(1000));
			  }catch (InterruptedException e) {
				  e.printStackTrace();
			  }finally {
				  Fil5mon.log("Filozof: " + Thread.currentThread().getName() + " jadlem \t" + _licznik/10 + " razy");
				  Fil5mon.increment();
			  }
			  
			  prawyWidelec.odloz();
			  lewyWidelec.odloz();
		  }
		  
		  // koniec jedzenia
		  Fil5mon.log("Filozof: "+Thread.currentThread().getName()+" rozmyœla...");
		
		}
	}
}
