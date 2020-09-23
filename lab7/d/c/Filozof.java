package lab7.d.c;

import java.util.Random;

import lab7.c.Lokaj;
import lab7.c.Widelec;

public class Filozof extends Thread{
	private int _licznik = 0;
	private final Widelec lewyWidelec;
	private final Widelec prawyWidelec;
	private Random random;
	private final Lokaj lokaj;
	private boolean konsumuje;
	private int ileRazyJadl;

	public Filozof(Widelec lewyWidelec, Widelec prawyWidelec, Lokaj lokaj) {
		this.lewyWidelec=lewyWidelec;
		this.prawyWidelec=prawyWidelec;
		this.random=new Random();
		this.lokaj=lokaj;
		this.konsumuje=false;
		this.ileRazyJadl=0;
	}
	
	public void run() {
		while (true) {
			
		  // jedzenie
		  ++_licznik;
		  if (_licznik % 10 == 0) {
			  
			  	lokaj.getLock().lock();
			  	
				if(!lokaj.czyWidelceZajete(lewyWidelec.getId(), prawyWidelec.getId())) {
					  lewyWidelec.podnies();
					  prawyWidelec.podnies();
					  konsumuje=true;
					  ileRazyJadl++;
				}
				
				lokaj.getLock().unlock();
				
			  
			  if(konsumuje) {
			
				  Fil5mon.log("Filozof: " + Thread.currentThread().getName() + " jadlem \t" + (++ileRazyJadl) + " razy");
				  Fil5mon.increment();
				  
				  
				  prawyWidelec.odloz();
				  lewyWidelec.odloz();
				  konsumuje=false;
				  
			  }
		  }

		  // koniec jedzenia
		 //Fil5mon.log("Filozof: "+Thread.currentThread().getName()+" rozmyœla...");
		
		  if(Fil5mon.stopThread) break;
		}
	}
}
