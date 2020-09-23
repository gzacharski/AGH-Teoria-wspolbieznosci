package lab7.d.b;

import java.util.Random;

import lab7.b.Widelce;

public class Filozof extends Thread{
	private int _licznik = 0;
	private Random random;
	private final Widelce widelce;
	private int ileRazyJadl;

	public Filozof(Widelce widelce) {
		this.random=new Random();
		this.widelce=widelce;
		this.ileRazyJadl=0;
	}
	
	public void run() {
		while (true) {
			
		  // jedzenie
		  ++_licznik;
		  if (_licznik % 10 == 0) {

			  widelce.podnies();
			  
			  if(widelce.podniesione()) {
				  
				  synchronized(this) {

					  Fil5mon.log(Thread.currentThread().getName() + " jadlem \t" + (++ileRazyJadl) + " razy");
					  Fil5mon.increment();
				  
				  }
				  
				  widelce.odloz();
			  }
			  
		  }
		  
		  // koniec jedzenia
		  //Fil5mon.log("Filozof: "+Thread.currentThread().getName()+" rozmyœla...");
		  if(Fil5mon.stopThread) break;
		}
	}
}
