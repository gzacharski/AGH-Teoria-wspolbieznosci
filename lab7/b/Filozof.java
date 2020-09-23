package lab7.b;

import java.util.Random;

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
					  try {
						  Thread.sleep((long)random.nextInt(1000));
					  }catch (InterruptedException e) {
						  e.printStackTrace();
					  }finally {
						  Fil5mon.log(Thread.currentThread().getName() + " jadlem \t" + (++ileRazyJadl) + " razy");
						  Fil5mon.increment();
					  }
				  }
				  
				  widelce.odloz();
			  }
			  
		  }
		  
		  // koniec jedzenia
		  //Fil5mon.log("Filozof: "+Thread.currentThread().getName()+" rozmyœla...");
		}
	}
}
