package lab4;

public class Reader extends Thread{
	private SharedResource sharedResource;
	private int repeats;
	
	public Reader(SharedResource sharedResource, int repeats) {
		this.sharedResource=sharedResource;
		this.repeats=repeats;
	}
	
	public void run() {
		for(int i=0;i<repeats;i++) {
			try {
				sharedResource.getResource();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
 