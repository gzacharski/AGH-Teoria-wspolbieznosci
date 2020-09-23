package lab5.zad1;

public class Converter extends Thread{
	private Buffer previous;
	private Buffer next;
	private int iterations;
	
	public Converter(Buffer previous, Buffer next,int iterations) {
		this.previous=previous;
		this.next=next;
		this.iterations=iterations;
	}
	
	public void run() {
		for(int i=0; i<iterations; i++) {
			
			int tmp=previous.get();
			
			try {
				System.out.println("Thread " + Thread.currentThread().getName() + " operates " +tmp);
				sleep(10);
			}catch(InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
			next.put(tmp);
			
		}
	}
}
