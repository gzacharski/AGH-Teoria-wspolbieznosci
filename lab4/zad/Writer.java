package lab4;

public class Writer extends Thread{
	private SharedResource sharedResource;
	private int repeats;
	
	public Writer(SharedResource sharedResource, int repeats) {
		this.sharedResource=sharedResource;
		this.repeats=repeats;
	}
	
	public void run() {
		for(int i=0;i<repeats;i++){
			
			try {
				char character=(char)(65+(int)(90-65)*Math.random());
				sharedResource.setResource(String.valueOf(character));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
