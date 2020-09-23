package lab7.b;

public class Widelce {
	private final Widelec lewyWidelec;
	private final Widelec prawyWidelec;
	private boolean podniesione;
	private boolean lewyWidelecZajety;
	private boolean prawyWidelecZajety;
	
	public Widelce(Widelec lewyWidelec, Widelec prawyWidelec) {
		this.lewyWidelec=lewyWidelec;
		this.prawyWidelec=prawyWidelec;
		this.podniesione=false;
		this.lewyWidelecZajety=false;
		this.prawyWidelecZajety=false;
	}
	
	public void podnies() {
		lewyWidelecZajety=lewyWidelec.podnies();
		prawyWidelecZajety=prawyWidelec.podnies();
		
		if(lewyWidelecZajety && prawyWidelecZajety) {
			podniesione=true;
			Fil5mon.log(Thread.currentThread().getName() + " podnosi widelce nr:\t " + lewyWidelec.getId()+ " oraz "+prawyWidelec.getId());
			
		}else {
			if(lewyWidelecZajety) {
				
				lewyWidelec.odloz();
				lewyWidelecZajety=false;
				
			}else if(prawyWidelecZajety) {
				
				prawyWidelec.odloz();
				prawyWidelecZajety=false;
				
			}

		}
		
	}

	
	public void odloz() {
		lewyWidelecZajety=false;
		prawyWidelecZajety=false;
		podniesione=false;
		Fil5mon.log(Thread.currentThread().getName() + " odk³ada widelce nr:\t" + lewyWidelec.getId()+ " oraz "+prawyWidelec.getId());
		lewyWidelec.odloz();
		prawyWidelec.odloz();
	}
	
	public boolean podniesione() {
		return podniesione;
	}
	
	
}
